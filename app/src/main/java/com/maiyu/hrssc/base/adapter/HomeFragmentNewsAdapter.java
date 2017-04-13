package com.maiyu.hrssc.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.News;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页 news 适配
 */

public class HomeFragmentNewsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
   // private final View.OnClickListener mListener;
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public HomeFragmentNewsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
       // mListener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_fragment_home_news, parent, false));
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

        public AddressViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.content);
            time = (TextView) itemView.findViewById(R.id.time);
        }

        @Override
        public void onBindView() {
            News news = (News) mList.get(getAdapterPosition());

            title.setText(news.getTitle());
            time.setText(news.getCreate_time());

            //itemView.setTag(R.id.key_tag_item_data, news);
           // itemView.setOnClickListener(mListener);

        }
    }
}
