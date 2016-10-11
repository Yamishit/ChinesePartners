package com.clannad.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.clannad.R;
import com.clannad.common.base.BaseActivity;
import com.clannad.ui.main.activity.MainActivity;
import com.clannad.widget.CustomVideoView;

import butterknife.Bind;
import butterknife.OnClick;

import static android.animation.ObjectAnimator.ofFloat;

/**
 * Created by F_ck on 2016/9/27.
 */

public class VideoActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {


    @Bind(R.id.id_splash_videoview)
    CustomVideoView id_splash_videoview;
    @Bind(R.id.id_splash_start)
    Button id_splash_start;
    @Bind(R.id.id_splash_view)
    ImageView id_splash_view;

    private boolean isFirst = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        SetTranslanteBar();
        //设置播放加载路径
        id_splash_videoview.setVideoURI(Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.mishi));
        //播放
        id_splash_videoview.start();
        //循环播放
        id_splash_videoview.setOnCompletionListener(this);
    }


    @OnClick({R.id.id_splash_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_splash_start:
                id_splash_videoview.stopPlayback();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (id_splash_videoview != null) {
            id_splash_videoview.stopPlayback();
            id_splash_videoview = null;
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        startAni(id_splash_view);
        ObjectAnimator.ofFloat(id_splash_start, "alpha", 0F, 1F).setDuration(1000).start();
    }

    /**
     * 插值器
     * AccelerateDecelerateInterpolator 在动画开始与结束的地方速率改变比较慢，在中间的时候加速
     * AccelerateInterpolator  在动画开始的地方速率改变比较慢，然后开始加速
     * AnticipateInterpolator 开始的时候向后然后向前甩
     * AnticipateOvershootInterpolator 开始的时候向后然后向前甩一定值后返回最后的值
     * BounceInterpolator   动画结束的时候弹起
     * CycleInterpolator 动画循环播放特定的次数，速率改变沿着正弦曲线
     * DecelerateInterpolator 在动画开始的地方快然后慢
     * LinearInterpolator   以常量速率改变
     * OvershootInterpolator    向前甩一定值后再回到原来位置
     */
    //在动画开始的地方速率改变比较慢，然后开始加速
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    //向前甩一定值后再回到原来位置
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private void startAni(View view) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator alphaAni = ObjectAnimator.ofFloat(view, "alpha", 0F, 1F);
        alphaAni.setDuration(1000);
        alphaAni.setInterpolator(ACCELERATE_INTERPOLATOR);

        ObjectAnimator bounceAnimX = ofFloat(view, "scaleX", 0.2f, 1f);
        bounceAnimX.setDuration(1800);
        bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);


        ObjectAnimator bounceAnimY = ofFloat(view, "scaleY", 0.2f, 1f);
        bounceAnimY.setDuration(1800);
        bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
        bounceAnimY.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始
            }
        });
        animatorSet.play(alphaAni).with(bounceAnimX).with(bounceAnimY);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //组合动画结束
            }
        });
        animatorSet.start();
    }
}
