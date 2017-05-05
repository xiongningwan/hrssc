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
import com.maiyu.hrssc.home.bean.Template;

import java.util.ArrayList;
import java.util.List;


/**
 * 模版适配器
 */

public class TempleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public TempleAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_list_choose_temple_item, parent, false);
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

        private final TextView name;

        public AddressViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.temple_name);
        }

        @Override
        public void onBindView() {
            Template template = (Template) mList.get(getAdapterPosition());
            if (template != null) {
                name.setText(template.getName());
            }

            itemView.setTag(R.id.key_tag_item_data, template);
            itemView.setOnClickListener(mListener);
        }
    }
}
