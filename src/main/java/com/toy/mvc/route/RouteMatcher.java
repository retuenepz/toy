package com.toy.mvc.route;

import java.util.List;

/**
 * 路由匹配器
 * Created by Administrator on 2017/9/23.
 */
public class RouteMatcher {
    private List<Route> routes ;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
