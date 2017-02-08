package com.czhappy.commonindexdemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: chenzheng
 * Date: 2017/1/18 0018
 * Time: 17:28
 */
public class ActivityCollector {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);

    }

    public static void removeActivity(Activity activity){
        activityList.remove(activity);

    }

    public static Activity getTopActivity(){
        if(activityList.isEmpty()){
            return null;
        }else{
            //获取栈顶Activity
            return activityList.get(activityList.size()-1);
        }
    }
}
