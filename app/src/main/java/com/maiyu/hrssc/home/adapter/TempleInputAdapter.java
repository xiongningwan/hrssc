package com.maiyu.hrssc.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 模版输入适配器
 */

public class TempleInputAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public TempleInputAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_list_temple_input_item, parent, false);
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
        private final EditText input;

        public AddressViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            input = (EditText) itemView.findViewById(R.id.input_et);
        }

        @Override
        public void onBindView() {
            String lable = (String) mList.get(getAdapterPosition());
            if (lable != null) {
                name.setText(lable);
            }

        }
    }
}
