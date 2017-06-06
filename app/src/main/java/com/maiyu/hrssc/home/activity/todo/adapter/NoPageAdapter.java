package com.maiyu.hrssc.home.activity.todo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maiyu.hrssc.R;

/**
 * 合同
 */

public class NoPageAdapter extends RecyclerView.Adapter<NoPageAdapter.TodoPageViewHolder> {

    private final Context mContext;

    public NoPageAdapter(Context context) {
        mContext = context;
    }


    @Override
    public TodoPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View   view = View.inflate(mContext, R.layout.list_item_list_todo_no_item, null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_list_todo_no_item, parent, false);
        return new TodoPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoPageViewHolder holder, int position) {
        holder.onBindView();
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    class TodoPageViewHolder extends RecyclerView.ViewHolder {

        public TodoPageViewHolder(View itemView) {
            super(itemView);

        }


        void onBindView() {

        }
    }


}
