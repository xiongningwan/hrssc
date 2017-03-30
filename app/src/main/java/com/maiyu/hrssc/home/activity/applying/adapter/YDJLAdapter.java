package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.TypeText;

import java.util.ArrayList;
import java.util.List;


/**
 * 运单记录进度
 */

public class YDJLAdapter extends RecyclerView.Adapter<YDJLAdapter.ProgressViewHolder> {
    private List<TypeText> mList = new ArrayList();




    private final Context mContext;

    public YDJLAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<TypeText> list) {
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
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_list_vertical_line_progress_type_text_yun_dan_item, null);
        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgressViewHolder holder, int position) {
        holder.onBindView();
    }

    @Override
    public int getItemCount() {
         return mList.size();
    }


    class ProgressViewHolder extends RecyclerView.ViewHolder {

        private final View point;
        private final View line;
        private final View line3;
        private final RelativeLayout ll;
        private final RelativeLayout ll_line2;
        private final RelativeLayout rl;
        private final TextView content;
        private final TextView time;

        public ProgressViewHolder(View itemView) {
            super(itemView);
            ll = (RelativeLayout)itemView.findViewById(R.id.ll);
            ll_line2 = (RelativeLayout)itemView.findViewById(R.id.ll_line2);
            rl = (RelativeLayout)itemView.findViewById(R.id.rl);
            point = (View)itemView.findViewById(R.id.point);
            line = (View)itemView.findViewById(R.id.line);
            line3 = (View)itemView.findViewById(R.id.line3);
            content = (TextView)itemView.findViewById(R.id.content);
            time = (TextView)itemView.findViewById(R.id.time);



        }


        void onBindView() {
            final TypeText typeText = (TypeText) mList.get(getAdapterPosition());
            if (typeText == null) {
                return;
            }

            line3.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
            ll_line2.setVisibility(View.VISIBLE);


            if(0 == getAdapterPosition()) {
                // 取消顶部线条
                line3.setVisibility(View.GONE);
                point.setBackgroundResource(R.mipmap.icon_dian);
                content.setTextColor(ContextCompat.getColor(mContext, R.color.project_color_general_hyperlink));
            } else if(mList.size()-1 == getAdapterPosition()) {
                // 取消中部和下部线条
                line.setVisibility(View.GONE);
                ll_line2.setVisibility(View.GONE);
            }



            content.setText(typeText.getContent());
            time.setText(typeText.getTime());
        }

    }

}
