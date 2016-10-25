package com.dy45.reader.Util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by dy45 on 5/23/2015.
 */
public class ProgressUtil {
    public static Dialog showProgress(Context context){
        ProgressDialog dialog = new ProgressDialog(context);
        return dialog;
    }

}
