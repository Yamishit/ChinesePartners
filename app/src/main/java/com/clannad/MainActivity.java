package com.clannad;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.clannad.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.id_main_testtv)
    TextView id_main_testtv;
    @Bind(R.id.id_main_firsttab)
    TextView id_main_firsttab;
    @Bind(R.id.id_main_sectab)
    TextView id_main_sectab;
    @Bind(R.id.id_main_thirdtab)
    TextView id_main_thirdtab;
    @Bind(R.id.id_main_fourthtab)
    TextView id_main_fourthtab;

    @Override
    public int setContetId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        Typeface iconfont = Typeface.createFromAsset
                (getAssets(), "mainfonts/iconfont.ttf");
        id_main_firsttab.setTypeface(iconfont);
        id_main_sectab.setTypeface(iconfont);
        id_main_thirdtab.setTypeface(iconfont);
        id_main_fourthtab.setTypeface(iconfont);
        clearTabColor();
        id_main_firsttab.setTextColor(Color.GREEN);
        id_main_testtv.setText(R.string.first_tv);
    }

    @OnClick({R.id.id_main_firsttab,R.id.id_main_sectab,
            R.id.id_main_thirdtab,R.id.id_main_fourthtab})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_main_firsttab:
                clearTabColor();
                id_main_firsttab.setTextColor(Color.GREEN);
                id_main_testtv.setText(R.string.first_tv);
                break;

            case R.id.id_main_sectab:
                clearTabColor();
                id_main_sectab.setTextColor(Color.GREEN);
                id_main_testtv.setText(R.string.sec_tv);
                break;

            case R.id.id_main_thirdtab:
                clearTabColor();
                id_main_thirdtab.setTextColor(Color.GREEN);
                id_main_testtv.setText(R.string.third_tv);
                break;

            case R.id.id_main_fourthtab:
                clearTabColor();
                id_main_fourthtab.setTextColor(Color.GREEN);
                id_main_testtv.setText(R.string.fourth_tv);
                break;
        }
    }

    private void clearTabColor() {
        id_main_firsttab.setTextColor(Color.GRAY);
        id_main_sectab.setTextColor(Color.GRAY);
        id_main_thirdtab.setTextColor(Color.GRAY);
        id_main_fourthtab.setTextColor(Color.GRAY);
    }


    @Override
    public void getRxMsg() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
