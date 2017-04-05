package com.maiyu.hrssc.integration.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.integration.activity.ProductItemActivity;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示积分商品的GridView的适配器
 */
public class INtegrationGridAdapter extends BaseAdapter {
    Context mContext;
    List<IngegrationProduct> mList = new ArrayList<>();
    LayoutInflater mLayoutInflater = null;

    public INtegrationGridAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /*public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }*/


    @Override
    public int getCount() {
        return mList.size();
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

        ImageViewHolder holder = null;
        if (convertView == null) {
            holder = new ImageViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.view_grid_item_integration_product_item, null);

            holder.productImage = (ImageView) convertView.findViewById(R.id.product_iv);
            holder.productName = (TextView) convertView.findViewById(R.id.product_name);
            holder.productJifen = (TextView) convertView.findViewById(R.id.product_jifen);

            holder.productImage.setScaleType(ImageView.ScaleType.CENTER_CROP);


            convertView.setTag(holder);
        } else {
            holder = (ImageViewHolder) convertView.getTag();
        }

        IngegrationProduct item = mList.get(position);


        if (item != null) {
            ImageLoaderUtil.loadImage(
                    holder.productImage,
                    mList.get(position).getImageUrl(),
                    R.mipmap.user_profile_image_default);

            holder.productName.setText(item.getName());
            holder.productJifen.setText(item.getJifen()+"积分");
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ProductItemActivity.class));
            }
        });


        return convertView;
    }

    public class ImageViewHolder {
        public ImageView productImage;
        public TextView productName;
        public TextView productJifen;

    }

    public void setViewData(List<IngegrationProduct> items, GridView mGridView) {

        if (items == null) {
            return;
        }

        mList.clear();
        mList.addAll(items);
        setListViewHeightBasedOnChildren(mGridView);
        notifyDataSetChanged();

    }

    public void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int extraHeight = 10;
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
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

}