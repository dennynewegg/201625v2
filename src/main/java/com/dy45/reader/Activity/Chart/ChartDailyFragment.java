package com.dy45.reader.Activity.Chart;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.dy45.articlereader.R;
import com.dy45.reader.Biz.ChartBiz;
import com.dy45.reader.Util.OnActionListener1;
import com.dy45.reader.Util.StringUtil;

import java.util.Date;

/**
 * Created by dy45 on 5/20/2015.
 */
public class ChartDailyFragment extends Fragment {

    public final static String Code_Param = "Code";
//    public final static String ChartType_Param = "ChartType";

    private CodeChartType ChartType ;
    private String code;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        img = (ImageView) view.findViewById(R.id.chart_daily_img);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            code = getArguments().getString(Code_Param);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!StringUtil.isEmpty(code)) {
            ChartBiz.getChartImg(this.getActivity(), code, ChartType, new OnActionListener1<byte[]>() {
                @Override
                public void onAction(byte[] bytes) {
                    if (bytes != null
                            && bytes.length > 0) {
                        img.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    }
                }
            });
        }
    }

    public CodeChartType getChartType() {
        return ChartType;
    }

    public void setChartType(CodeChartType chartType) {
        ChartType = chartType;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        img.setImageResource(0);
    }

    public enum CodeChartType{
        Min,
        Daily,
        Weekly,
        Month
    }

}
