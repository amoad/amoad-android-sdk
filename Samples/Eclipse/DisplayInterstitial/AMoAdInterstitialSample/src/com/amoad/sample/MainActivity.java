package com.amoad.sample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private static final String SID = "管理画面から発行されるsidを設定してください";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// インタースティシャル広告の準備
		com.amoad.InterstitialAd.prepare(this, SID);
		// 閉じるボタン画像（40dpx40dp）
		com.amoad.InterstitialAd.setCloseButton(SID, R.drawable.amoad_close_btn, R.drawable.amoad_close_btn_h);
		// リンクボタン画像（280dpx50dp）
		com.amoad.InterstitialAd.setLinkButton(SID, R.drawable.amoad_link_btn, R.drawable.amoad_link_btn_h);
		// 縦画面のパネル画像（310dpx380dp）
		com.amoad.InterstitialAd.setPortraitPanel(SID, R.drawable.amoad_panel);
		// 横画面のパネル画像（380dpx310dp）
		com.amoad.InterstitialAd.setLandscapePanel(SID, R.drawable.amoad_panel_l);
		// 広告クリック時に確認ダイアログを表示するかどうかを設定する
		com.amoad.InterstitialAd.setDialogShown(SID, false);

		findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// インタースティシャル広告を表示する
				com.amoad.InterstitialAd.show(SID, new com.amoad.InterstitialAd.OnCloseListener() {
					@Override
					public void onClose(com.amoad.InterstitialAd.Result result) {
						switch (result) {
						case Click:
							// リンクボタンがクリックされたので閉じました
							break;
						case Close:
							// 閉じるボタンがクリックされたので閉じました
							break;
						case CloseFromApp:
							// 広告のクローズ
							break;
						case Duplicated:
							// 既に開かれているので開きませんでした
							break;
						case Failure:
							// 広告の取得に失敗しました
							break;
						}
					}
				});
			}
		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		com.amoad.InterstitialAd.onConfigurationChanged(newConfig);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		com.amoad.InterstitialAd.close(SID);
	}
}
