package com.toy.mvc.view;

import com.toy.mvc.MyContext;
import com.toy.mvc.config.Const;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * JSP
 * Created by Administrator on 2017/9/23.
 */
public class JspResolver implements ViewResolver {
    public void render(String viewName, Writer writer) {
        MyContext context = MyContext.getContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String viewPath = this.getViewPath(viewName);
        try {
            request.getRequestDispatcher(viewPath).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getViewPath(String viewName){
        String viewPath = Const.VIEW_PREFIX + "/" +viewName + Const.VIEW_SUFFIX;
        return viewPath.replaceAll("[/]+","/");
    }
}
