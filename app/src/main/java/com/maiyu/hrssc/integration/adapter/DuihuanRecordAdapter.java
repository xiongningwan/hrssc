package com.maiyu.hrssc.integration.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.integration.activity.ProductItemActivity;
import com.maiyu.hrssc.integration.bean.Record;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请 待签署
 */

public class DuihuanRecordAdapter extends RecyclerView.Adapter<DuihuanRecordAdapter.TodoPageViewHolder> {

    private List<Record> mList = new ArrayList();
    private final Context mContext;

    public DuihuanRecordAdapter(Context context) {
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
        View view = View.inflate(mContext, R.layout.view_recycle_duihuan_record_item, null);
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

        private final TextView title;
        private final ImageView image;
        private final TextView jifen;
        private final TextView number;
        private final TextView time;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.product_desc_tv);
            image = (ImageView) itemView.findViewById(R.id.product_iv);
            jifen = (TextView) itemView.findViewById(R.id.product_jifen_value_tv);
            number = (TextView) itemView.findViewById(R.id.product_number_value_tv);
            time = (TextView) itemView.findViewById(R.id.time_tv);
        }


        void onBindView() {
            final Record record = (Record) mList.get(getAdapterPosition());
            if (record == null) {
                return;
            }
            title.setText(record.getPname());
            jifen.setText(record.getPoints());
            number.setText("×" + record.getCount());
            time.setText(record.getCreate_time());
            ImageLoaderUtil.loadImage(image, ConstantValue.FILE_SERVER_URI + record.getImg_url(), R.mipmap.user_profile_image_default);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProductItemActivity.class);
                    intent.putExtra("productId", record.getPid());
                    mContext.startActivity(intent);
                }
            });
        }
    }


}
