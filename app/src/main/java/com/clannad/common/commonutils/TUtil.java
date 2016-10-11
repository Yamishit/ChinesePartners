package com.clannad.common.commonutils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by F_ck on 2016/10/10.
 * 类转换初始化
 */

public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[i])
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className){
        try{
            return Class.forName(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
