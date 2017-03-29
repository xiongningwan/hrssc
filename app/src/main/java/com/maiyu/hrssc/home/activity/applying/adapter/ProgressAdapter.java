package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请进度
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.TodoPageViewHolder> {

    private List<Apply> mList = new ArrayList();
    private final Context mContext;

    public ProgressAdapter(Context context) {
        mContext = context;
    }

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMoreData(List list) {
        int startPosition = mList.size();
        mList.addAll(list);
        notifyItemRangeChanged(startPosition, list.size());
    }


    @Override
    public TodoPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_list_vertical_line_progress_type_text_item, null);
        return new TodoPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoPageViewHolder holder, int position) {
        holder.onBindView();
    }

    @Override
    public int getItemCount() {
       // return mList.size();
        return 5;
    }


    class TodoPageViewHolder extends RecyclerView.ViewHolder {

        public TodoPageViewHolder(View itemView) {
            super(itemView);
        }


        void onBindView() {
           /* final Apply apply = (Apply) mList.get(getAdapterPosition());
            if (apply == null) {
                return;
            }*/
        }
    }

}
