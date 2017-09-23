package com.toy.mvc.wrapper;

import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletResponse 增强
 * Created by Administrator on 2017/9/23.
 */
public class Response {
    private HttpServletResponse response ;

    public Response(HttpServletResponse response) {
        this.response = response;
    }
}
