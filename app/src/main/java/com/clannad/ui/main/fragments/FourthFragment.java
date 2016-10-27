package com.clannad.ui.main.fragments;

import android.view.View;
import android.widget.LinearLayout;

import com.clannad.R;
import com.clannad.common.base.BaseFragment;
import com.clannad.common.commonutils.ToastUitl;
import com.clannad.widget.ZitiButton;
import com.clannad.widget.sidemenu.SlideMenuItem;
import com.clannad.widget.sidemenu.ClannadSideMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by F_ck on 2016/9/28.
 */

public class FourthFragment extends BaseFragment implements ClannadSideMenu.OnSideMenuClickListener {


    @Bind(R.id.id_side_menull)
    LinearLayout id_side_menull;
    @Bind(R.id.id_side_openside)
    ZitiButton id_side_openside;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fourth_tab;
    }

    private List<SlideMenuItem> items;
    private ClannadSideMenu clannadSideMenu;

    @Override
    protected void initView() {
        items = new ArrayList<>();
        SlideMenuItem menuItem0 = new SlideMenuItem("1", R.drawable.icn_close);
        items.add(menuItem0);
        SlideMenuItem menuItem1 = new SlideMenuItem("2", R.drawable.icn_1);
        items.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem("3", R.drawable.icn_2);
        items.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem("4", R.drawable.icn_3);
        items.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem("5", R.drawable.icn_4);
        items.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem("6", R.drawable.icn_5);
        items.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem("7", R.drawable.icn_6);
        items.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem("8", R.drawable.icn_7);
        items.add(menuItem7);
        clannadSideMenu = new ClannadSideMenu(items, id_side_menull, getActivity());
        clannadSideMenu.setOnSideMenuClickListener(this);
    }

    @OnClick({R.id.id_side_openside})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_side_openside:
                clannadSideMenu.openSide();
                break;

        }
    }

    @Override
    public void sideMenuClick(View v, int pos) {
        ToastUitl.showShort("高烽傻逼" + pos);
    }


    @Override
    protected void initPresenter() {

    }


}
