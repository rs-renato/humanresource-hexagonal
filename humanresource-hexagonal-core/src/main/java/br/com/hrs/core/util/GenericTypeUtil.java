package br.com.hrs.core.util;

import java.lang.reflect.ParameterizedType;

public class GenericTypeUtil {

    public static <T> String getGenericName(Class<T> clazz){
        return ((Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();
    }
}