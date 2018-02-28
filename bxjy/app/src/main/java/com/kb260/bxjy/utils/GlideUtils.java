package com.kb260.bxjy.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kb260.bxjy.R;

/**
 * @author KB260
 *         Created on  2018/2/26
 */

public class GlideUtils {

    public static void showImg(Context context, String url,ImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.image4)
                .error(R.drawable.image2);
                //.priority(Priority.HIGH)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }

    public static void showImgHead(Context context, String url,ImageView view,int error){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(error);
        //.priority(Priority.HIGH)
        //.diskCacheStrategy(DiskCacheStrategy.NONE)

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(view);
    }
}
