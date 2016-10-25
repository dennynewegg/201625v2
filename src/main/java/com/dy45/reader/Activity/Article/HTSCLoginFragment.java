package com.dy45.reader.Activity.Article;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;
import com.dy45.reader.Util.ToastUtil;
import com.dy45.reader.entity.RestResult;
import com.dy45.reader.entity.htsctokendto;
import com.dy45.reader.http.RequestWrapper;
import com.dy45.articlereader.R;


public class HTSCLoginFragment extends Fragment implements Response.ErrorListener {

    public static final String INTENT_COOKIE="INTENT_COOKIE";
    public static final int INTENT_COOKIE_RESULTCODE = 10;

//    http://www.htsc.com.cn/htzq/zjclub/login.jsp
//    http://www.htsc.com.cn/htzq/tradeUserLogin.do?method=ImageCode&time=1429695284169
    private String loginUIUrl = "http://www.htsc.com.cn/htzq/zjclub/login.jsp";
    private String loginUrl = "http://www.htsc.com.cn/htzq/tradeUserLogin.do?method=zgkhUserlogin&parentIndex=true&zjcfClub=Y";
    private String imgBaseUrl = "http://www.htsc.com.cn/htzq/verifyCode.do?method=getVerifyCode";
    private String tokenUrl = "http://www.htsc.com.cn/htzq/tokenAction.do?method=getToken&key=TOKEN_TRADE_USER_LOGIN";
    private RequestWrapper wrapper;
    private EditText userNameView;
    private EditText passwordView;
    private EditText validateCodeView;
    private Button loginButton;
    private ImageView validateImage;
    private boolean isrefreshing = false;
    private View loginContainer;
    private ProgressBar processbar;
    private String cookie;


    public HTSCLoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_htsclogin, container, false);
        findView(view);
        RefreshValidateImage();
        isrefreshing = false;
        return view;
    }


    public void RefreshValidateImage(){
        wrapper = new RequestWrapper();
        wrapper.getString(loginUIUrl, new OnActionListener1<RestResult<String>>() {
            @Override
            public void onAction(RestResult<String> stringRestResult) {
                if(!stringRestResult.hasError()){
                    cookie = stringRestResult.getCookie();
                    wrapper.addCookie(cookie);
                    String imgUrl = String.format(imgBaseUrl,System.currentTimeMillis());
                    wrapper.getBytes(HTSCLoginFragment.this.getActivity(), imgUrl,new OnActionListener1<RestResult<byte[]>>() {
                        @Override
                        public void onAction(RestResult<byte[]> restResult) {
                            if (!restResult.hasError()) {
                                byte[] imageBytes = restResult.getResult();
                                if (imageBytes != null
                                        && imageBytes.length > 0) {
                                    validateImage.setImageBitmap(BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
                                }
                            }
                        }
                    } );
                }
            }
        });
    }


    private void findView(View view) {
        loginButton = (Button) view.findViewById(R.id.htsc_login);
        userNameView = (EditText) view.findViewById(R.id.htsc_username);
        passwordView = (EditText) view.findViewById(R.id.htsc_password);
        validateImage = (ImageView) view.findViewById(R.id.htsc_validateCodeImg);
        validateCodeView = (EditText) view.findViewById(R.id.htsc_validateCodeText);
        loginContainer = view.findViewById(R.id.login_container);
        processbar = (ProgressBar) view.findViewById(R.id.login_progress);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginIn();
            }
        });

        validateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTSCLoginFragment.this.RefreshValidateImage();
            }
        });

        validateCodeView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
                {
                    loginIn();
                    return  true;
                }

                return false;
            }
        });
        validateCodeView.requestFocus();
    }

    private void showProcess(){
        loginContainer.setVisibility(View.GONE);
        processbar.setVisibility(View.VISIBLE);
    }

    private void hideProcess(){
        loginContainer.setVisibility(View.VISIBLE);
        processbar.setVisibility(View.GONE);
    }



    private void loginIn(){
        if(!isrefreshing) {
            showProcess();
            String userName = userNameView.getText().toString();
            String password = passwordView.getText().toString();
            String validateCode = validateCodeView.getText().toString();
            if (!StringUtil.isEmpty(userName)
                    && !StringUtil.isEmpty(password)
                    && !StringUtil.isEmpty(validateCode)) {
                isrefreshing = true;
                wrapper.getString(tokenUrl,stringRestResult -> {
                    if(!stringRestResult.hasError()) {
                        htsctokendto token = JSON.parseObject(stringRestResult.getResult(),htsctokendto.class);
                        if(token.getSuccess().equalsIgnoreCase("true")) {
                            String postContext = String.format("%1s=%2s&account=%3s&servicepwd=%4s&verifyCode="
                                    ,token.getToken_key()
                                    ,token.getToken_value()
                                    , userName
                                    , Base64.encodeToString(password.getBytes(), Base64.NO_WRAP));

                            postContext = postContext+validateCode.trim();
                            wrapper.postString(loginUrl, postContext, stringRestResult1 -> {
                                if(!stringRestResult1.hasError()){
                                    String htmlContext = stringRestResult1.getResult();
                                    if (htmlContext.contains("您的客户经理")) {
                                        Intent intent = new Intent();
                                        intent.putExtra(INTENT_COOKIE, cookie);
                                        getActivity().setResult(INTENT_COOKIE_RESULTCODE, intent);
                                        getActivity().finish();
                                        return;
                                    }
                                }
                                hideProcess();
                                isrefreshing = false;
                                RefreshValidateImage();
                                ToastUtil.show(HTSCLoginFragment.this.getActivity(), "Login Failed");
                            });
                            return ;
                        }
                    }
                    hideProcess();
                    isrefreshing = false;
                    RefreshValidateImage();
                    ToastUtil.show(HTSCLoginFragment.this.getActivity(), "Login Failed");
                });
            }
        }
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        isrefreshing = false;
        hideProcess();
        ToastUtil.show(this.getActivity(),volleyError.getMessage());
    }

}
