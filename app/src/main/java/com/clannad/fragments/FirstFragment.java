package com.clannad.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clannad.R;
import com.clannad.ui.base.BaseFragment;

/**
 * Created by F_ck on 2016/9/28.
 */

public class FirstFragment extends BaseFragment {

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.fragment_first_tab;
    }
}
