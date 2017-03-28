package com.maiyu.hrssc.home.activity.todo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.todo.TodoDeitailActivity;
import com.maiyu.hrssc.home.activity.todo.bean.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */

public class TodoPageAdapter extends RecyclerView.Adapter<TodoPageAdapter.TodoPageViewHolder>{

    private List<Todo> mList = new ArrayList();
    private final Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public TodoPageAdapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.list_item_list_todo_item, null);
        return new TodoPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoPageViewHolder holder, int position) {
        holder.onBindView();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class TodoPageViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView status;
        private TextView time;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
        }


        void onBindView() {
            final Todo todo = (Todo) mList.get(getAdapterPosition());
            if(todo == null) {
                return;
            }
            title.setText(todo.getTitle());
            status.setText(todo.getStatus());
            time.setText(todo.getTime());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TodoDeitailActivity.class);
                    intent.putExtra("title", todo.getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
