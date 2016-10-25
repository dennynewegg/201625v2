package com.dy45.reader.Util;

import com.dy45.reader.ReaderApp;

/**
 * Created by dy45 on 4/25/2015.
 */
public class ExceptionUtil {
    public static void handle(Throwable ex) {
        StringBuffer stringBuffer = new StringBuffer(1024);
        stringBuffer.append("\nMessage:\n");
        stringBuffer.append(ex.getMessage());
        stringBuffer.append("\n");
        stringBuffer.append("StackTrace:\n");
        for(StackTraceElement stackTraceElement :ex.getStackTrace()) {
            stringBuffer.append(stackTraceElement.toString());
            stringBuffer.append("\n");
        }
        final String msg = ex.getMessage();

        LogUtil.e(stringBuffer.toString());

        HandlerUtil.post(() -> {
            ToastUtil.show(ReaderApp.Instance, msg);
//                        HeXinApp.this.
        });
    }
}
