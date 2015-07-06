package com.amoad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amoad.AMoAdLogger;
import com.amoad.AMoAdLoggerListener;
import com.amoad.AMoAdNativeListener;
import com.amoad.AMoAdNativeViewManager;

public class MainActivity extends Activity {
    private static final String TAG1 = "ad-01";
    private static final String TAG2 = "ad-02";
    // TODO 管理画面から発行されるネイティブ(APP)のメイン画像テキスト型のsidを設定してください
    private static final String SID = "管理画面から発行されるネイティブ(APP)のメイン画像テキスト型のsidを設定してください";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AMoAdLogger.getInstance().setEnabled(true);
        AMoAdLogger.getInstance().setAMoAdLoggerListener(new AMoAdLoggerListener() {
            @Override
            public void onLog(int level, String tag, String msg, Throwable throwable) {
                // ログ確認
            }
        });

        AMoAdNativeViewManager.getInstance(this).prepareAd(SID, true, true);

        // createViewの使い方
        ViewGroup containe = (ViewGroup) findViewById(R.id.container);
        View adView = AMoAdNativeViewManager.getInstance(this).createView(SID, TAG1, R.layout.template, mListener);
        containe.addView(adView);

        // renderAdの使い方
        View templateView = findViewById(R.id.templateView);
        AMoAdNativeViewManager.getInstance(this).renderAd(SID, TAG2, templateView, mListener);
    }

	private AMoAdNativeListener mListener = new AMoAdNativeListener() {

		@Override
		public void onReceived(String sid, String tag, View templateView, AMoAdNativeListener.Result result) {
			// 広告情報の取得処理が終わったら呼ばれる
			if (result == AMoAdNativeListener.Result.Success) {
				// ...
			} else if (result == AMoAdNativeListener.Result.Failure) {
				// ...
			}
		}

		@Override
		public void onIconReceived(String sid, String tag, View templateView, AMoAdNativeListener.Result result) {
			// アイコン画像の取得処理が終わったら呼ばれる
			if (result == AMoAdNativeListener.Result.Success) {
				// ...
			} else if (result == AMoAdNativeListener.Result.Failure) {
				// ...
			}
		}

		@Override
		public void onImageReceived(String sid, String tag, View templateView, AMoAdNativeListener.Result result) {
			// メイン画像の取得処理が終わったら呼ばれる
			if (result == AMoAdNativeListener.Result.Success) {
				// ...
			} else if (result == AMoAdNativeListener.Result.Failure) {
				// ...
			}
		}

		@Override
		public void onClicked(String sid, String tag, View templateView) {
			// 広告がクリックされたら呼ばれる
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("update");
        menu.add("clear");
        menu.add("clear all");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("update".equals(item.getTitle())) {
            update();
        } else if ("clear".equals(item.getTitle())) {
            clear();
        } else if ("clear all".equals(item.getTitle())) {
            clearAll();
        }
        return true;
    }

    void update() {
        AMoAdNativeViewManager.getInstance(this).updateAd(SID, TAG1);
        AMoAdNativeViewManager.getInstance(this).updateAd(SID, TAG2);
    }

    void clear() {
        AMoAdNativeViewManager.getInstance(this).clearAd(SID, TAG1);
        AMoAdNativeViewManager.getInstance(this).clearAd(SID, TAG2);
    }

    void clearAll() {
        AMoAdNativeViewManager.getInstance(this).clearAds(SID);
    }
}
