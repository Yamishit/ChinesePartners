package com.clannad;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.clannad.fragments.FirstFragment;
import com.clannad.fragments.FourthFragment;
import com.clannad.fragments.SecFragment;
import com.clannad.fragments.ThirdFragment;
import com.clannad.ui.VideoActivity;
import com.clannad.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @Bind(R.id.id_main_firsttab)
    Button id_main_firsttab;
    @Bind(R.id.id_main_sectab)
    Button id_main_sectab;
    @Bind(R.id.id_main_thirdtab)
    Button id_main_thirdtab;
    @Bind(R.id.id_main_fourthtab)
    Button id_main_fourthtab;
    @Bind(R.id.id_main_frame)
    FrameLayout id_main_frame;

    private Fragment firstFragment;
    private Fragment secFragment;
    private Fragment thirdFragment;
    private Fragment fourthFragment;

    @Override
    public int setContetId() {
        return R.layout.activity_main;
    }

    @Override
    public void getRxMsg() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //如果发现是强杀而跳转的就执行protectApp方法
        String action = intent.getStringExtra(KEY_HOME_ACTION);
        if (ACTION_BACK_TO_HOME.equals(action)) {
            protectApp();
        }
    }

    @Override
    public void initData() {
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "mainfonts/iconfont.ttf");
        id_main_firsttab.setTypeface(iconfont);
        id_main_sectab.setTypeface(iconfont);
        id_main_thirdtab.setTypeface(iconfont);
        id_main_fourthtab.setTypeface(iconfont);
        clearTabColor();
        id_main_firsttab.setTextColor(getResources().getColor(R.color.colorAccent));
        //一进入就跳转到第一个fragment
        if (firstFragment == null) {
            firstFragment = new FirstFragment();
        }
        changeFragment(firstFragment);
    }

    //不提取clearTabColor()是因为将来主页可能会有更多的事件处理
    @OnClick({R.id.id_main_firsttab, R.id.id_main_sectab,
            R.id.id_main_thirdtab, R.id.id_main_fourthtab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_main_firsttab:
                clearTabColor();
                id_main_firsttab.setTextColor(getResources().getColor(R.color.colorAccent));
                if(firstFragment == null){
                    firstFragment = new FirstFragment();
                }
                changeFragment(firstFragment);
                break;

            case R.id.id_main_sectab:
                clearTabColor();
                id_main_sectab.setTextColor(getResources().getColor(R.color.colorAccent));
                if(secFragment == null){
                    secFragment = new SecFragment();
                }
                changeFragment(secFragment);
                break;

            case R.id.id_main_thirdtab:
                clearTabColor();
                id_main_thirdtab.setTextColor(getResources().getColor(R.color.colorAccent));
                if(thirdFragment == null){
                    thirdFragment = new ThirdFragment();
                }
                changeFragment(thirdFragment);
                break;

            case R.id.id_main_fourthtab:
                clearTabColor();
                id_main_fourthtab.setTextColor(getResources().getColor(R.color.colorAccent));
                if(fourthFragment == null){
                    fourthFragment = new FourthFragment();
                }
                changeFragment(fourthFragment);
                break;
        }
    }

    public void changeFragment(Fragment fragment) {
        if (fragment != null) {
            android.app.FragmentTransaction localFragmentTransaction =
                    getFragmentManager().beginTransaction();
            localFragmentTransaction.replace(R.id.id_main_frame, fragment);
            localFragmentTransaction.commit();
        }
    }

    private void clearTabColor() {
        id_main_firsttab.setTextColor(getResources().getColor(R.color.clannad_trans_gray));
        id_main_sectab.setTextColor(getResources().getColor(R.color.clannad_trans_gray));
        id_main_thirdtab.setTextColor(getResources().getColor(R.color.clannad_trans_gray));
        id_main_fourthtab.setTextColor(getResources().getColor(R.color.clannad_trans_gray));
    }

    @Override
    protected void protectApp() {
        //如果已经在首页并且被强杀了就不用再回首页了,而是直接跳转到引导页面,所以这里不能super。
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
        finish();
    }
}
