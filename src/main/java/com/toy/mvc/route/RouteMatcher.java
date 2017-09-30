package com.toy.mvc.route;

import com.alibaba.fastjson.JSON;
import com.toy.mvc.util.PathUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 路由匹配器
 * Created by    on 2017/9/23.
 */
public class RouteMatcher {
    private List<Route> routes = new ArrayList<Route>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route findRoute(String path) {
        String cleanPath = parsePath(path);
        List<Route> matchRoutes = new ArrayList<Route>(); // 匹配结果集
        for (Route route : routes) {
            if(matchPath(route.getPath(),cleanPath)){
                matchRoutes.add(route);
            }
        }
        //优先匹配原则
        giveMatch(path,matchRoutes);
        return matchRoutes.size()>0 ? matchRoutes.get(0):null ;
    }

    /**
     * 选择最佳路由
     * @param uri
     * @param routes
     */
    private void giveMatch(final String uri, List<Route> routes) {
        Collections.sort(routes, new Comparator<Route>() {
            public int compare(Route o1, Route o2) {
                if (o2.getPath().equals(uri)) {
                    return o2.getPath().indexOf(uri);
                }
                return -1;
            }
        });
    }
    private boolean matchPath(String routePath, String pathToMatch) {
        routePath = routePath.replaceAll(PathUtil.VAR_REGEXP, PathUtil.VAR_REPLACE);
        return pathToMatch.matches("(?i)" + routePath);
    }

    private String parsePath(String path) {
        path = PathUtil.fixPath(path);
        try {
            URI uri = new URI(path);
            return uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
