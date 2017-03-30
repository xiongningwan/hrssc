package com.maiyu.hrssc.home.activity.applying.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.home.activity.applying.bean.AttachFile;
import com.maiyu.hrssc.home.activity.applying.bean.AttachImage;
import com.maiyu.hrssc.home.activity.applying.bean.Schedule;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 申请进度
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {
    private List<Schedule> mList = new ArrayList();




    private final Context mContext;

    public ProgressAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<Schedule> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();

       /* for(Schedule s : list) {
           *//* if(s.getImages() != null && 0 != s.getImages().size()) {
                iamges.addAll(s.getImages());
            }*//*
            if(s.getFiles() != null && 0 != s.getFiles().size()) {
                files.addAll(s.getFiles());
            }
        }*/


    }



    public void loadMoreData(List list) {
        int startPosition = mList.size();
        mList.addAll(list);
        notifyItemRangeChanged(startPosition, list.size());
    }


    @Override
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.list_item_list_vertical_line_progress_type_text_item, null);
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
        private GridView mGridView;
        private GridAdapter mGridAdapter;
        private RecyclerView mRecyclerView;
        private AttachFilemRecyclerViewAdapter mRecyclerViewAdapter;

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
            mGridView = (GridView)itemView.findViewById(R.id.grid_view);
            mRecyclerView = (RecyclerView)itemView.findViewById(R.id.file_recyclerView);



        }


        void onBindView() {
            final Schedule schedule = (Schedule) mList.get(getAdapterPosition());
            if (schedule == null) {
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
            } else if(mList.size()-1 == getAdapterPosition()) {
                // 取消中部和下部线条
                line.setVisibility(View.GONE);
                ll_line2.setVisibility(View.GONE);
            }




            content.setText(schedule.getContent());
            time.setText(schedule.getTime());


            // 设置gridview
            mGridAdapter = new GridAdapter(mContext);
            mGridView.setAdapter(mGridAdapter);
            mGridAdapter.updatePickImageView(schedule.getImages(), mGridView, mGridAdapter);

            // 设置RecyclerView
            mRecyclerViewAdapter = new AttachFilemRecyclerViewAdapter();
            mRecyclerViewAdapter = new AttachFilemRecyclerViewAdapter();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            mRecyclerViewAdapter.updateFileView(schedule.getFiles(), mRecyclerViewAdapter);
        }





    }



    /**
     * 展示图片的GridView的适配器
     */
    class GridAdapter extends BaseAdapter {
        List<AttachImage> iamges = new ArrayList<>();
        LayoutInflater mLayoutInflater = null;

        public GridAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return iamges.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ImageViewHolder holder = null;
            if (convertView == null) {
                holder = new ImageViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.view_grid_item_image_item, null);

                holder.image = (ImageView) convertView.findViewById(R.id.child_iv);
                holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);


                convertView.setTag(holder);
            } else {
                holder = (ImageViewHolder) convertView.getTag();
            }

            if(iamges.get(position) != null) {
                ImageLoaderUtil.loadImage(
                        holder.image,
                        iamges.get(position).getImageUrl(),
                        R.mipmap.user_profile_image_default);
            }


            return convertView;
        }

        public class ImageViewHolder {
            public ImageView image;
        }

        public void updatePickImageView(List<AttachImage> pictures, GridView mGridView, GridAdapter mGridAdapter) {

            if(pictures == null) {
                return;
            }

            iamges.clear();
            iamges.addAll(pictures);
            setListViewHeightBasedOnChildren(mGridView);
            mGridAdapter.notifyDataSetChanged();

        }
    }


    class  AttachFilemRecyclerViewAdapter extends  RecyclerView.Adapter<AttachFilemRecyclerViewAdapter.AttachFileViewHolder>{
        List<AttachFile> files = new ArrayList<>();


        @Override
        public AttachFilemRecyclerViewAdapter.AttachFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(mContext, R.layout.list_item_list_attach_file_item, null);
            return new AttachFileViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AttachFilemRecyclerViewAdapter.AttachFileViewHolder holder, int position) {
            holder.onBindView();
        }

        @Override
        public int getItemCount() {
            return files.size();
        }


        class AttachFileViewHolder extends RecyclerView.ViewHolder{
            private TextView attachFileTv;
            public AttachFileViewHolder(View itemView) {
                super(itemView);
                attachFileTv = (TextView)itemView.findViewById(R.id.attach_file_tv);
            }

            public void onBindView() {
                AttachFile file = files.get(getAdapterPosition());
                if(file == null) {
                    return;
                }
                attachFileTv.setText(file.getDesc());

            }
        }

        public void updateFileView(List<AttachFile> list, AttachFilemRecyclerViewAdapter mRecyclerViewAdapter) {

            if(list == null) {
                return;
            }

            files.clear();
            files.addAll(list);
            mRecyclerViewAdapter.notifyDataSetChanged();

        }
    }



    public  void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int extraHeight = 10;
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight + extraHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

}
