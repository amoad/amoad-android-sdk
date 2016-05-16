package com.amoad.sample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.amoad.AMoAdError;
import com.amoad.AMoAdLogger;
import com.amoad.AMoAdLoggerListener;
import com.amoad.AdLoadListener;
import com.amoad.AdResult;
import com.amoad.InterstitialAd;

public class MainActivity extends Activity {
//    private static final String SID = "管理画面から発行されるsidを設定してください";
    private static final String SID = "62056d310111552c25d5488b4d921ac158b4e6a60950024f2454b61d6e44f86c";
    private EditText mConsole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        initAd();
        initConsole();
    }

    private void initUi() {
        mConsole = (EditText) findViewById(R.id.console);
        mConsole.setMovementMethod(ScrollingMovementMethod.getInstance());
        mConsole.setHorizontallyScrolling(true);

        CheckBox autoReload = (CheckBox) findViewById(R.id.autoReload);
        autoReload.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {
                com.amoad.InterstitialAd.setAutoReload(SID, isChecked);
            }
        });
        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadAd();
            }
        });
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showAdIfLoaded();
            }
        });
        findViewById(R.id.logClear).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mConsole.setText("");
            }
        });
    }

    private void initConsole() {
        AMoAdLogger.getInstance().setAMoAdLoggerListener(new AMoAdLoggerListener() {
            @Override
            public void onLog(int level, String tag, final String msg, Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        android.util.Log.d("TAG", "msg:" + msg);
                        mConsole.append(msg + "\n");
                        mConsole.setSelection(mConsole.getText().length());
                    }
                });
            }
        });
        AMoAdLogger.getInstance().setEnabled(true);
    }

    private void initAd() {
        com.amoad.InterstitialAd.register(SID);
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
    }

    private void loadAd() {
        com.amoad.InterstitialAd.load(MainActivity.this, SID, new AdLoadListener() {
            @Override
            public void onLoaded(String sid, AdResult adResult, AMoAdError error) {
                switch (adResult) {
                    case Success:
                        AMoAdLogger.getInstance().i("広告取得を成功した");
                        // 広告ロード完了後、すぐ表示する
                        //showAd();
                        break;
                    case Empty:
                        AMoAdLogger.getInstance().i("配信可能広告がない");
                        break;
                    case Failure:
                        AMoAdLogger.getInstance().i("広告取得を失敗した");
                        break;
                }
            }
        });
    }

    private void showAd() {
        com.amoad.InterstitialAd.show(SID, new InterstitialAd.OnCloseListener() {
            @Override
            public void onClose(InterstitialAd.Result result) {
                switch (result) {
                    case Click:
                        AMoAdLogger.getInstance().i("リンクボタンがクリックされたので閉じました");
                        break;
                    case Close:
                        AMoAdLogger.getInstance().i("閉じるボタンがクリックされたので閉じました");
                        break;
                    case CloseFromApp:
                        AMoAdLogger.getInstance().i("広告のクローズ");
                        break;
                    case Duplicated:
                        AMoAdLogger.getInstance().i("既に広告が開かれているので開きませんでした");
                        break;
                    case Failure:
                        AMoAdLogger.getInstance().i("広告の取得に失敗しました");
                        break;
                }
            }
        });
    }

    private void showAdIfLoaded() {
        if (com.amoad.InterstitialAd.isLoaded(SID)) {
            showAd();
        } else {
            AMoAdLogger.getInstance().i("広告がロードされてない");
        }
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
