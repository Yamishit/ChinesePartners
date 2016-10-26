package com.clannad.yalantis;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by F_ck on 2016/10/26.
 */

public class FilpAnimation extends Animation {
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private Camera mCamera;

    public FilpAnimation(float mCenterY, float mCenterX, float mToDegrees, float mFromDegrees) {
        this.mCenterY = mCenterY;
        this.mCenterX = mCenterX;
        this.mToDegrees = mToDegrees;
        this.mFromDegrees = mFromDegrees;
    }

    //初始化
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    /**
     * 第一个参数为动画的进度时间值，取值范围为[0.0f,1.0f]，
     * 第二个参数Transformation记录着动画某一帧中变形的原始数据。
     * 该方法在动画的每一帧显示过程中都会被调用。
     *
     *  在绘制动画的过程中会反复的调用applyTransformation函数，
     *  每次调用参数interpolatedTime值都会变化，该参数从0渐 变为1，
     *  当该参数为1时表明动画结束。
     *  通过参数Transformation 来获取变换的矩阵（matrix），通过改变矩阵就可以实现各种复杂的效果。
     * 实现效果
     * @param interpolatedTime
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();

        camera.rotateY(degrees);

        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
    }
}
