package com.dy45.reader.Util;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.dy45.reader.File.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Created by dy45 on 4/25/2015.
 */
public class ProcessUtil {

    private static final int[] pageList = { 2205, 2216, 2222, 2215, 2217, 2220, 2218, 2210, 2225 };

    public static boolean viewFile(Context context, String filePath){
        File file = new File(FileUtil.getPath(filePath));
        Intent target = getIntent(file);
        Intent intent = Intent.createChooser(target, "Open File");
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
        }
        return false;
    }

    private static Intent getIntent(File file) {
        String filePath = file.getAbsolutePath();
        Intent target = new Intent(Intent.ACTION_VIEW);
        if(filePath.endsWith("pdf")){
            target.setDataAndType(Uri.fromFile(file),"application/pdf");
        }
        else if(filePath.endsWith("html")){
            target.setDataAndType(Uri.fromFile(file),"text/html");
        }
        else {
            target.setData(Uri.fromFile(file));
        }
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        return target;
    }

    public static boolean openThsApp(Context paramContext, String stockcode, String stockName)
    {
        Intent localIntent = new Intent("com.hexin.plat.android.jumpfromotherapp");
        Bundle localBundle = new Bundle();
        localBundle.putString("param_stock_name", stockName);
        localBundle.putString("param_stock_code", stockcode);
        localBundle.putString("param_type", "stock_assistant");
        localBundle.putString("param_class_name", "com.hexin.android.bank.LogoActivity");
        localBundle.putString("param_package_name", "com.hexin.android.bank");
        localBundle.putInt("param_target_pageid", 2205);
        localBundle.putIntArray("param_effective_pagelist", pageList);
        if (Build.VERSION.SDK_INT >= 12) {
            localIntent.setFlags(32);
        }
        localIntent.putExtras(localBundle);
        List<ResolveInfo> resolveInfos = paramContext.getPackageManager().queryBroadcastReceivers(localIntent, 0);
        if ((resolveInfos != null) && (resolveInfos.size() > 0))
        {
            paramContext.sendBroadcast(localIntent);
            return false;
        }
        localIntent = new Intent();
        ComponentName  componentName = new ComponentName("com.hexin.plat.android", "com.hexin.plat.android.AndroidLogoActivity");
        localIntent.putExtras(localBundle);
        localIntent.setComponent(componentName);
        localIntent.setAction(Intent.ACTION_MAIN);
        localIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        paramContext.startActivity(localIntent);
        return false;
    }


}
