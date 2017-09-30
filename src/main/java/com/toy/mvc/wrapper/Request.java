package com.toy.mvc.wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest 增强
 * Created by Administrator on 2017/9/23.
 */
public class Request {
    private HttpServletRequest request = null ;

    public Request(HttpServletRequest request) {
        this.request = request;
    }
    public void setAttr(String name , Object val){
        this.request.setAttribute(name,val);
    }
    public Object getAttr(String name){
        return this.request.getAttribute(name);
    }
    public String getParam(String name){
        return this.request.getParameter(name);
    }
}
