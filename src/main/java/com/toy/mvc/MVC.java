package com.toy.mvc;

import com.toy.mvc.config.ConfigLoader;
import com.toy.mvc.route.Route;
import com.toy.mvc.route.RouteManager;
import com.toy.mvc.view.JspResolver;
import com.toy.mvc.view.ViewResolver;
import com.toy.mvc.wrapper.Request;
import com.toy.mvc.wrapper.Response;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23.
 */
public class MVC {
    /**
     * 路由管理器
     */
    private RouteManager routeManager = null;
    /**
     * 配置加载器
     */
    private ConfigLoader configLoader = null ;
    /**
     * 视图解析器
     */
    private ViewResolver viewResolver = null ;
    /**
     * 框架加载状态
     */
    private boolean init = false;

    public MVC() {
        routeManager = new RouteManager();
        configLoader = new ConfigLoader();
        viewResolver = new JspResolver();
    }
    public void addRoute(String path,String methodName , Object controller){
        try {
            Method method = controller.getClass().getMethod(methodName, Request.class, Response.class);
            routeManager.addRoute(path,method,controller);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public List<Route> getRoutes(){
        return routeManager.getRoutes();
    }
    public boolean isInit(){
        return this.init;
    }
    public void setInit(boolean init){
        this.init=init;
    }


    private static class MVCHolder{
        private static MVC MVC = new MVC();
    }
    public static MVC start(){
        return MVCHolder.MVC;
    }
    public ViewResolver getViewResolver(){
        return this.viewResolver;
    }
}
