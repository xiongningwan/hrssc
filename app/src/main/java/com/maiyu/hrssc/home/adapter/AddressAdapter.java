package com.maiyu.hrssc.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2017/3/27.
 * 地址选择适配器
 */

public class AddressAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public AddressAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_choose_address, parent, false));
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HintUitl.toastShort(mContext, mList.get(getAdapterPosition()).toString());
                }
            });
        }
    }
}
