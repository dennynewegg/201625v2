package com.dy45.reader.Util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy45 on 5/16/2015.
 */
public class ReflectUtil {
    public static void reSetObject(Object source,Object desc){
        if(source==null
            || desc == null){
            return;
        }
        List<Method> getMList = getGetMethods(source.getClass());
        List<Method> setMList = getSetMethods(desc.getClass());
        for (Method getm:getMList){
            String propertyName = getm.getName().substring(3);
            for (Method setm:setMList){
                String setPropertyName = setm.getName().substring(3);
                if(StringUtil.isEqual(propertyName,setPropertyName)
                        &&getm.getReturnType() == setm.getParameterTypes()[0]){

                    Object value = null;
                    try
                    {
                        value = getm.invoke(source,new Object[0]);
                        if(value != null
                           && value!=getm.getDefaultValue())
                        {
                            setm.invoke(desc,value);
                        }
                    }catch (Exception ex){

                    }


                    continue;
                }
            }
        }

    }

    public static List<Method> getGetMethods(Class<?> clazz) {

        Method [] methods = clazz.getMethods();
        List<Method> list = new ArrayList<>(10);
        for(Method m:methods){
            if(m.getName().toLowerCase().startsWith("get")
                    &&m.getName().length()>3){
                if((m.getParameterTypes() == null
                        || m.getParameterTypes().length==0)
                        && !StringUtil.isEqual(m.getReturnType().getName(),"void")) {
                    list.add(m);
                }
            }
        }
        return list;
    }

    public static List<Method> getSetMethods(Class<?> clazz) {
        Method [] methods = clazz.getMethods();
        List<Method> list = new ArrayList<>(10);
        for(Method m:methods){
            if(m.getName().toLowerCase().startsWith("set")
                    &&m.getName().length()>3){
                if(m.getParameterTypes()!=null
                    && m.getParameterTypes().length==1
                    && StringUtil.isEqual(m.getReturnType().getName(),"void")) {
                    list.add(m);
                }
            }
        }
        return list;
    }
}
