package com.maiyu.hrssc.home.activity.information.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.home.activity.information.InformationDetialActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 资讯列表适配
 */

public class InfoPageAdapter extends RecyclerView.Adapter<InfoPageAdapter.TodoPageViewHolder>{

    private List<News> mList = new ArrayList();
    private final Context mContext;
    SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat msdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public InfoPageAdapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.list_item_list_info_item, null);
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
        private TextView time;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_name);
            time = (TextView) itemView.findViewById(R.id.time);
        }


        void onBindView() {
            final News news = (News) mList.get(getAdapterPosition());
            if(news == null) {
                return;
            }
            title.setText(news.getTitle());

            try {
                Date dateTime = msdf.parse(news.getCreate_time());
                time.setText(msdf1.format(dateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, InformationDetialActivity.class);
                    intent.putExtra("nid", String.valueOf(news.getId()));
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
