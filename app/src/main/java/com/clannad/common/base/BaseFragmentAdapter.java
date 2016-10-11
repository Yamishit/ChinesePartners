package com.clannad.common.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.clannad.common.commonutils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F_ck on 2016/10/11.
 */

public class BaseFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitles;

    public BaseFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList){
        this(fm,fragmentList,null);
    }

    public BaseFragmentAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> mTitles){
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return !CollectionUtils.isNullOrEmpty(mTitles) ? mTitles.get(position) : "";
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
