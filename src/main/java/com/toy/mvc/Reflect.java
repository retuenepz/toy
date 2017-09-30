package com.toy.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/23.
 */
public class Reflect {
    public static Object newInstance(String name ){
        Object obj = null ;
        try {
            Class<?> clazz = Class.forName(name);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj ;
    }

    public static Object invoke(Object controller, Method action,Object... args) {
        try {
            return action.invoke(controller,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null ;
    }
}
