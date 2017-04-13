package com.maiyu.hrssc.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.Messages;

import java.util.ArrayList;
import java.util.List;


/**
 * 消息适配
 */

public class messageAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private final View.OnClickListener mDelListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMoreData(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }



    public messageAdapter(Context context, View.OnClickListener listener, View.OnClickListener delListener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
        mDelListener = delListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_activity_message, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder != null) {
            holder.onBindView();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class AddressViewHolder extends BaseViewHolder {

        private final TextView title;
        private final TextView time;
        private RelativeLayout delRL;
        private Button delBtn;

        public AddressViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            delRL = (RelativeLayout) itemView.findViewById(R.id.del_bg_btn_rl);
            delBtn = (Button) itemView.findViewById(R.id.btn_del);

        }

        @Override
        public void onBindView() {
            Messages messages = (Messages) mList.get(getAdapterPosition());

            title.setText(messages.getTitle());
            time.setText(messages.getCreate_time());


            itemView.setTag(R.id.key_tag_item_data, messages);
            itemView.setOnClickListener(mListener);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    delRL.setVisibility(View.VISIBLE);
                    return false;
                }
            });

            delBtn.setTag(R.id.key_tag_item_data, messages);
            delBtn.setOnClickListener(mDelListener);


        }
    }



    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public int getPosition(Object b) {
        int position = mList.indexOf(b);
        return position;
    }



}
