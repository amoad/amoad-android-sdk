package com.amoad.amoadandroidsdkdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.amoad.AMoAdNativeViewManager;

public class ListViewActivity extends HomeButtonActivity {
    private static final String TAG = "ad1";
    private ArrayAdapter<String> mAdapter;
    private BaseAdapter mNativeAdAdapter;
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_list_view);
        mSid = getIntent().getStringExtra("sid");
        AMoAdNativeViewManager.getInstance(this).prepareAd(mSid, 1, 5, true, true);
        initAd();
        addItems(10);
    }

    private void initAd() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        mNativeAdAdapter = AMoAdNativeViewManager.getInstance(this).createAdapter(mSid, TAG, mAdapter, R.layout.item_native);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(mNativeAdAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("add");
        menu.add("update");
        menu.add("delete");
        menu.add("clear");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("add".equals(item.getTitle())) {
            addItems(10);
        } else if ("update".equals(item.getTitle())) {
            update();
        } else if ("delete".equals(item.getTitle())) {
            delete();
        } else if ("clear".equals(item.getTitle())) {
            clear();
        }
        return super.onOptionsItemSelected(item);
    }

    void addItems(int count) {
        int index = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            mAdapter.add("item " + (i + index));
        }
    }

    private void update() {
        mAdapter.clear();
        AMoAdNativeViewManager.getInstance(this).updateAd(mSid, TAG);
        addItems(10);
    }

    private void delete() {
        if (!mAdapter.isEmpty()) {
            mAdapter.remove(mAdapter.getItem(0));
        }
    }

    private void clear() {
        AMoAdNativeViewManager.getInstance(this).clearAd(mSid, TAG);
        //clea all
        //AMoAdNativeViewManager.getInstance(this).clearAds(mSid);
    }
}
