package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdNativeViewManager;

public class NativeAppActivity extends HomeButtonActivity {
    private static final String TAG = "ad1";
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_app);
        mSid = getIntent().getStringExtra("sid");
        initAd();
    }

    private void initAd() {
        AMoAdNativeViewManager.getInstance(this).prepareAd(mSid, true, true);

        View template = getLayoutInflater().inflate(R.layout.item_native, null);
        AMoAdNativeViewManager.getInstance(this).renderAd(mSid, TAG, template, null, null, null, null);

        ViewGroup vg = (ViewGroup) findViewById(R.id.ad);
        vg.addView(template);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Update");
        menu.add("Clear");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("Update".equals(item.getTitle())) {
            update();
        } else if ("Clear".equals(item.getTitle())) {
            clear();
        }
        return super.onOptionsItemSelected(item);
    }

    private void update() {
        AMoAdNativeViewManager.getInstance(this).updateAd(mSid, TAG);
    }

    private void clear() {
        AMoAdNativeViewManager.getInstance(this).clearAd(mSid, TAG);
        //clear all
        //AMoAdNativeViewManager.getInstance(this).clearAds(mSid);
    }
}
