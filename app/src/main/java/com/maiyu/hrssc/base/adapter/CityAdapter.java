package com.maiyu.hrssc.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.City;

import java.util.ArrayList;
import java.util.List;


/**
 * 城市
 */

public class CityAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public CityAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycle_city_item, parent, false));
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

        private final TextView cityTv;

        public AddressViewHolder(View itemView) {
            super(itemView);
            cityTv = (TextView) itemView.findViewById(R.id.city_tv);
        }

        @Override
        public void onBindView() {
            City city = (City) mList.get(getAdapterPosition());

            cityTv.setText(city.getCity());
            itemView.setTag(R.id.key_tag_item_data, city);
            itemView.setOnClickListener(mListener);
        }
    }
}
