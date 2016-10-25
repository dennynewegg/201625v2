package com.dy45.reader.Util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dy45.articlereader.R;

/**
 * Created by dy45 on 4/22/2015.
 */
public class ToastUtil {


    public static final int TOAST_DURATION_LONG = Toast.LENGTH_LONG;

    /**
     * show toast message after action executed. the toast display time is short
     * default.
     *
     * @param mContext
     * @param message
     */

    private static Toast mToast = null;
    private static int mScreenWidth = 0;

    public static void show(Context context, String message) {
        if (context == null) {
            return;
        }
        View viewContainer = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) viewContainer;
        textView.setText(message);
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 140);
        }
        mToast.setView(getToastView(message, context));
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

    /**
     * show toast message after action executed.
     *
     * @param context
     * @param message
     * @param duration : Toast.LENGTH_SHORT or Toast.LENGTH_LONG
     */
    public static void show(Context context, String message, int duration) {
        if (context == null) {
            return;
        }
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 140);
        }
        mToast.setView(getToastView(message, context));
        mToast.setDuration(duration);
        mToast.show();
    }

    private static View getToastView(String message, Context context) {
//        R.layout.toast_layout
        View viewContainer = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) viewContainer;
        textView.setText(message);
        textView.setWidth(mScreenWidth);
        return viewContainer;
    }

    public static void getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
    }

}
