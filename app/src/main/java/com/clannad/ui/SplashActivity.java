package com.clannad.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.clannad.R;
import com.clannad.common.base.BaseActivity;
import com.clannad.ui.main.activity.MainActivity;
import com.clannad.utils.AnimationUtil;
import com.clannad.utils.StatusBarUtil;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/4/28.
 */
public class SplashActivity extends BaseActivity {

    private static final long ANIM_DURATION = 2000;
    @Bind(R.id.splash_view)
    ImageView splash_view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        //设置状态栏透明
        StatusBarUtil.setTranslucentBackground(this);
        //开始执行动画,开始跳转
        startScaleAnimation();
    }

    private void startScaleAnimation() {
        /** 设置位移动画 向右位移150 */
        ScaleAnimation animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(ANIM_DURATION);//设置动画持续时间
        animation.setFillAfter(true);
        splash_view.setAnimation(animation);
        animation.startNow();
        AnimationUtil.setAnimationListener(animation, new AnimationUtil.AnimListener() {
            @Override
            public void onAnimFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
//                LoginActivity.start(SplashActivity.this);
            }
        });
    }


}
