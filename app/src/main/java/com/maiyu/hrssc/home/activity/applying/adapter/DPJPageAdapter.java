package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请 待评价
 */

public class DPJPageAdapter extends RecyclerView.Adapter<DPJPageAdapter.TodoPageViewHolder>{

    private List<Apply> mList = new ArrayList();
    private final Context mContext;

    public DPJPageAdapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.list_item_list_applying_item, null);
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
        private Button btn;
        private TextView reason;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
            btn = (Button) itemView.findViewById(R.id.btn_item);
            reason = (TextView) itemView.findViewById(R.id.reason);
        }


        void onBindView() {
            final Apply apply = (Apply) mList.get(getAdapterPosition());
            if(apply == null) {
                return;
            }
            title.setText(apply.getTitle());
            status.setText(apply.getSatus());
            time.setText(apply.getTime());

            btn.setVisibility(View.VISIBLE);
            btn.setBackgroundResource(R.drawable.corner_view_selector);
            reason.setVisibility(View.GONE);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Intent intent = new Intent(mContext, InformationDetialActivity.class);
                    //  intent.putExtra("title", info.getTitle());
                    //  mContext.startActivity(intent);
                }
            });
        }
    }

}
