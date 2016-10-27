package com.clannad.widget.sidemenu;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.clannad.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by F_ck on 2016/10/27.
 */
public class ClannadSideMenu<T extends Resourceble> {

    private List<T> items;
    private ViewGroup container;
    private Activity context;
    private List<View> views;
    private static final int ANIMATION_DURATION = 200;
    private OnSideMenuClickListener listener;

    public ClannadSideMenu(List<T> items, ViewGroup container, Activity context) {
        this.items = items;
        this.container = container;
        this.context = context;
        views = new ArrayList<>();
        addView();
    }

    private void addView() {
        for (int i = 0; i < items.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_side_menu,null);
            ImageView item = (ImageView) view.findViewById(R.id.id_sideitem_iv);
            item.setImageResource(items.get(i).getImageRes());
            view.setVisibility(View.GONE);
            container.addView(view);
            views.add(view);
            final int position = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.sideMenuClick(v,position);
                    }
                    closeSide();
                }
            });
        }
    }

    public void openSide() {
        setViewsClickable(false);
        double size = items.size();
        for (int i = 0; i < items.size(); i++) {
            //延迟动画
            final double position = i;
            final double delay = 3 * ANIMATION_DURATION * (position / size);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    animateView((int) position);
                    if(position == views.size() - 1){
                        setViewsClickable(true);
                    }
                }
            }, (long) delay);
        }
    }

    public void closeSide() {
        setViewsClickable(false);
        double size = views.size();
        for (int i = views.size() - 1; i >= 0; i--) {
            //延迟动画
            final double position = i;
            final double delay = 3 * ANIMATION_DURATION * (position / size);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    animateHideView((int)position);
                }
            },(long)delay);
        }
    }

    public void animateHideView(int position) {
        final View view = views.get(position);
        FilpAnimation rotation =
                new FilpAnimation(0, 90, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(rotation);
    }


    public void animateView(final int position) {
        final View view = views.get(position);
        //将view显示出来
        view.setVisibility(View.VISIBLE);
        FilpAnimation rotation =
                new FilpAnimation(90, 0, 0.0f, view.getHeight() / 2.0f);
        rotation.setDuration(ANIMATION_DURATION);
        rotation.setFillAfter(true);
        //在动画开始的地方速率改变比较慢，然后开始加速
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //移除animation对view点击事件等的影响
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(rotation);
    }

    public void setViewsClickable(boolean clickable) {
        for (View view : views) {
            view.setEnabled(clickable);
        }
    }

    public void setOnSideMenuClickListener(OnSideMenuClickListener listener){
        this.listener = listener;
    }

    public interface OnSideMenuClickListener{
        public void sideMenuClick(View v,int pos);
    }

}
