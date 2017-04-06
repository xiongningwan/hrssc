package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity {

    private static final int QUEST_CODE_EDITE_NAME = 101;
    private static final int QUEST_CODE_EDITE_TEL = 102;
    private static final int QUEST_CODE_EDITE_ADDRESS = 103;
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.name_ll)
    LinearLayout mNameLl;
    @BindView(R.id.tel)
    TextView mTel;
    @BindView(R.id.tel_ll)
    LinearLayout mTelLl;
    @BindView(R.id.lable_city)
    TextView mLableCity;
    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.city_rl)
    RelativeLayout mCityRl;
    @BindView(R.id.lable_address)
    TextView mLableAddress;
    @BindView(R.id.address)
    TextView mAddress;
    @BindView(R.id.address_rl)
    RelativeLayout mAddressRl;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;



    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        AddressManage addressManage = (AddressManage) getIntent().getParcelableExtra(AddressManageActivity.CONTENT_ITEM);

        if (addressManage != null && addressManage.getName() != null && !"".equals(addressManage.getName())) {

            mHeadView.setTitle("编辑地址", true, false);
            mName.setText(addressManage.getName());
            mTel.setText(addressManage.getTel());
            mAddress.setText(addressManage.getAddress());

        } else {
            mHeadView.setTitle("添加地址", true, false);

        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.name_ll, R.id.tel_ll, R.id.city_rl, R.id.address_rl, R.id.confirm_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name_ll:
                startActivityForResult(new Intent(this, EditActivity.class), QUEST_CODE_EDITE_NAME);
                break;
            case R.id.tel_ll:
                startActivityForResult(new Intent(this, EditActivity.class), QUEST_CODE_EDITE_TEL);
                break;
            case R.id.city_rl:
                startCitySelect();
                break;
            case R.id.address_rl:
                startActivityForResult(new Intent(this, EditActivity.class), QUEST_CODE_EDITE_ADDRESS);
                break;
            case R.id.confirm_btn:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (RESULT_OK == resultCode) {
            String content = data == null ? "" : data.getStringExtra(EditActivity.EDIT_CONTENT);
            switch (requestCode) {
                case QUEST_CODE_EDITE_NAME:

                    mName.setText(content);
                    break;
                case QUEST_CODE_EDITE_TEL:
                    mTel.setText(content);
                    break;
                case QUEST_CODE_EDITE_ADDRESS:
                    mAddress.setText(content);
                    break;
            }
        }
    }

    private void startCitySelect() {
        CityPicker cityPicker = new CityPicker.Builder(AddAddressActivity.this).textSize(20)
                .title("")
                .titleTextColor("#35a4f2")
                .titleBackgroundColor("#ffffff")
                .backgroundPop(0xa0000000)
                .confirTextColor("#35a4f2")
                .cancelTextColor("#35a4f2")
                .textSize(16)
                .province("广东省")
                .city("深圳市")
                .district("南山区")
                .textColor(Color.parseColor("#333333"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(5)
                .itemPadding(10)
                .build();


        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                mCity.setText(citySelected[0] + citySelected[1] + citySelected[2]);
            }

            @Override
            public void onCancel() {
                Toast.makeText(AddAddressActivity.this, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }
}
