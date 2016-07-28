package com.amoad.amoadandroidsdkdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.amoad.Native;
import com.amoad.Result;
import com.amoad.ResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class NativeHtmlActivity extends HomeButtonActivity implements View.OnClickListener {
    private static final String TAG = "ad1";
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSid = getIntent().getStringExtra("sid");
        setContentView(R.layout.activity_native_html);

        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_hide).setOnClickListener(this);
        findViewById(R.id.btn_reload).setOnClickListener(this);

        initAd();
    }

    private void initAd() {
        JSONObject css = null;
        try {
            css = new JSONObject("{\"border\": \"dotted 2px #0000ff\"}");
        } catch (JSONException e) {
        }

        Native.load(this, mSid, TAG, new ResultListener() {
            @Override
            public void onResult(String sid, String tag, Result result, JSONObject json) {
                switch (result) {
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
        }, css);
        View adView = Native.getView(mSid, TAG);
        attachAdView(adView);
    }

    private void attachAdView(View adView) {
        //dpiをpixelに変換する
        float density = getDensity(this);
        int width = dpiToPixel(140, density);
        int height = dpiToPixel(120, density);
        ViewGroup adContainer = (ViewGroup) findViewById(R.id.ad);
        adContainer.addView(adView, new ViewGroup.LayoutParams(width, height));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Native.disposeView(mSid, TAG);
    }

    private static final float getDensity(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    private static int dpiToPixel(int dpiValue, float density) {
        return (int) ((float) dpiValue * density + 0.5F);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                Native.show(mSid, TAG);
                break;
            case R.id.btn_hide:
                Native.hide(mSid, TAG);
                break;
            case R.id.btn_reload:
                Native.reload(mSid, TAG);
                break;
        }
    }
}
