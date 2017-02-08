package com.czhappy.commonindexdemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/8/29 0029
 * Time: 17:24
 */
public class Utils {

    public static final DateFormat DATE_TIME_FORMATER = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static final DateFormat DATE_FORMATER = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.CHINA);
    private static String albumPath = "/DCIM/camera";

    public static boolean isEmpty(String paramString) {
        return (paramString == null) || (paramString.trim().length() <= 0);
    }

    /**
     * 将字符串转换成整形，如果格式错误，将转成0
     *
     * @param str
     * @return
     */
    public static int toInt(String str) {
        if (Utils.isEmpty(str)) {
            return 0;
        }

        str = str.trim();
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {

        }
        return i;
    }

    /**
     * 将字符串转换成浮点型，如果格式错误，将转成0
     *
     * @param str
     * @return
     */
    public static float toFloat(String str) {
        if (Utils.isEmpty(str)) {
            return 0;
        }

        str = str.trim();
        float i = 0;
        try {
            i = Float.parseFloat(str);
        } catch (Exception e) {

        }
        return i;
    }

    /**
     * @param str
     * @return int
     * @throws
     * @author chenzheng
     * @Description: 转double
     * @since 2015-6-7
     */
    public static double toDouble(String str) {
        if (Utils.isEmpty(str)) {
            return 0;
        }

        str = str.trim();
        double i = 0;
        try {
            i = Double.parseDouble(str);
        } catch (Exception e) {

        }
        return i;
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param context
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        return DEVICE_ID;
    }

    public static String getVersionName(Context context) {
        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }

    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    /**
     * @param context
     * @return int
     * @throws
     * @author chenzheng
     * @Description: 获取屏幕宽度
     * @since 2014-5-9
     */
    public static int getScreenW(Context context) {
        return getScreenSize(context, true);
    }

    /**
     * @param context
     * @return int
     * @throws
     * @author chenzheng
     * @Description: 获取屏幕高度
     * @since 2014-5-9
     */
    public static int getScreenH(Context context) {
        return getScreenSize(context, false);
    }

    private static int getScreenSize(Context context, boolean isWidth) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return isWidth ? dm.widthPixels : dm.heightPixels;
    }

    public static String StringDateFormat(String dateString, String formatString) {
        if(isEmpty(dateString)){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        Date date = null ;
        try{
            date = sdf.parse(dateString) ;   // 将给定的字符串中的日期提取出来
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace() ;       // 打印异常信息
            return dateString;
        }
        return sdf.format(date);
    }

    /**
     * 判断是否手机号码
     *
     * @param number
     * @return
     */
    public static boolean isMobileNumber(String number) {
        number = number.trim();
        return Pattern.matches("^1\\d{10}$", number);
    }

    public static String checkTimeStatus(String startTime, String endTime){
        if(!Utils.isEmpty(startTime) && !Utils.isEmpty(endTime)) {
            String curTime = DATE_TIME_FORMATER.format(new Date());
            long longCurTime = Long.valueOf(curTime.replaceAll("[-\\s:]", ""));
            if (startTime.length() == 10) {
                long longStartTime = Long.valueOf(startTime.replaceAll("[-\\s:]", "")) * 1000000;
                long longEndTime = Long.valueOf(endTime.replaceAll("[-\\s:]", "")) * 1000000 + 235959;
                if (longCurTime <= longStartTime) {
                    return "未开始";
                } else if (longCurTime >= longStartTime && longCurTime <= longEndTime) {
                    return "进行中";
                } else {
                    return "已结束";
                }
            }else if (startTime.length() == 19) {
                long longStartTime = Long.valueOf(startTime.replaceAll("[-\\s:]", ""));
                long longEndTime = Long.valueOf(endTime.replaceAll("[-\\s:]", ""));
                if (longCurTime <= longStartTime) {
                    return "未开始";
                } else if (longCurTime >= longStartTime && longCurTime <= longEndTime) {
                    return "进行中";
                } else {
                    return "已结束";
                }
            }else{
                return "已结束";
            }
        }else{
            if(Utils.isEmpty(startTime) && Utils.isEmpty(endTime)){
                return "未开始";
            }else if(!Utils.isEmpty(startTime) && Utils.isEmpty(endTime)){
                return "进行中";
            }else{
                return "已结束";
            }
        }
    }
}
