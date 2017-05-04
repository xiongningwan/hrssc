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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 合同
 */

public class TodoPageAdapter extends RecyclerView.Adapter<TodoPageAdapter.TodoPageViewHolder> {

    private List<Todo> mList = new ArrayList();
    private final Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

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
            if (todo == null) {
                return;
            }
            title.setText(todo.getTpl_name());
            Date dateTime = null;

            if ("0".equals(todo.getStatus())) {
                status.setText("待签署");
                try {
                    dateTime = sdf.parse(todo.getCreate_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if ("1".equals(todo.getStatus())) {
                status.setText("待盖章");
                try {
                    dateTime = sdf.parse(todo.getSign_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else if ("2".equals(todo.getStatus())) {
                status.setText("已撤销");
                try {
                    dateTime = sdf.parse(todo.getCreate_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else if ("3".equals(todo.getStatus())) {
                status.setText("已完成");
                try {
                    dateTime = sdf.parse(todo.getFinish_time());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            time.setText(setTime(dateTime));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TodoDeitailActivity.class);
                    intent.putExtra("title", todo.getTpl_name());
                    intent.putExtra("id", todo.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    /**
     * 1分钟内 刚刚
     * 59分钟内 xx分钟前
     * 24小时内 xx小时前
     * 经过24小时  具体日期
     *
     * @param dateTime
     */
    String setTime(Date dateTime) {
        Date now = new Date();
        long nowLong = now.getTime();
        long dateTimeLong = dateTime.getTime();

        if (nowLong < dateTimeLong) {
            return "Unknown";
        }

        long cal = (nowLong - dateTimeLong) / 1000;

        if (cal < 1 * 60) {
            return "刚刚";

        } else if (cal < 60 * 60) {
            long minutes = cal / 60;
            return minutes + "分钟前";

        } else if (cal <= 24 * 60 * 60) {
            long hours = cal / 60 * 60;
            return hours + "小时前";

        } else if (cal > 24 * 60 * 60) {
            return sdf1.format(dateTime) + "";
        }

        return "";
    }

}
