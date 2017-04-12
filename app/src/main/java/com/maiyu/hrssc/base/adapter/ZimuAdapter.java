package com.maiyu.hrssc.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 字母
 */

public class ZimuAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public ZimuAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycle_zimu_item, parent, false));
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

        private final TextView zimuTv;

        public AddressViewHolder(View itemView) {
            super(itemView);
            zimuTv = (TextView) itemView.findViewById(R.id.zimu_Tv);
        }

        @Override
        public void onBindView() {
            String letter = (String) mList.get(getAdapterPosition());
            zimuTv.setText(letter);
            Log.d("sssss","letter:"+letter);
            itemView.setOnClickListener(mListener);
            itemView.setTag(R.id.key_tag_item_data, letter);

        }
    }
}
