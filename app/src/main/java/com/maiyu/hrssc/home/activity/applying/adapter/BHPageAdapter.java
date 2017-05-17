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
import com.maiyu.hrssc.util.AppUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 申请 驳回
 */

public class BHPageAdapter extends RecyclerView.Adapter<BHPageAdapter.TodoPageViewHolder> {

    private final View.OnLongClickListener mListener;
    private List<Apply> mList = new ArrayList();
    private final Context mContext;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BHPageAdapter(Context context, View.OnLongClickListener listener) {
        mContext = context;
        mListener = listener;
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
            title.setText(apply.getName());

            if ("0".equals(apply.getStatus())) {
                status.setText("待审核");
            } else if ("1".equals(apply.getStatus())) {
                status.setText("待办理");
            } else if ("2".equals(apply.getStatus())) {
                status.setText("待领取");
            } else if ("3".equals(apply.getStatus())) {
                status.setText("待评价");
            } else if ("4".equals(apply.getStatus())) {
                status.setText("已完成");
            } else if ("5".equals(apply.getStatus())) {
                status.setText("已驳回");
            }

            Date dateTime = null;
            try {
                dateTime = sdf.parse(apply.getCreate_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            time.setText(AppUtil.setTime(dateTime));

            btn.setVisibility(View.GONE);
            reason.setVisibility(View.VISIBLE);
            reason.setText(apply.getResult());

            /*delBtn.setOnClickListener(new View.OnClickListener() {
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
            });*/

            itemView.setOnLongClickListener(mListener);
            itemView.setTag(R.id.key_tag_item_data, apply);

        }
    }

}
