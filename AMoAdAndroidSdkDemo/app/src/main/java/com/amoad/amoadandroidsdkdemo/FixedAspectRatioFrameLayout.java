package com.amoad.amoadandroidsdkdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class FixedAspectRatioFrameLayout extends FrameLayout {

    private float mWidthRatio;
    private float mHeightRatio;

    public FixedAspectRatioFrameLayout(Context context) {
        this(context, null);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioFrameLayout);

        String[] aspectRatio = a.getString(R.styleable.FixedAspectRatioFrameLayout_aspect_ratio).split(":");
        mWidthRatio  = Float.parseFloat(aspectRatio[0]);
        mHeightRatio = Float.parseFloat(aspectRatio[1]);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode  = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            int height = (int) (mHeightRatio * MeasureSpec.getSize(widthMeasureSpec) / mWidthRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            int width = (int) (mWidthRatio * MeasureSpec.getSize(heightMeasureSpec) / mHeightRatio);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
