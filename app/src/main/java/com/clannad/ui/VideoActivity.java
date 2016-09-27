package com.clannad.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.clannad.MainActivity;
import com.clannad.R;
import com.clannad.ui.base.BaseActivity;
import com.clannad.widget.CustomVideoView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by F_ck on 2016/9/27.
 */

public class VideoActivity extends BaseActivity {


    @Bind(R.id.id_splash_videoview)
    CustomVideoView id_splash_videoview;
    @Bind(R.id.id_splash_start)
    Button id_splash_start;

    @Override
    public int setContetId() {
        return R.layout.activity_splash_video;
    }

    @Override
    public void initData() {
        //设置播放加载路径
        id_splash_videoview.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.media));
        //播放
        id_splash_videoview.start();
        //循环播放
        id_splash_videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                id_splash_videoview.start();
            }
        });
    }

    @OnClick({R.id.id_splash_start})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_splash_start:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }



    @Override
    public void getRxMsg() {

    }
}
