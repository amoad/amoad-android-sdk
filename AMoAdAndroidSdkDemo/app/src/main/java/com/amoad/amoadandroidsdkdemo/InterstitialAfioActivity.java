package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.View;

import com.amoad.AMoAdInterstitialVideo;
import com.amoad.AdResult;

public class InterstitialAfioActivity extends HomeButtonActivity implements AMoAdInterstitialVideo.Listener {
    private static final String TAG = "ad1";

    private AMoAdInterstitialVideo mInterstitialVideo;
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mSid = getIntent().getStringExtra("sid");

        mInterstitialVideo = AMoAdInterstitialVideo.sharedInstance(this, mSid, TAG);
        mInterstitialVideo.setListener(this);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
    }

    private void load() {
        mInterstitialVideo.dismiss(this);
        mInterstitialVideo.load(this);
    }

    @Override
    public void onLoad(AMoAdInterstitialVideo aMoAdInterstitialVideo, AdResult adResult) {
        if (mInterstitialVideo.isLoaded()) {
            mInterstitialVideo.show(this);
        }
    }

    @Override
    public void onStart(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }

    @Override
    public void onComplete(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }

    @Override
    public void onFailed(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }

    @Override
    public void onShown(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }

    @Override
    public void onDismissed(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }

    @Override
    public void onClick(AMoAdInterstitialVideo amoadInterstitialVideo) {

    }
}
