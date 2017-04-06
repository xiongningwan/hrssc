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
    private List mList = new ArrayList<>();

    public void setData(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    public AddressManagerAdapter(Context context, View.OnClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mListener = listener;
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
            tel.setText(addressManage.getTel());
            address.setText(addressManage.getAddress());
            choose.setChecked(addressManage.isSelect());


            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 删除
                }
            });

            edit.setOnClickListener(mListener);
            edit.setTag(R.id.key_tag_item_data, addressManage);

            choose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // 选中默认


                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // HintUitl.toastShort(mContext, mList.get(getAdapterPosition()).toString());
                }
            });
        }
    }
}
