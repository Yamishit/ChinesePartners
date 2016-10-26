package com.clannad.yalantis;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by F_ck on 2016/10/26.
 */

public class ViewAnimator<T extends Resourceble> {
    private final int ANIMATION_DURATION = 175;
    public static final int CIRCULAR_REVEAL_ANIMATION_DURATION = 500;

    private Activity activity;
    private List<T> list;
    private List<View> viewList = new ArrayList<>();
    private ScreenShotable screenShotable;
    private DrawerLayout drawerLayout;
    private ViewAnimatorListener animatorListener;

    public ViewAnimator(Activity activity,
                        List<T> items,
                        ScreenShotable screenShotable,
                        final DrawerLayout drawerLayout,
                        ViewAnimatorListener animatorListener){
        this.activity = activity;
        this.list = items;
        this.screenShotable = screenShotable;
        this.drawerLayout = drawerLayout;
        this.animatorListener = animatorListener;
    }

    public void showMenuContent(){

    }



    public interface ViewAnimatorListener{
        public ScreenShotable onSwitch(Resourceble sideMenuItem,ScreenShotable screenShotable,int position);
        public void disableHomeButton();
        public void enableHomeButton();
        public void addViewToContainer(View view);
    }


}
