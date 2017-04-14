package com.maiyu.hrssc.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.AddressManage;

import java.util.ArrayList;
import java.util.List;


/**
 * 地址管理适配器
 */

public class AddressManagerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final View.OnClickListener mListener;
    private final CompoundButton.OnCheckedChangeListener mCheckedListener;
    private final View.OnClickListener mDelListener;
    private List mList = new ArrayList<>();
    private List<CheckBox> mChooseList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void loadMoreData(List list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public AddressManagerAdapter(Context context, View.OnClickListener listener,
                                 CompoundButton.OnCheckedChangeListener onCheckedChangeListener, View.OnClickListener delListener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
        mCheckedListener = onCheckedChangeListener;
        mDelListener = delListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressViewHolder(mInflater.inflate(R.layout.view_recycler_view_item_manange_address, parent, false));
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

        private final TextView name;
        private final TextView tel;
        private final TextView address;
        private final TextView del;
        private final TextView edit;
        private final CheckBox choose;

        public AddressViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            tel = (TextView) itemView.findViewById(R.id.tel);
            address = (TextView) itemView.findViewById(R.id.address_detail_tv);
            choose = (CheckBox) itemView.findViewById(R.id.moren_xuanze);
            del = (TextView) itemView.findViewById(R.id.shanchu_tv);
            edit = (TextView) itemView.findViewById(R.id.bianji_tv);
        }

        @Override
        public void onBindView() {
            AddressManage addressManage = (AddressManage) mList.get(getAdapterPosition());


            name.setText(addressManage.getName());
            tel.setText(addressManage.getPhone());
            address.setText(addressManage.getAddr());

            if ("1".equals(addressManage.getIs_default())) {
                choose.setChecked(true);
            } else {
                choose.setChecked(false);
            }


            // 编辑
            edit.setTag(R.id.key_tag_item_data, addressManage);
            edit.setOnClickListener(mListener);

            // 删除
            del.setTag(R.id.key_tag_item_data, addressManage);
            del.setOnClickListener(mDelListener);

            // 选默认
            choose.setTag(R.id.key_tag_item_data, addressManage);
            choose.setOnCheckedChangeListener(mCheckedListener);
            mChooseList.add(choose);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // HintUitl.toastShort(mContext, mList.get(getAdapterPosition()).toString());
                }
            });
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

    /**
     * 让所有项都设为
     */
    public void setALlNoChecked() {
        for (int i = 0; i < mChooseList.size(); i++) {
            mChooseList.get(i).setChecked(false);
        }
    }

}
