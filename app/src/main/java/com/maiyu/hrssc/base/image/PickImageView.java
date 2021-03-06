package com.maiyu.hrssc.base.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import cc.dagger.photopicker.PhotoPicker;


/**
 * Created by Administrator on 2017/3/24.
 */

public class PickImageView extends RelativeLayout {
    private GridView mGridview;
    private GridAdapter mGridAdapter;
    //存放所有选择的照片
    private ArrayList<String> allSelectedPictures = new ArrayList<String>();
    private Context mContext;
    private UpdateOuterDataListener mListener;
    private boolean mIsFirstCamera;
    private int mCount = 0;


    public PickImageView(Context context) {
        super(context);
        initView(context);
    }

    public PickImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PickImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.view_pick_image, this);
        //显示图片
        mGridview = (GridView) view.findViewById(R.id.grid_view);
        mGridAdapter = new GridAdapter(context);
        mGridview.setAdapter(mGridAdapter);
    }

    /**
     * 展示图片的GridView的适配器
     */
    class GridAdapter extends BaseAdapter {
        LayoutInflater mLayoutInflater = null;

        public GridAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return allSelectedPictures.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.view_pick_image_child_grid_item, null);

                holder.image = (ImageView) convertView.findViewById(R.id.child_iv);
                holder.delete = (ImageView) convertView.findViewById(R.id.child_delete);
                holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            setParam(position, holder, convertView);


            return convertView;
        }
    }

    boolean isLast;

    void setParam(final int position, ViewHolder holder, View convertView) {
        if (position != allSelectedPictures.size()) {
            Log.i("PickImageView", "position!=allSelectedPictures.size()" + "position:" + position + "****allSelectedPictures.size():" + allSelectedPictures.size());
            holder.delete.setVisibility(View.VISIBLE);
            String imageUrl = allSelectedPictures.get(position);
            if (imageUrl.contains("http")) {
                ImageLoaderUtil.loadImage(holder.image, imageUrl, R.mipmap.user_profile_image_default);
            } else {
                ImageLoader.getInstance().displayImage("file://" + allSelectedPictures.get(position),
                        holder.image);
            }


            holder.delete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击后移除图片
                    allSelectedPictures.remove(position);

                    //更新UI
                    mGridview.setAdapter(mGridAdapter);


                    // 更新外部
                    if (mListener != null) {
                        mListener.updateData(position);
                    }
                }
            });

            // holder.delete.setOnClickListener(mListener);
        } else {
            holder.image.setImageBitmap(
                    BitmapFactory.decodeResource(convertView.getResources(),
                            R.mipmap.icon_upiantianjia));

            holder.delete.setVisibility(View.GONE);

            holder.image.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    settingPickImage(mContext);
                }
            });
            isLast = true;

            Log.i("PickImageView", "position==allSelectedPictures.size()" + "position:" + position + "****allSelectedPictures.size():" + allSelectedPictures.size() + "flag:" + "1");


        }
    }


    public void setUpdateOterDataListener(UpdateOuterDataListener listener) {
        mListener = listener;
    }

    public interface UpdateOuterDataListener {
        public void updateData(int positon);
    }


    private void settingPickImage(Context context) {

        PhotoPicker.init(new UILImageLoader(), null);
        // 多选
        PhotoPicker.load()
                //  .filter(PhotoFilter) // 照片属性过滤
                .gridColumns(4) // 照片列表显示列数
                .showCamera(true)
                .multi()
                .maxPickSize(9) // 最大选择数
                // .selectedPaths(allSelectedPictures) // 已选择的照片地址
                .start((Activity) context); // 从Fragment、Activity中启动

    }

    public class ViewHolder {
        public ImageView image;
        public ImageView delete;
    }


    public void updatePickImageView(ArrayList<String> pictures) {

        allSelectedPictures.clear();
        allSelectedPictures.addAll(pictures);
        setListViewHeightBasedOnChildren(mGridview);
     //   mGridAdapter.notifyDataSetChanged();
        //更新UI
        mGridview.setAdapter(mGridAdapter);
    }

    public void updatePickImageView(ArrayList<String> pictures, boolean isFirstCamera) {
        mIsFirstCamera = isFirstCamera;
        allSelectedPictures.clear();
        allSelectedPictures.addAll(pictures);
        setListViewHeightBasedOnChildren(mGridview);
        mGridAdapter.notifyDataSetChanged();

    }


    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int extraHeight = 50;
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight + extraHeight;
        // 设置margin
        ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }


}
