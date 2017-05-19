package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.Apply;
import com.maiyu.hrssc.util.AppUtil;
import com.maiyu.hrssc.util.LogHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 草稿箱
 */

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.TodoPageViewHolder> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private List<Apply> mList = new ArrayList();
    private List<Apply> mIndexs = new ArrayList();
    private final Context mContext;
    private boolean isShow = false;

    public DraftAdapter(Context context) {
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

    public List<Apply> getRemoveDrafts() {
        return mIndexs;
    }

    public void detele(Apply draft) {
        mList.remove(draft);
        notifyDataSetChanged();
    }

    public void detele() {
        if (mIndexs.size() == 0) {
            return;
        }

        for (Apply draft : mIndexs) {
            mList.remove(draft);
            LogHelper.d("remove", "mList" + mList.toString());
        }

        notifyDataSetChanged();
    }

    public void showCircleDelBtn(boolean b) {
        isShow = b;
        notifyDataSetChanged();

    }


    @Override
    public TodoPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_list_draft_item, null);
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
        private TextView time;
        private CheckBox deleteBtn;

        public TodoPageViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_name);
            time = (TextView) itemView.findViewById(R.id.time);
            deleteBtn = (CheckBox) itemView.findViewById(R.id.delete_btn);
        }


        void onBindView() {
            final Apply draft = (Apply) mList.get(getAdapterPosition());
            if (draft == null) {
                return;
            }
            title.setText(draft.getName());
            Date dateTime = null;
            try {
                dateTime = sdf.parse(draft.getCreate_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            time.setText(AppUtil.setTime(dateTime));
            deleteBtn.setChecked(false);


            if (isShow) {
                deleteBtn.setVisibility(View.VISIBLE);
            } else {
                deleteBtn.setVisibility(View.GONE);
            }


            deleteBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mIndexs.add(draft);
                    } else {
                        mIndexs.remove(draft);
                    }
                }
            });


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
