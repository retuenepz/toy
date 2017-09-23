package com.toy.mvc;

import com.toy.mvc.route.Route;
import com.toy.mvc.route.RouteMatcher;
import com.toy.mvc.util.CollectionUtils;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * 框架的主filter
 * Created by hongyb on 2017/9/23.
 */
public class MVCFilter implements Filter {
    private ServletContext servletContext = null ;
    private RouteMatcher routeMatcher = null ;
    public void init(FilterConfig filterConfig) throws ServletException {
        MVC mvc = MVC.start();
        // 初始化：加载路由
        if(!mvc.isInit()){
            String bootstrap = filterConfig.getInitParameter("bootstrap");
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

    }

    public void destroy() {

    }
}
