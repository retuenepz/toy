package com.toy.mvc.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理路径工具
 * Created by Administrator on 2017/9/27.
 */
public class PathUtil {
    public static final String VAR_REGEXP = ":(\\w+)";
    public static final String VAR_REPLACE = "([^#/?]+)";
    private static final String SLAH = "/";
    public static String getRelativePath(HttpServletRequest request){
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        path = path.substring(contextPath.length());

        if(path.length()>0){
            path = path.substring(1);
        }
        if(!path.startsWith(SLAH)){
            path = SLAH + path;
        }
        return path;
    }

    /**
     * 处理path  让他符合标准。以/开头 不以/结尾
     * @param path
     * @return
     */
    public static String fixPath(String path) {
        if(path == null ){
            return SLAH;
        }
        if(!path.startsWith(SLAH)){
            path = SLAH + path;
        }
        if(path.length()>1 && path.endsWith(SLAH)){
            path = path.substring(0,path.length()-1);
        }
        return path;
    }
}
