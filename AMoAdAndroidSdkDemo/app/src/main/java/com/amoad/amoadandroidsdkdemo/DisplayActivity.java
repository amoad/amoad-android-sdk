package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdBuildConfig;
import com.amoad.AMoAdView;
import com.amoad.AdCallback;

public class DisplayActivity extends HomeButtonActivity implements View.OnClickListener, AdCallback {
    private AMoAdView mAdView;
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mSid = getIntent().getStringExtra("sid");
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_hide).setOnClickListener(this);
        initAd();
    }

    private void initAd() {
        mAdView = new AMoAdView(this);
        mAdView.setCallback(this);
        //mAdView.setNetworkTimeoutMillis(5000);
        //mAdView.setClickTransition(AMoAdView.ClickTransition.NONE);
        //mAdView.setRotateTransition(AMoAdView.RotateTransition.ALPHA);
        mAdView.setResponsiveStyle(true);
        mAdView.setSid(mSid);

        ViewGroup adLayout = (ViewGroup) findViewById(R.id.ad_layout);
        adLayout.addView(mAdView);
    }

    private void showAd() {
        mAdView.setVisibility(View.VISIBLE);
    }

    private void hideAd() {
        mAdView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hide:
                hideAd();
                break;
            case R.id.btn_show:
                showAd();
        }
    }

    @Override
    public void didFailToReceiveAdWithError() {
        //広告取得の失敗
    }

    @Override
    public void didReceiveEmptyAd() {
        //空広告
    }

    @Override
    public void didReceiveAd() {
        //広告取得の成功
    }
}
