package com.toy.mvc.view;

import java.io.Writer;

/**
 * 视图渲染
 * Created by Administrator on 2017/9/23.
 */
public interface ViewResolver {
    public void render(String viewName, Writer writer);
}
