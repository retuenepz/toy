package com.toy.mvc.wrapper;

import com.toy.mvc.MVC;
import com.toy.mvc.view.ViewResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpServletResponse 增强
 * Created by Administrator on 2017/9/23.
 */
public class Response {
    private HttpServletResponse response ;
    private ViewResolver viewResolver;
    public Response(HttpServletResponse response) {
        this.response = response;
        this.viewResolver = MVC.start().getViewResolver();
    }
    public void render(String view){
        viewResolver.render(view,null);
    }
    public void redirect(String location){
        try {
            this.response.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void text(String text) {
        response.setContentType("text/plan;charset=UTF-8");
        this.print(text);
    }

    public void html(String html) {
        response.setContentType("text/html;charset=UTF-8");
        this.print(html);
    }
    private void print(String str){
        try {
            ServletOutputStream outputStream = this.response.getOutputStream();
            outputStream.write(str.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
