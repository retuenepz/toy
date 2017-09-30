package com.toy.mvc;

import com.toy.mvc.config.Const;
import com.toy.mvc.route.Route;
import com.toy.mvc.route.RouteMatcher;
import com.toy.mvc.util.CollectionUtils;
import com.toy.mvc.util.PathUtil;
import com.toy.mvc.wrapper.Request;
import com.toy.mvc.wrapper.Response;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 框架的主filter
 * Created by hongyb on 2017/9/23.
 */
public class MVCFilter implements Filter {
    private ServletContext servletContext = null ;
    private RouteMatcher routeMatcher = new RouteMatcher() ;
    public void init(FilterConfig filterConfig) throws ServletException {
        MVC mvc = MVC.start();
        // 初始化：加载路由
        if(!mvc.isInit()){
            String bootstrap = filterConfig.getInitParameter("bootstrap");
            // 注册控制器controller
            BootStrap bootStrap = (BootStrap) Reflect.newInstance(bootstrap);
            bootStrap.init(mvc);
            List<Route> routes = mvc.getRoutes();
            if(CollectionUtils.isNotEmpty(routes)){
                routeMatcher.setRoutes(routes);
            }
            servletContext = filterConfig.getServletContext();
            mvc.setInit(true);
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置字符集
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(Const.DEFAULT_CODING);
        response.setCharacterEncoding(Const.DEFAULT_CODING);
        // 获取请求uri
        String uri = PathUtil.getRelativePath(request);
        Route route = routeMatcher.findRoute(uri);

        if(route != null ){
            // 执行
            handle(request,response,route);
        }else{
            filterChain.doFilter(request,response);
        }

    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Route route) {
        // 保存上下文
        MyContext.initContext(servletContext,request,response);
        // 封装请求
        Request request1 = new Request(request);
        Response response1 = new Response(response);

        Object controller = route.getController();
        Method action = route.getAction();
        // 执行
        executeAction(controller,action,request1,response1);
    }

    /**
     * 执行action
     * @param controller
     * @param action
     * @param request1
     * @param response1
     */
    private Object executeAction(Object controller, Method action, Request request1, Response response1) {
        int length = action.getParameterTypes().length;
        if(length >0 ){//有参数action
            // 尝试注入request response
            Object[] args = getArgs(request1, response1, action.getParameterTypes());
            return Reflect.invoke(controller,action,args);
        }else{ //无参数action
            return Reflect.invoke(controller,action);
        }
    }

    private Object[] getArgs(Request request1, Response response1, Class<?>[] parameterTypes) {
        int len = parameterTypes.length;
        Object[] args = new Object[len];
        for (int i = 0; i < len ; i++) {
            if(parameterTypes[i].getName().equals(Request.class.getName())){
                args[i]=request1;
            }
            if(parameterTypes[i].getName().equals(Response.class.getName())){
                args[i]=response1;
            }
        }
        return args ;
    }

    public void destroy() {

    }
}
