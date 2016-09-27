package com.clannad.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.module.GlideModule;

/**
 * 如果你对默认的RGB_565效果还比较满意，
 * 可以不做任何事，但是如果你觉得难以接受，
 * 可以创建一个新的GlideModule将Bitmap格式转换到ARGB_8888
 *
 * 同时在AndroidManifest.xml中将GlideModule定义为meta-data
 * Created by SK on 2016/3/26.
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        //默认RGB_565
//        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }



    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
        //设置自定义分辨率
//        glide.register(MyDataModel.class, InputStream.class,
//                new MyUrlLoader.Factory());
    }
}
