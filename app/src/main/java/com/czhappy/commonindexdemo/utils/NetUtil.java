package com.czhappy.commonindexdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/8/31 0031
 * Time: 10:47
 */
public class NetUtil {

    /**
     * 检查是否有可用网络
     *
     * @param context
     *            上下文环境
     * @return 有可用网络返回true 否则返回false
     */
    public static boolean isHasNet(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();// 获取联网状态网络
        if (info == null || !info.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }
}
