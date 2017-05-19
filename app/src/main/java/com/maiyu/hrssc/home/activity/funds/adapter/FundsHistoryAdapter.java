package com.maiyu.hrssc.home.activity.funds.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.funds.bean.PublicFund;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class FundsHistoryAdapter extends RecyclerView.Adapter<FundsHistoryAdapter.TodoPageViewHolder> {

    private List<PublicFund> mList = new ArrayList();
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
        View view = View.inflate(mContext, R.layout.list_item_list_fund_history_item, null);
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
        private TextView city;
        private TextView time;
        private TextView jishu;
        private TextView danweibili;
        private TextView gerenbili;
        private TextView amount;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            city = (TextView) itemView.findViewById(R.id.city);
            time = (TextView) itemView.findViewById(R.id.time);
            jishu = (TextView) itemView.findViewById(R.id.jishu);
            danweibili = (TextView) itemView.findViewById(R.id.danweibili);
            gerenbili = (TextView) itemView.findViewById(R.id.gerenbili);
            amount = (TextView) itemView.findViewById(R.id.amount);
        }


        void onBindView() {
            final PublicFund item = (PublicFund) mList.get(getAdapterPosition());
            if (item == null) {
                return;
            }
            city.setText(item.getCity());
            time.setText(item.getPaytime());
            jishu.setText(item.getBase());
            danweibili.setText(item.getPercent_company());
            gerenbili.setText(item.getPercent_personal());
            amount.setText(item.getPayamount());


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(mContext, FundsDetailActivity.class);
                    //  intent.putExtra("title", info.getTitle());
                    // mContext.startActivity(intent);
                }
            });
        }
    }

}
