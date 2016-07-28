package com.amoad.amoadandroidsdkdemo;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.amoad.AMoAdError;
import com.amoad.AdLoadListener;
import com.amoad.AdResult;
import com.amoad.InterstitialAd;

public class InterstitialActivity extends HomeButtonActivity implements View.OnClickListener {
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mSid = getIntent().getStringExtra("sid");
        findViewById(R.id.btn_show).setOnClickListener(this);
        initAd();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        InterstitialAd.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InterstitialAd.close(mSid);
    }

    private void initAd() {
        InterstitialAd.setPortraitPanel(mSid, R.drawable.amoad_panel);
        InterstitialAd.setLandscapePanel(mSid, R.drawable.amoad_panel_l);
        InterstitialAd.setCloseButton(mSid, R.drawable.amoad_close_btn, R.drawable.amoad_close_btn_h);
        InterstitialAd.setLinkButton(mSid, R.drawable.amoad_link_btn, R.drawable.amoad_link_btn_h);
        InterstitialAd.load(this, mSid, new AdLoadListener() {
            @Override
            public void onLoaded(String sid, AdResult result, AMoAdError error) {
                switch (result) {
                    case Success:
                        break;
                    case Empty:
                        break;
                    case Failure:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (InterstitialAd.isLoaded(mSid)) {
            InterstitialAd.show(mSid, new InterstitialAd.OnCloseListener() {
                @Override
                public void onClose(InterstitialAd.Result result) {
                    switch (result) {
                        case Click:
                            break;
                        case Failure:
                            break;
                        case Duplicated:
                            break;
                        case CloseFromApp:
                            break;
                        case Close:
                            break;
                    }
                }
            });
        }
    }
}
