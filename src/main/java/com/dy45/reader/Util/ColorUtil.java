package com.dy45.reader.Util;

import android.graphics.Color;

/**
 * Created by user on 2015/5/17.
 */
public class ColorUtil {
    public final static int Black_Color = Color.rgb(0, 0, 0);
    public final static int Red_Color = Color.rgb(230,10,10);
    public final static int Green_Color = Color.rgb(10,230,10);

    public static int getColor(Double value){
        int color = ColorUtil.Black_Color;
        if(value!=null)
        {
            if(value>0){
                color = ColorUtil.Red_Color;
            }
            else if(value<0){
                color = ColorUtil.Green_Color;
            }
        }
        return color;
    }
}
