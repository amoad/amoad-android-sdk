package com.amoad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdNativeFailureListener;
import com.amoad.AMoAdNativeViewManager;

public class MainActivity extends Activity {
    /* マンガボックステスト_グリッド(小) */
    private static final String SID1 = "62056d310111552c95c1571ac120c24fa414e24922410ee28479adefe662ce2d";

    /* マンガボックステスト_グリッド(大) */
    private static final String SID2 = "62056d310111552c95c1571ac120c24ffe493245f0d9f3a2d6aafe878b356c18";

    private static final String TAG1 = "tag1";
    private static final String TAG2 = "tag2";
    private static final String TAG3 = "tag3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMoAdNativeViewManager.getInstance(this).prepareAd(SID1, TAG1, true, true);
        AMoAdNativeViewManager.getInstance(this).prepareAd(SID2, TAG2);
        AMoAdNativeViewManager.getInstance(this).prepareAd(SID1, TAG3);

        showAd1();
        showAd2();
        showAd3();
    }

    void showAd1() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container1);
        View adView = AMoAdNativeViewManager.getInstance(this).createView(SID1, TAG1, R.layout.ad_small, new AMoAdNativeFailureListener() {
            @Override
            public void onFailure(String sid, String tag, View templateView) {
                // 広告の取得失敗
            }
        });
        container.addView(adView);
    }

    void showAd2() {
        ViewGroup container = (ViewGroup) findViewById(R.id.container2);
        View adView = AMoAdNativeViewManager.getInstance(this).createView(SID2, TAG2, R.layout.ad_big);
        container.addView(adView);
    }

    void showAd3() {
        View templateView = findViewById(R.id.container3);
        AMoAdNativeViewManager.getInstance(this).renderAd(SID1, TAG3, templateView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("update");
        menu.add("clear");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("update".equals(item.getTitle())) {
            updateAd();
        } else if ("clear".equals(item.getTitle())) {
            clearAd();
        }
        return true;
    }

    /* 広告更新 */
    void updateAd() {
        AMoAdNativeViewManager.getInstance(this).updateAd(SID1, TAG1);
        AMoAdNativeViewManager.getInstance(this).updateAd(SID2, TAG2);
        AMoAdNativeViewManager.getInstance(this).updateAd(SID1, TAG3);
    }

    void clearAd() {
        AMoAdNativeViewManager.getInstance(this).clearAd(SID1, TAG1);
        AMoAdNativeViewManager.getInstance(this).clearAd(SID2, TAG2);
        AMoAdNativeViewManager.getInstance(this).clearAd(SID1, TAG3);
    }
}
