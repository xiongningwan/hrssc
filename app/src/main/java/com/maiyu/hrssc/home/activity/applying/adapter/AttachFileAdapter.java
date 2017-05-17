package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.AProgress;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请 驳回
 */

public class AttachFileAdapter extends RecyclerView.Adapter<AttachFileAdapter.AttachFileViewHolder> {

    private List<String> mList = new ArrayList();
    private final Context mContext;

    public AttachFileAdapter(Context context) {
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
    public AttachFileAdapter.AttachFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_list_attach_file_item_content_detail, null);
        return new AttachFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttachFileAdapter.AttachFileViewHolder holder, int position) {
        holder.onBindView();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    class AttachFileViewHolder extends RecyclerView.ViewHolder{
        private TextView attachFileTv;
        public AttachFileViewHolder(View itemView) {
            super(itemView);
            attachFileTv = (TextView)itemView.findViewById(R.id.attach_file_tv);
        }

        public void onBindView() {
            String file = mList.get(getAdapterPosition());
            if(file == null) {
                return;
            }

            attachFileTv.setText(file.substring(file.lastIndexOf("/") + 1, file.length()));

        }
    }

    void setFiles(AProgress aprogress) {
        // 添加文件
        List<String> files = new ArrayList<>();
        String[] fileArr = aprogress.getAttachs().split(";");
        for (int i = 0; i < fileArr.length; i++) {
            files.add(fileArr[i]);
        }
    }

}
