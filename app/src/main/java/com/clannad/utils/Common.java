package com.clannad.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.clannad.MainActivity;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


/**
 * Created by Sk on 2016/3/8.
 * 封装一些通用方法
 */
public class Common {
    private static Common instance;
    //字体样式  在初始化的时候设置好字体
    public Typeface TYPE_FACE = null;
    //activity集合
    private static ArrayList<Activity> activityList = null;
    private MainActivity mainActivity = null;


    private static volatile Common sInst = null;  // <<< 这里添加了 volatile

    public static Common getInstance() {
        Common inst = sInst;  // <<< 在这里创建临时变量
        if (inst == null) {
            synchronized (Common.class) {
                inst = sInst;
                if (inst == null) {
                    inst = new Common();
                    sInst = inst;
                }
            }
        }
        return inst;  // <<< 注意这里返回的是临时变量
    }

    public static ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void addToActivityList(Activity activity) {
        if (activityList == null) {
            activityList = new ArrayList<Activity>();
        }

        if (activity != mainActivity) {
            activityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        try {
            activityList.remove(activity);
        } catch (Exception e) {

        }
    }

    public void finishAll() {
        if (activityList != null && activityList.size() > 0) {
            for (int i = 0; i < activityList.size(); i++) {
                try {
                    activityList.get(i).finish();
                } catch (Exception e) {

                }
            }
            activityList.clear();
            activityList = null;
        }
    }

    public void setMainActivity(MainActivity ac) {
        mainActivity = ac;
    }

    // 判断app是否被结束
    public boolean isAppExist() {
        if (mainActivity != null) {
            return true;
        } else {
            return false;
        }
    }

    long lastClickTime = 0;

    //判断是否重复点击
    public boolean isNotFastClick() {
        long curTime = System.currentTimeMillis();
        if (curTime - lastClickTime >= 500) {
            lastClickTime = curTime;
            return true;
        } else {
            return false;
        }
    }

    /**
     * fresco
     * @param
     * @param imgUrl
     */
//    public void setImg(SimpleDraweeView draweeView, String imgUrl) {
//        Uri uri = Uri.parse(imgUrl);
//        draweeView.setImageURI(uri);
//    }

    public void glideImg(Activity activity, String imgUrl, int defultImg, ImageView imageView) {
        Glide.with(activity).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void glideImg(Fragment fragment, String imgUrl, int defultImg, ImageView imageView) {
        Glide.with(fragment).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }


    public void glideCirleImg(Activity activity, String imgUrl, int defultImg, ImageView imageView) {
        Glide.with(activity).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(activity))
                .into(imageView);
    }

    public void glideCirleImg(Fragment fragment, Context context, String imgUrl, int defultImg, ImageView imageView) {
        Glide.with(fragment).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    public void glideRoundImg(Activity activity, String imgUrl, int defultImg, ImageView imageView) {
        Glide.with(activity).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(activity))
                .into(imageView);
    }

    public void glideRoundImg(Fragment fragment, String imgUrl, int defultImg, ImageView imageView, Context context) {
        Glide.with(fragment).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(context))
                .into(imageView);
    }

    public void glideRoundImgWithRadius(Activity activity, String imgUrl, int defultImg, ImageView imageView, int radius) {
        Glide.with(activity).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(activity, radius))
                .into(imageView);
    }

    public void glideRoundImgWithRadius(Fragment fragment, Context context, String imgUrl, int defultImg, ImageView imageView, int radius) {
        Glide.with(fragment).load(imgUrl)
                .placeholder(defultImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundTransform(context, radius))
                .into(imageView);
    }

    /**
     * 获取缓存地址
     * 当SD卡存在或者SD卡不可被移除的时候，
     * 就调用getExternalCacheDir()方法来获取缓存路径，
     * 否则就调用getCacheDir()方法来获取缓存路径。
     * 前者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
     * 而后者获取到的是 /data/data/<application package>/cache
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取到当前应用程序的版本号
     * 每当版本号改变，缓存路径下存储的所有数据都会被清除掉，
     * 因为DiskLruCache认为当应用程序有版本更新的时候，所有的数据都应该从网上重新获取。
     *
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * MD5
     *
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
