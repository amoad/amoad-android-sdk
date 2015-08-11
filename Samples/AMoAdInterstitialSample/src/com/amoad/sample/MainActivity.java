package com.amoad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private static final String SID = "管理画面から発行されるsidを設定してください";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		com.amoad.InterstitialAd.prepare(this, SID);
		// com.amoad.InterstitialAd.setCloseButton(SID, R.drawable.amoad_close_btn, R.drawable.amoad_close_btn_h);
		// com.amoad.InterstitialAd.setLinkButton(SID, R.drawable.amoad_link_btn, R.drawable.amoad_link_btn_h);
		// com.amoad.InterstitialAd.setPanel(SID, R.drawable.amoad_panel);

		findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				com.amoad.InterstitialAd.show(SID, new com.amoad.InterstitialAd.OnCloseListener() {
					@Override
					public void onClose(com.amoad.InterstitialAd.Result result) {
						switch (result) {
						case Click:
							// 広告のクリック
							break;
						case Close:
							// 広告のクローズ
							break;
						case Failure:
							// 広告取得の失敗
							break;
						}
					}
				});
			}
		});
	}
}
