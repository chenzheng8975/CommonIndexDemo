package com.czhappy.commonindexdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.czhappy.commonindexdemo.utils.ActivityCollector;
import com.czhappy.commonindexdemo.utils.LogUtil;
import com.czhappy.commonindexdemo.utils.NetUtil;
import com.czhappy.commonindexdemo.utils.ToastUtil;
import com.lzy.ninegrid.NineGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import okhttp3.Call;

/**
 * Description:
 * User: chenzheng
 * Date: 2017/1/18 0018
 * Time: 16:38
 */
public class KuaiZhiApplication extends Application{

    public static KuaiZhiApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkHttpUtils")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(20000)  //全局的连接超时时间
                    .setReadTimeOut(20000)     //全局的读取超时时间
                    .setWriteTimeOut(20000)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认就是Default,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                    .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore());        //cookie持久化存储，如果cookie不过期，则一直有效

        } catch (Exception e) {
            e.printStackTrace();
        }

        NineGridView.setImageLoader(new GlideImageLoader());
    }

    /** Glide 加载 */
    private class GlideImageLoader implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.empty_photo)//
                    .error(R.drawable.empty_photo)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    public void exit() {
        for (Activity activity : ActivityCollector.activityList) {
            activity.finish();
        }
        ActivityCollector.activityList.clear();
        System.exit(0);
    }

    public static KuaiZhiApplication getInstance() {
        return instance;
    }

    public static void showResultToast(Context context, Call call, Exception e) {
        LogUtil.e("请求失败  请求方式：" + call.request().method() + "\n" + "url：" + call.request().url());
        LogUtil.e("异常信息："+ e.getMessage());
        if(!NetUtil.isHasNet(context)){
            ToastUtil.show(context, "当前网络不可用，请检查网络设置");
            return;
        }
        if(call.isCanceled()){
            ToastUtil.show(context, "已取消请求");
            return;
        }
        ToastUtil.show(context, "服务器响应异常，请稍后再访问");
    }
}
