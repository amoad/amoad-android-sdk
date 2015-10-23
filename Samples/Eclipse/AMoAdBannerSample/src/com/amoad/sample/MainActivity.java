package com.amoad.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amoad.AMoAdView;
import com.amoad.AdCallback;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("画面１");
        setContentView(R.layout.activity_main);
        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SubActivity.class));
            }
        });


        /**
         * SDKから通知を受け取るためにAdCallbackを設定する
         */
        ((AMoAdView) findViewById(R.id.banner)).setCallback(new AdCallback() {
            @Override
            public void didReceiveEmptyAd() {
                // 空広告を受信した場合に呼び出されます。
            }

            @Override
            public void didReceiveAd() {
                // 広告受信に成功した場合に呼び出されます。
            }

            @Override
            public void didFailToReceiveAdWithError() {
                // 広告受信に失敗した場合に呼び出されます。
            }
        });
    }
}
