package com.kb260.gxdk.utils;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kb260.gxdk.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author  KB260
 * Created on  2017/11/2
 */
public class ImageLoader {

    public static void showImage(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.drawable.user)
                //.placeholder(R.color.text_green)//加载中
                .fallback(R.drawable.user)//url为空
                .into(imageView);

    };

    public static void showImage(CircleImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.drawable.user)
                //.placeholder(R.color.text_green)//加载中
                .fallback(R.drawable.user)//url为空
                .into(imageView);

    };

    public static void showImage(ImageView view, int drawable){
        Glide.with(view.getContext())
                .load(drawable)
                .error(R.drawable.ic_arrow_drop_down_white_24dp)
                //.placeholder(R.color.text_green)//加载中
                .fallback(R.drawable.ic_arrow_drop_down_white_24dp)//url为空
                .into(view);
    };

}
