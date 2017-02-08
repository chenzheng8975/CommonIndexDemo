package com.czhappy.commonindexdemo.utils;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/8/29 0029
 * Time: 17:26
 */
public class Api {

    public static String SUCCESS = "1";

    //服务器地址
    public static String DOMAIN_URL = "http://121.42.53.175:8080/kuaizhi/";
    //public static String DOMAIN_URL = "http://192.168.168.171:8080/kuaizhi/";

    //活动列表
    public static String GET_CAMPAIGN_LIST = DOMAIN_URL + "app/campaign/list";
    //首页轮播图
    public static String GET_INDEX_BANNER_LIST = DOMAIN_URL + "app/banner/list";
}
