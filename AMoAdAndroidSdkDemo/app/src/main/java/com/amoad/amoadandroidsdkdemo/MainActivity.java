package com.amoad.amoadandroidsdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amoad.AMoAdLogger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final List<Object[]> ITEMS = new ArrayList<Object[]>();

    static {
        ITEMS.add(new Object[]{"Display広告", DisplayActivity.class});
        ITEMS.add(new Object[]{"Interstitial広告", InterstitialActivity.class});
        ITEMS.add(new Object[]{"Infeed広告", InfeedActivity.class});
        ITEMS.add(new Object[]{"NativeHtml広告", NativeHtmlActivity.class});
        ITEMS.add(new Object[]{"Screen広告", ScreenActivity.class});
        ITEMS.add(new Object[]{"InFeedAfiO広告", InFeedAfioActivity.class});
        ITEMS.add(new Object[]{"InterstitialAfiO広告", InterstitialAfioActivity.class});
        ITEMS.add(new Object[]{"NativeApp広告", NativeAppActivity.class});
        ITEMS.add(new Object[]{"ListView広告", ListViewActivity.class});
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AMoAd Sdkのログを出力する
        AMoAdLogger.getInstance().setEnabled(true);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new ItemAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> clazz = (Class<?>) ((Object[]) ITEMS.get(position))[1];
                startActivity(new Intent(MainActivity.this, FormActivity.class).putExtra("next_activity", clazz.getName()));
            }
        });
    }

    class ItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return ITEMS.size();
        }

        @Override
        public Object getItem(int position) {
            return ITEMS.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
            view.setText(((Object[]) getItem(position))[0].toString());
            return view;
        }
    }
}
