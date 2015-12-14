package com.amoad.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private static final String SID = "管理画面から取得したプリロール広告のsidを指定してください";
	private static final String TAG1 = "TAG1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// [SDK] 広告分析情報を生成する
		// => ご利用の際は担当営業までお問い合わせください。利用しない場合は、nilを指定してください。
		String report_id = "ABCD"; // レポートを細分化するためのID
		String value1 = "ABCD"; // パブリッシャ様が任意に指定できる分析用パラメタ文字列
		String value2 = "ABCD"; // パブリッシャ様が任意に指定できる分析用パラメタ文字列
		String value3 = "ABCD"; // パブリッシャ様が任意に指定できる分析用パラメタ文字列

		com.amoad.Analytics analytics = new com.amoad.Analytics(report_id);
		analytics.setParameter("key1", value1);
		analytics.setParameter("key2", value2);
		analytics.setParameter("key3", value3);

		com.amoad.NativePreRoll.prepareAd(this, SID);
		com.amoad.NativePreRoll.renderAd(this, SID, TAG1, (RelativeLayout) (findViewById(R.id.relativelayout)), analytics, new com.amoad.OnReceiveListener() {
			@Override
			public void onReceive(String sid, com.amoad.AMoAdResult result) {
				switch (result.result) {
				case Success:
					Log.d("MainActivity", "広告受信成功:" + sid + ", " + result.tag);
					break;
				case Failure:
					Log.d("MainActivity", "広告受信失敗:" + sid + ", " + result.tag);
					break;
				case Empty:
					Log.d("MainActivity", "配信する広告が無い:" + sid + ", " + result.tag);
					break;
				}
			}
		});
	}
}
