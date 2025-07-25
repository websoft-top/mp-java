package com.gxwebsoft.common.core.utils;

import com.gxwebsoft.cms.entity.CmsNavigation;

public class SpmUtil {

  // 生成spmUrl
  public static String getSpmUrl(String path){
    return path.concat("?spm=c.");
  }

  // 生成spmUrl
  public static String getSpmUrl(String path, CmsNavigation navigation){
    return path.concat("?spm=c.".concat(navigation.getNavigationId().toString()));
  }

  // 生成spmUrl
//  public static <T>  String getSpmUrl(String path, T data){
//    System.out.println("json = " + data);
//    return "?spm=".concat(path).concat();
//  }

}
