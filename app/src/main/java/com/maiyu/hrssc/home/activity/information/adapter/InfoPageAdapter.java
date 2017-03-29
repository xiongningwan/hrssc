package com.maiyu.hrssc.home.activity.information.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.information.InformationDetialActivity;
import com.maiyu.hrssc.home.activity.information.bean.Info;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/28.
 */

public class InfoPageAdapter extends RecyclerView.Adapter<InfoPageAdapter.TodoPageViewHolder>{

    private List<Info> mList = new ArrayList();
    private final Context mContext;

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
            final Info info = (Info) mList.get(getAdapterPosition());
            if(info == null) {
                return;
            }
            title.setText(info.getTitleName());
            time.setText(info.getTime());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, InformationDetialActivity.class);
                  //  intent.putExtra("title", info.getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
