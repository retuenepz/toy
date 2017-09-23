package com.toy.mvc.route;

import java.lang.reflect.Method;

/**
 * 代表一个路由
 * Created by Administrator on 2017/9/23.
 */
public class Route {
    /**
     * 路由的地址
     */
    private String path ;
    /**
     * 请求对应的方法
     */
    private Method action;
    /**
     * 请求对应的控制器
     */
    private Object controller;

    public Method getAction() {
        return action;
    }

    public void setAction(Method action) {
        this.action = action;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
