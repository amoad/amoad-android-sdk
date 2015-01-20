package com.amoad.sample.amoadnativelistviewsample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.amoad.AMoAdNativeViewManager;

public class MainActivity extends ListActivity {
    private static final String SID = "62056d310111552c9b3760c1d92edf9269130714a5305730ef8c06c47fc19bb6";
    
    private static final String TAG = "tag1";
    private ArrayAdapter<String> mAdapter;
    private BaseAdapter mNativeAdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AMoAdNativeViewManager.getInstance(this).prepareAd(SID, TAG, 1, 5, true);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        add();

        mNativeAdAdapter = AMoAdNativeViewManager.getInstance(this).createAdapter(SID, TAG, mAdapter, R.layout.native_icon);
        setListAdapter(mNativeAdAdapter);
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
            add();
        } else if ("update".equals(item.getTitle())) {
            update();
        } else if ("delete".equals(item.getTitle())) {
            delete();
        } else if ("clear".equals(item.getTitle())) {
            clear();
        }
        return true;
    }

    void add() {
        int index = mAdapter.getCount();
        for (int i = 0; i < 10; i++) {
            mAdapter.add("item " + (i + index));
        }
    }

    void update() {
        mAdapter.clear();
        AMoAdNativeViewManager.getInstance(this).updateAd(SID, TAG);
        add();
    }
    
    void clear() {
        AMoAdNativeViewManager.getInstance(this).clearAd(SID, TAG);
    }

    void delete() {
        if (!mAdapter.isEmpty()) {
            mAdapter.remove(mAdapter.getItem(0));
        }
    }
}
