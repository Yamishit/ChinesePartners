package com.clannad.common.base;

/**
 * Created by F_ck on 2016/10/11.
 */

public interface BaseView {
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
