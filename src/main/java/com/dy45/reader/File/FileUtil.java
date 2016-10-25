package com.dy45.reader.File;

import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.dy45.reader.Util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * Created by dy45 on 4/23/2015.
 */
public class FileUtil {

    private static String cacheRoot;

    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // SD-card available
            cacheRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            cacheRoot = Environment.getDataDirectory().getAbsolutePath();
        }
        cacheRoot = cacheRoot+File.separator+"Android"+File.separator+"data"+File.separator+"AArticle";
        File file = new File(cacheRoot);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    public static String read(String fileName){
        try {
            byte[] content = readBytes(fileName);
            if(content!=null) {
                return new String(content, "UTF-8");
            }
        }catch (Exception ex){

        }
        return "";
    }

    public static byte[] readBytes(String fileName){
        try {
            fileName = getPath(fileName);
            File file = new File(fileName);
            if(file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                byte[] buffer = new byte[fileInputStream.available()];
                fileInputStream.read(buffer, 0, fileInputStream.available());
                fileInputStream.close();
                return buffer;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void write(String fileName,String fileContent,boolean isAppend){
            write(fileName, fileContent.getBytes(), isAppend);
    }

    public static void writeObject(String filePath,Object obj){
        if(obj!=null){
            String content = JSON.toJSONString(obj);
            if(!StringUtil.isEmpty(content)) {
                write(filePath, content, false);
            }
        }
    }

    public static void writeLine(String fileName,String fileContent,boolean isAppend){
        if(fileContent!=null){
            fileContent=fileContent+"\n";
        }
        else{
            fileContent="\n";
        }
        write(fileName,fileContent,isAppend);
    }

    public static boolean exists(String filePath){
        if(!StringUtil.isEmpty(filePath)){
            return new File(filePath).exists();
        }
        return false;
    }


    public static void write(String fileName,byte[] fileContent,boolean isAppend){
        try {
            fileName = getPath(fileName);
            File file = new File(fileName);
            mkDirParent(file.getParentFile());
            if(!file.getParentFile().exists()){
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,isAppend);
            fileOutputStream.write(fileContent,0,fileContent.length);
            fileOutputStream.close();
        }
        catch (Exception ex){
        }
    }

    public static void mkDirParent(File file){
        if(file.exists()){
            return;
        }
        else{
            if(!file.getParentFile().exists()) {
                mkDirParent(file.getParentFile());
            }
            file.mkdir();
        }
    }

    public static String getPath(String filePath){
        if(StringUtil.isEmpty(filePath)){
            return "";
        }
        if(filePath.startsWith(cacheRoot)){
            return filePath;
        }
        return cacheRoot+File.separator+filePath;
    }

    public static <T> T readObject(String filePath, Class<T> clazz){
        String content = FileUtil.read(filePath);
        if(!StringUtil.isEmpty(content)){
            try{
                return JSON.parseObject(content,clazz);
            }
            catch (Exception ex){

            }
        }
        return null;
    }

    public static <T> List<T> readArray(String filePath, Class<T> clazz){
        String content = FileUtil.read(filePath);
        if(!StringUtil.isEmpty(content)){
            try{
                return JSON.parseArray(content, clazz);
            }
            catch (Exception ex){
            }
        }
        return null;
    }





}
