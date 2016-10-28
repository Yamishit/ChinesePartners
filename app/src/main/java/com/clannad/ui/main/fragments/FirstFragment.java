package com.clannad.ui.main.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.clannad.R;
import com.clannad.bean.PhotoGirl;
import com.clannad.common.base.BaseFragment;
import com.clannad.common.commonutils.LogUtils;
import com.clannad.common.commonutils.ToastUitl;
import com.clannad.ui.cardview.CardDataItem;
import com.clannad.ui.cardview.CardSlidePanel;
import com.clannad.ui.fragments.contract.PhotoListContract;
import com.clannad.ui.fragments.model.PhotosListModel;
import com.clannad.ui.fragments.presenter.PhotosListPresenter;
import com.clannad.widget.ZitiButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by F_ck on 2016/9/28.
 * 步骤一:UI实现View方法,引用presenter
 * 步骤二:Presenter调用Model,走Model具体逻辑
 * 步骤三:Model逻辑实现,回调Presenter方法
 * 步骤四:Presenter回调View,即回到UI,回调View方法,将处理好得多数据传给View
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})

public class FirstFragment extends BaseFragment<PhotosListPresenter,PhotosListModel> implements PhotoListContract.View {




    private CardSlidePanel.CardSwitchListener cardSwitchListener;

    private String imagePaths[] = {"assets://wall01.jpeg",
            "assets://wall02.jpeg", "assets://wall03.jpeg",
            "assets://wall04.jpeg", "assets://wall05.jpeg",
            "assets://wall06.jpeg", "assets://wall07.jpeg",
            "assets://wall08.jpeg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg",
            "assets://wall12.jpg", "assets://wall01.jpg",
            "assets://wall02.jpg", "assets://wall03.jpg",
            "assets://wall04.jpg", "assets://wall05.jpg",
            "assets://wall06.jpg", "assets://wall07.jpg",
            "assets://wall08.jpg", "assets://wall09.jpg",
            "assets://wall10.jpg", "assets://wall11.jpg", "assets://wall12.jpg"}; // 24个图片资源名称

    private String names[] = {"", "爱犬邵康", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", ""}; // 24个人名

    private List<CardDataItem> dataList = new ArrayList<CardDataItem>();


    @Override
    protected int getLayoutId() {
        return R.layout.card_layout;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        CardSlidePanel slidePanel = (CardSlidePanel) rootView
                .findViewById(R.id.image_slide_panel);
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.d("CardFragment", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("CardFragment", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
            }

            @Override
            public void onItemClick(View cardView, int index) {
                Log.d("CardFragment", "卡片点击-" + dataList.get(index).userName);
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);

        prepareDataList();
        slidePanel.fillData(dataList);
    }
    private void prepareDataList() {
        int num = imagePaths.length;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < num; i++) {
                CardDataItem dataItem = new CardDataItem();
                dataItem.userName = names[i];
                dataItem.imagePath = imagePaths[i];
                dataItem.likeNum = (int) (Math.random() * 10);
                dataItem.imageNum = (int) (Math.random() * 6);
                dataList.add(dataItem);
            }
        }
    }

//    @OnClick({R.id.id_firsttab_reqbt})
//    public void onClick(View view){
//        Intent intent = null;
//        switch (view.getId()){
//            case R.id.id_firsttab_reqbt:
////                mPresenter.getPhotosListDataRequest(SIZE, mStartPage);
//                break;
//
//
//        }
//    }

    @Override
    public void returnPhotosListData(List<PhotoGirl> photoGirls) {
        ToastUitl.show(photoGirls.get(0).getDesc(),0);
        LogUtils.logd(photoGirls.toString());
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.show(msg,0);
    }
}
