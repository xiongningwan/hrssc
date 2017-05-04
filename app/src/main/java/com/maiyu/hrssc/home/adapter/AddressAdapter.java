package com.maiyu.hrssc.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;
import com.maiyu.hrssc.home.bean.SelfAddress;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/27.
 * 地址选择适配器
 */

public class AddressAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public AddressAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_recycler_view_item_choose_address, parent, false);
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        view.setBackgroundResource(typedValue.resourceId);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder != null) {
            holder.onBindView();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class AddressViewHolder extends BaseViewHolder {

        private final TextView addressName;
        private final TextView addressDetail;

        public AddressViewHolder(View itemView) {
            super(itemView);
            addressName = (TextView) itemView.findViewById(R.id.address_name);
            addressDetail = (TextView) itemView.findViewById(R.id.address_detial);
        }

        @Override
        public void onBindView() {
            SelfAddress selfAddress = (SelfAddress) mList.get(getAdapterPosition());
            if (selfAddress != null) {
                addressName.setText(selfAddress.getAddress());
                addressDetail.setText(selfAddress.getAddress_info());
            }

            itemView.setTag(R.id.key_tag_item_data, selfAddress);
            itemView.setOnClickListener(mListener);
        }
    }
}
