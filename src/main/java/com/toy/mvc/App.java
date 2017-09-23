package com.toy.mvc;

import com.toy.mvc.controller.IndexController;

/**
 * Created by Administrator on 2017/9/23.
 */
public class App implements BootStrap {
    public void init(MVC mvc) {
        IndexController index = new IndexController();
        mvc.addRoute("/","index",index);
    }
}
