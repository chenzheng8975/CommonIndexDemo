package com.czhappy.commonindexdemo.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.czhappy.commonindexdemo.R;

import java.io.File;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/8/31 0031
 * Time: 15:43
 */
public class GlideImgManager {

    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        if(context!=null) {
            //原生 API
            Glide.with(context).load(url).placeholder(emptyImg).error(erroImg).into(iv);
        }
    }

    public static void loadImage(Context context, String url, ImageView iv) {
        if(context!=null) {
            //原生 API
            Glide.with(context).load(url).crossFade().placeholder(R.drawable.empty_photo).error(R.drawable.empty_photo).into(iv);
        }
    }

    public static void loadGifImage(Context context, String url, ImageView iv) {
        if(context!=null) {
            Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.empty_photo).error(R.drawable.empty_photo).into(iv);
        }
    }


    public static void loadCircleImage(Context context, String url, ImageView iv) {
        if(context!=null) {
            Glide.with(context).load(url).placeholder(R.drawable.empty_photo).error(R.drawable.empty_photo).transform(new GlideCircleTransform(context)).into(iv);
        }
    }

    public static void loadRoundCornerImage(Context context, String url, ImageView iv) {
        if(context!=null) {
            Glide.with(context).load(url).placeholder(R.drawable.empty_photo).error(R.drawable.empty_photo).transform(new GlideRoundTransform(context, 10)).into(iv);
        }
    }


    public static void loadImage(Context context, final File file, final ImageView imageView) {
        if(context!=null) {
            Glide.with(context)
                    .load(file)
                    .into(imageView);
        }
    }

    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        if(context!=null) {
            Glide.with(context)
                    .load(resourceId)
                    .into(imageView);
        }
    }
}