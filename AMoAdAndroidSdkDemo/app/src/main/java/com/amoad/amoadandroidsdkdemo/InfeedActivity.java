package com.amoad.amoadandroidsdkdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amoad.AdItem;
import com.amoad.AdList;
import com.amoad.AdResult;
import com.amoad.InfeedAd;
import com.amoad.InfeedAdLoadListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InfeedActivity extends HomeButtonActivity {
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private ItemViewAdapter mAdapter;
    private ItemLoadTask mTask;
    private String mSid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infeed);
        mSid = getIntent().getStringExtra("sid");
        mAdapter = new ItemViewAdapter(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(mAdapter);

        if (savedInstanceState == null) {
            appendItems(10);
        }
    }

    void appendItems(int count) {
        if (mTask == null) {
            mTask = new ItemLoadTask(count);
            mExecutor.execute(mTask);
        }
    }

    void refreshItems() {
        mAdapter.clear();
        appendItems(10);
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

    class ItemLoadTask implements Runnable {
        int mCount;

        ItemLoadTask(int count) {
            mCount = count;
        }

        @Override
        public void run() {

            //アイテム取得
            final List<MyItem> items = getMyItems(mCount);

            //広告取得
            InfeedAd.load(getApplicationContext(), mSid, new InfeedAdLoadListener() {
                @Override
                public void onLoad(AdList adList, AdResult adResult) {
                    switch (adResult) {
                        case Success:
                            //TODO 広告取得成功
                            Log.d("TAG", "広告取得成功");
                            mAdapter.addItems(mergeAdItems(items, adList));
                            break;
                        case Empty:
                            //TODO 空広告(配信されている広告がない)
                            Log.d("TAG", "空広告");
                            break;
                        case Failure:
                        default:
                            //TODO 広告取得失敗
                            Log.d("TAG", "広告取得失敗");
                    }
                    mTask = null;
                }
            });
        }
    }

    private List<MyItem> getMyItems(int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("<yyyy/MM/dd hh:mm ss>");
        Date now = new Date();
        final List<MyItem> items = new ArrayList<MyItem>();
        for (int i = 0; i < count; i++) {
            items.add(new MyItem("", "Title-" + i, "Desciption-" + i, "" + dateFormat.format(now)));
        }
        return items;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", new ArrayList<Parcelable>(mAdapter.getItems()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.addItems(savedInstanceState.getParcelableArrayList("items"));
    }

    static class ItemViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        final List<Parcelable> mItems = new ArrayList<Parcelable>();
        final Context mContext;

        ItemViewAdapter(Context context) {
            mContext = context;
        }

        public List<Parcelable> getItems() {
            return mItems;
        }

        public void addItems(List<Parcelable> items) {
            mItems.addAll(items);
            notifyDataSetChanged();
        }

        public void clear() {
            mItems.clear();
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_infeed, null));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder vh, int position) {
            AdItem adItem = null;
            Object item = mItems.get(position);
            if (item instanceof AdItem) {
                adItem = (AdItem) item;
                bindAdItem(vh, adItem);
            } else if (item instanceof MyItem) {
                bindMyItem(vh, (MyItem) item);
            }
            InfeedAd.setViewabilityTracking((ViewGroup)vh.itemView, adItem);
        }

        private void bindAdItem(ItemViewHolder vh, final AdItem adItem) {
            Picasso.with(mContext).load(adItem.getIconUrl()).fit().into(vh.imageView);
            vh.titleView.setText(adItem.getTitleShort());
            vh.descriptionView.setText(adItem.getTitleLong());
            vh.dateView.setText(adItem.getServiceName());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adItem.onClick(mContext);
                }
            });
        }

        private void bindMyItem(ItemViewHolder vh, MyItem item) {
            vh.imageView.setImageResource(R.drawable.item);
            vh.titleView.setText(item.mTitle);
            vh.descriptionView.setText(item.mDescription);
            vh.dateView.setText(item.mDate);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //...
                }
            });
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView descriptionView;
        TextView dateView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titleView = (TextView) itemView.findViewById(R.id.titleView);
            descriptionView = (TextView) itemView.findViewById(R.id.descriptionView);
            dateView = (TextView) itemView.findViewById(R.id.dateView);
        }
    }

    static class MyItem implements Parcelable {
        String mImageUrl;
        String mTitle;
        String mDescription;
        String mDate;

        MyItem(String imageUrl, String title, String description, String date) {
            mImageUrl = imageUrl;
            mTitle = title;
            mDescription = description;
            mDate = date;
        }

        MyItem(Parcel in) {
            this(in.readString(), in.readString(), in.readString(), in.readString());
        }

        public static final Creator<MyItem> CREATOR = new Creator<MyItem>() {
            @Override
            public MyItem createFromParcel(Parcel in) {
                return new MyItem(in);
            }

            @Override
            public MyItem[] newArray(int size) {
                return new MyItem[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(mImageUrl);
            dest.writeString(mTitle);
            dest.writeString(mDescription);
            dest.writeString(mDate);
        }
    }

    public static List<Parcelable> mergeAdItems(List<MyItem> items, AdList adList) {
        List<Parcelable> result = new ArrayList<Parcelable>();

        List<AdItem> ads = adList.getAdItemList();
        Iterator<AdItem> adIterator = ads.iterator();
        int beginIndex = adList.getBeginIndex();
        int interval = adList.getInterval();

        int totalCount = items.isEmpty() ? 0 : items.size() + ads.size();
        Iterator<MyItem> itemIterator = items.iterator();
        for (int i = 0; i < totalCount; i++) {
            if (isAdPosition(i, beginIndex, interval) && adIterator.hasNext()) {
                result.add(adIterator.next());
            } else if (itemIterator.hasNext()) {
                result.add(itemIterator.next());
            }
        }

        return result;
    }

    public static boolean isAdPosition(int position, int beginIndex, int interval) {
        if (beginIndex < 0) {
            return false;
        }
        if (interval == 0) {
            return (position == beginIndex) ? true : false;
        } else {
            if (position < beginIndex) {
                return false;
            }

            final int index = position - beginIndex;
            final boolean isAd = (index % interval == 0) ? true : false;
            if (isAd) {
                return (index / interval >= 0);
            }
            return false;
        }
    }

    private static class DividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        @SuppressWarnings("deprecation")
		public DividerItemDecoration(Context context) {
            mDivider = context.getResources().getDrawable(R.drawable.line_divider);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }
}
