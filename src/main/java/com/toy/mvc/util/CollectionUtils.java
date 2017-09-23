package com.toy.mvc.util;

import java.util.Collection;

/**
 * Created by Administrator on 2017/9/23.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection collection){
        if(null == collection || collection.isEmpty()){
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(Collection collection){
        if(null != collection && !collection.isEmpty()){
            return true;
        }
        return false;
    }
}
