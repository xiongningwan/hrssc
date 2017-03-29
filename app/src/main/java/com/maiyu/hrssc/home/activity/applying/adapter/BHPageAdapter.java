package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请 驳回
 */

public class BHPageAdapter extends RecyclerView.Adapter<BHPageAdapter.TodoPageViewHolder> {

    private List<Apply> mList = new ArrayList();
    private final Context mContext;

    public BHPageAdapter(Context context) {
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
        private RelativeLayout delRL;
        private Button delBtn;
        private Button recommitBtn;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
            btn = (Button) itemView.findViewById(R.id.btn_item);
            reason = (TextView) itemView.findViewById(R.id.reason);
            delRL = (RelativeLayout) itemView.findViewById(R.id.del_bg_btn_rl);
            delBtn = (Button) itemView.findViewById(R.id.btn_del);
            recommitBtn = (Button) itemView.findViewById(R.id.btn_recommit);
        }


        void onBindView() {
            final Apply apply = (Apply) mList.get(getAdapterPosition());
            if (apply == null) {
                return;
            }
            title.setText(apply.getTitle());
            status.setText(apply.getSatus());
            time.setText(apply.getTime());

            btn.setVisibility(View.GONE);
            reason.setVisibility(View.VISIBLE);


            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mList.remove(position);
                    notifyItemRemoved(position);
                }
            });
            recommitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delRL.setVisibility(View.GONE);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    delRL.setVisibility(View.VISIBLE);
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  delRL.setVisibility(View.GONE);


                    //  Intent intent = new Intent(mContext, InformationDetialActivity.class);
                    //  intent.putExtra("title", info.getTitle());
                    //  mContext.startActivity(intent);
                }
            });
        }
    }

}
