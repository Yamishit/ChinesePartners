package com.clannad.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.clannad.R;
import com.clannad.common.base.BaseFragment;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by F_ck on 2016/9/28.
 */

public class SecFragment extends BaseFragment {

    private static final String DEFAULT_IP = "your_current_IP";
    private static String CURRENT_IP = DEFAULT_IP; // your_current_IP
    private static final String WEEX_INDEX_URL = "http://" + CURRENT_IP + ":12580/examples/build/index.js";
    @Bind(R.id.viewGroup)
    RelativeLayout viewGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weex_layout;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        WXSDKInstance mInstance = new WXSDKInstance(getActivity());
        mInstance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                try{
                    //会报nullpoint
                    viewGroup.addView(view);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        mInstance.render("WeexQuickStart",
                WXFileUtils.loadAsset("tech_list.js", getActivity()),
                null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
//        renderPage(mInstance, getActivity().getPackageName(),
//                WXFileUtils.loadFileContent("hello.js", getActivity()), WEEX_INDEX_URL, null);
    }


    protected void renderPage(WXSDKInstance mInstance, String packageName, String template, String source, String jsonInitData) {
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, source);
        mInstance.render(
                packageName,
                template,
                options,
                jsonInitData,
                WXViewUtils.getScreenWidth(getActivity()),
                WXViewUtils.getScreenHeight(getActivity()),
                WXRenderStrategy.APPEND_ASYNC);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
