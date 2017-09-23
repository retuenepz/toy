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
}
