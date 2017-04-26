package com.maiyu.hrssc.integration.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.adapter.BaseViewHolder;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.view.AdvertisementImageBanner;
import com.maiyu.hrssc.integration.activity.DuihuanRecordActivity;
import com.maiyu.hrssc.integration.bean.IngegrationProduct;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示积分商品的适配器
 */
public class IntegrationAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    Context mContext;
    private final View.OnClickListener listener;
    List<IngegrationProduct> mList = new ArrayList<>();
    LayoutInflater mLayoutInflater = null;
    private final int TYPE_HEADER_ITEM = 0;
    private final int TYPE_LIST_ITEM = 1;


    public IntegrationAdapter(Context context, View.OnClickListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    public void setData(List list) {
        if (list == null || list.size() == 0) {
            return;
        }

        mList.clear();
        mList.addAll(list);
        notifyItemRangeChanged(1, (int) (mList.size() / 2.0 + 0.5));
    }

    public void loadMoreData(List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        int position = (int) (mList.size() / 2.0 + 0.5);
        mList.addAll(list);
        notifyItemRangeChanged(position, (int) (mList.size() / 2.0 + 0.5));
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        BaseViewHolder holder = null;
        switch (viewType) {
            case TYPE_HEADER_ITEM:
                view = mLayoutInflater.inflate(R.layout.view_recycle_view_item_integration_banner, parent, false);
                holder = new HeaderViewHolder(view);
                break;
            case TYPE_LIST_ITEM:
                view = mLayoutInflater.inflate(R.layout.view_recycle_view_item_integration_products, parent, false);
                holder = new ProductViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (holder != null) {
            holder.onBindView();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER_ITEM;

        } else {
            return TYPE_LIST_ITEM;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        int count = (int) ((mList.size() / 2.0) + 0.5 + 1);
        return count;
    }


    public class ProductViewHolder extends BaseViewHolder {
        private final RelativeLayout productRL1;
        private final ImageView productImage1;
        private final TextView productName1;
        private final TextView productJifen1;
        private final RelativeLayout productRL2;
        private final ImageView productImage2;
        private final TextView productName2;
        private final TextView productJifen2;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productRL1 = (RelativeLayout) itemView.findViewById(R.id.product_1);
            productImage1 = (ImageView) itemView.findViewById(R.id.product_iv_1);
            productName1 = (TextView) itemView.findViewById(R.id.product_name_1);
            productJifen1 = (TextView) itemView.findViewById(R.id.product_jifen_1);

            productRL2 = (RelativeLayout) itemView.findViewById(R.id.product_2);
            productImage2 = (ImageView) itemView.findViewById(R.id.product_iv_2);
            productName2 = (TextView) itemView.findViewById(R.id.product_name_2);
            productJifen2 = (TextView) itemView.findViewById(R.id.product_jifen_2);
        }

        @Override
        public void onBindView() {
            int position = getAdapterPosition();

            int index1 = (int) ((position - 0.5) * 2 - 1); // 0 2 4 6 8
            int index2 = index1 + 1; // 1 3 5 7 9

            productRL1.setVisibility(View.GONE);
            productRL2.setVisibility(View.GONE);
            if (index1 >= 0 && index1 < mList.size()) {
                IngegrationProduct item1 = mList.get(index1);


                if (item1 != null) {
                    ImageLoaderUtil.loadImage(
                            productImage1,
                            ConstantValue.FILE_SERVER_URI + item1.getImg_url(),
                            R.mipmap.user_profile_image_default);

                    productName1.setText(item1.getName());
                    productJifen1.setText(item1.getWorth() + "积分");

                    productRL1.setVisibility(View.VISIBLE);
                    productRL1.setTag(R.id.key_tag_item_data, item1);
                    productRL1.setOnClickListener(listener);
                }
            }

            if (index2 >= 0 && index2 < mList.size()) {
                IngegrationProduct item2 = mList.get(index2);
                if (item2 != null) {
                    ImageLoaderUtil.loadImage(
                            productImage2,
                            ConstantValue.FILE_SERVER_URI + item2.getImg_url(),
                            R.mipmap.user_profile_image_default);

                    productName2.setText(item2.getName());
                    productJifen2.setText(item2.getWorth() + "积分");

                    productRL2.setVisibility(View.VISIBLE);
                    productRL2.setTag(R.id.key_tag_item_data, item2);
                    productRL2.setOnClickListener(listener);
                }
            }
        }
    }

    private class HeaderViewHolder extends BaseViewHolder {
        private final AdvertisementImageBanner bannerView;
        private final TextView jifenTv;
        private final TextView recordBtn;

        public HeaderViewHolder(View view) {
            super(view);

            bannerView = (AdvertisementImageBanner) view.findViewById(R.id.banner_view);
            jifenTv = (TextView) view.findViewById(R.id.value_dangqianjf);
            recordBtn = (TextView) view.findViewById(R.id.dizhi_tv);
        }

        @Override
        public void onBindView() {
            String amount = DataCenter.getInstance().getuser().getAmount();
            jifenTv.setText(amount);

            recordBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, DuihuanRecordActivity.class));
                }
            });
        }
    }

    public void refreshIntegration() {
        notifyItemChanged(0);
    }
}
