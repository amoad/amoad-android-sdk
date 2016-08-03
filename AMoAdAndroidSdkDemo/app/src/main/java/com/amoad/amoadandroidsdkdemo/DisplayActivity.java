package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdView;

public class DisplayActivity extends HomeButtonActivity implements View.OnClickListener {
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
        mAdView.setSid(mSid);

        ViewGroup ad = (ViewGroup) findViewById(R.id.ad);
        ad.addView(mAdView);
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
}
