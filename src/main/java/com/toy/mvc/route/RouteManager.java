package com.toy.mvc.route;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *  路由管理器，管理所有路由
 * Created by Administrator on 2017/9/23.
 */
public class RouteManager {
    /**
     * 存放所有的路由
     */
    private List<Route> routes = new ArrayList<Route>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(String path, Method method,Object controller){
        Route route = new Route();
        route.setPath(path);
        route.setAction(method);
        route.setController(controller);
        routes.add(route);
    }
}
