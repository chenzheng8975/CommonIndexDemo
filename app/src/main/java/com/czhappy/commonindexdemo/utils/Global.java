package com.czhappy.commonindexdemo.utils;


import com.czhappy.commonindexdemo.model.Dictionary;
import com.czhappy.commonindexdemo.model.User;

import java.util.List;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/9/12 0012
 * Time: 10:36
 */
public class Global {
    //项目渠道号
    public static String APP_CHANNEL;
    //手机串号
    public static String DEVICE_ID;
    //app版本号
    public static String VERSION_CODE;
    //app版本
    public static String VERSION_INFO;

    public static User user;

    // 网络连接标识
    public static boolean IS_NETWORK_CONTECT = false;

    //活动分类字典列表
    public static List<Dictionary> categoryDictionaryList;


}
