package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdNativeListener;
import com.amoad.AMoAdNativeViewManager;

public class InFeedAfioActivity extends HomeButtonActivity implements AMoAdNativeListener {
    private static final String TAG = "ad1";

    private View mAdView;
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeed_afio);
        mSid = getIntent().getStringExtra("sid");

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        mAdView = findViewById(R.id.ad_view);

        initAd();
    }

    private void initAd() {
        // 広告 View を xml から生成する
        View view = LayoutInflater.from(this).inflate(R.layout.item_afio, (ViewGroup) mAdView);

        // 広告ダウンロードが完了するまで View を非表示にする
        mAdView.setVisibility(View.INVISIBLE);

        // 広告準備
        AMoAdNativeViewManager.getInstance(this).prepareAd(mSid, true, true);
        // 広告取得
        AMoAdNativeViewManager.getInstance(this).renderAd(mSid, TAG, view, this);
    }

    @Override
    public void onReceived(String s, String s1, View view, Result result) {
    }

    @Override
    public void onIconReceived(String s, String s1, View view, Result result) {
    }

    @Override
    public void onImageReceived(String s, String s1, View view, Result result) {
        // 広告ダウンロードが完了したら View を表示する
        if (result == Result.Success) {
            mAdView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClicked(String s, String s1, View view) {
    }

    private void update() {
        // 広告ダウンロードが完了するまで View を非表示にする
        mAdView.setVisibility(View.INVISIBLE);
        // 広告更新
        AMoAdNativeViewManager.getInstance(this).updateAd(mSid, TAG);
    }
}
