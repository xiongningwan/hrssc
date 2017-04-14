package com.maiyu.hrssc.my.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;
import com.maiyu.hrssc.my.activity.bean.FastService;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 快捷服务适配
 */

public class FastServiceAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
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


    public FastServiceAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_activity_fast_service, parent, false));
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

        private final ImageView imgIv;
        private final TextView title;
        private TextView desc;
        private Button goBtn;

        public AddressViewHolder(View itemView) {
            super(itemView);
            imgIv = (ImageView) itemView.findViewById(R.id.xiecheng_logo_iv);
            title = (TextView) itemView.findViewById(R.id.xiecheng_name_tv);
            desc = (TextView) itemView.findViewById(R.id.xiecheng_desc_tv);
            goBtn = (Button) itemView.findViewById(R.id.xiecheng_goto_btn);

        }

        @Override
        public void onBindView() {
            FastService fastService = (FastService) mList.get(getAdapterPosition());

            title.setText(fastService.getName());
            desc.setText(fastService.getBrief());

            if (fastService.getIcon() != null) {
                ImageLoaderUtil.loadImage(imgIv, fastService.getIcon(), R.mipmap.user_profile_image_default);
            }

            goBtn.setTag(R.id.key_tag_item_data, fastService);
            goBtn.setOnClickListener(mListener);
        }
    }


}
