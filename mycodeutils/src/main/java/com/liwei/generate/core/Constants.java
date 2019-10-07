package com.liwei.generate.core;

import sun.plugin.PluginURLJarFileCallBack;

import java.net.URL;

/**
 * @Author: li.wei
 * @Date: 2019/9/8 17:01
 * @Version
 */
public class Constants {
    public static URL resources = Constants.class.getResource("/");
    public static  String IMG =resources +"img/";
    public static  String CSS =resources +"css/";
    public static String TEMPLATEDIR = resources + "template/";
    public static String TITILE = "代码生成器";

}
