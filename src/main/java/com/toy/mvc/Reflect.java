package com.toy.mvc;

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
}
