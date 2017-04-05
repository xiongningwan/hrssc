package com.maiyu.hrssc.home.activity.funds.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.funds.FundsDetailActivity;
import com.maiyu.hrssc.home.activity.funds.bean.Funds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class FundsHistoryAdapter extends RecyclerView.Adapter<FundsHistoryAdapter.TodoPageViewHolder>{

    private List<Funds> mList = new ArrayList();
    private final Context mContext;

    public FundsHistoryAdapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.list_item_list_ss_history_item, null);
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
        private TextView time;
        private TextView money;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.time);
            money = (TextView) itemView.findViewById(R.id.money);
        }


        void onBindView() {
            final Funds item = (Funds) mList.get(getAdapterPosition());
            if(item == null) {
                return;
            }
            time.setText(item.getTime());
            money.setText(item.getMoney()+"元");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FundsDetailActivity.class);
                    //  intent.putExtra("title", info.getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
