package com.clannad.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clannad.R;

/**
 * Created by F_ck on 2016/9/28.
 */

public class ThirdFragment extends Fragment {

    public void onActivityCreated(Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        return paramLayoutInflater.inflate(R.layout.fragment_third_tab, paramViewGroup, false);
    }

    //复活啦,一些View在activity中的又可以重新赋值啦,比如换标题
    public void onResume() {
        super.onResume();
    }

}
