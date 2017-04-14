package com.maiyu.hrssc.my.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;
import com.maiyu.hrssc.my.activity.bean.QuestionClass;

import java.util.ArrayList;
import java.util.List;


/**
 * 问题中心分类适配
 */

public class QuestionClassAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMoreData(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public QuestionClassAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_activity_help_center, parent, false));
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

        private final TextView title;

        public AddressViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }

        @Override
        public void onBindView() {
            QuestionClass questionClass = (QuestionClass) mList.get(getAdapterPosition());
            title.setText(questionClass.getName());

            itemView.setTag(R.id.key_tag_item_data, questionClass);
            itemView.setOnClickListener(mListener);
        }
    }


}
