package com.toy.mvc;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上下文
 * Created by Administrator on 2017/9/30.
 */
public class MyContext {
    private static final ThreadLocal<MyContext> BOX = new ThreadLocal<MyContext>();
    private ServletContext servletContext ;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public static MyContext getContext(){
        return BOX.get();
    }
    public static void initContext(ServletContext context , HttpServletRequest request, HttpServletResponse response){
        MyContext myContext = new MyContext();
        myContext.servletContext=context;
        myContext.request=request;
        myContext.response=response;
        BOX.set(myContext);
    }
    public static void remove(){
        BOX.remove();
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
