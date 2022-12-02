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
        initAd();
        appendItems(10);
    }

    private void initAd() {
        AMoAdNativeViewManager.getInstance(this).prepareAd(mSid, 1, 5, true, false);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        mNativeAdAdapter = AMoAdNativeViewManager.getInstance(this).createAdapter(mSid, TAG, mAdapter, R.layout.item_listview, null, null);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(mNativeAdAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("AppendItems");
        menu.add("RefreshItems");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ("AppendItems".equals(item.getTitle())) {
            appendItems(10);
        } else if ("RefreshItems".equals(item.getTitle())) {
            refreshItems();
        }
        return super.onOptionsItemSelected(item);
    }

    private void appendItems(int count) {
        int index = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            mAdapter.add("item " + (i + index));
        }
    }

    private void refreshItems() {
        mAdapter.clear();
        AMoAdNativeViewManager.getInstance(this).updateAd(mSid, TAG);
        appendItems(10);
    }
}
