package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.amoad.AMoAdResult;
import com.amoad.Analytics;
import com.amoad.NativePreRoll;
import com.amoad.OnReceiveListener;

public class ScreenActivity extends HomeButtonActivity {
    private static final String TAG = "ad1";
    private String mSid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        mSid = getIntent().getStringExtra("sid");

        NativePreRoll.prepareAd(this, mSid);
        initAd();
    }

    private void initAd() {
        Analytics analytics = new Analytics("00000");
        analytics.setParameter("manga_id", "11111");
        analytics.setParameter("magazine_id", "22222");
        analytics.setParameter("content_id", "33333");
        NativePreRoll.renderAd(this, mSid, TAG, (FrameLayout) (findViewById(R.id.ad)), analytics, new OnReceiveListener() {
            @Override
            public void onReceive(String sid, AMoAdResult result) {
                switch (result.result) {
                    case Success:
                        //広告取得成功
                        break;
                    case Failure:
                        //広告取得失敗
                        break;
                    case Empty:
                        //配信広告がない
                        break;
                }
            }
        });
    }
}
