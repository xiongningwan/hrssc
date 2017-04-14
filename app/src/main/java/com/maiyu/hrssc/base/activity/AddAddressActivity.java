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
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

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
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private int MODE;
    private int MODE_EDITE_ADDRESS = 1;
    private int MODE_ADD_ADDRESS = 2;
    private String pro = null;
    private String city = null;
    private String area = null;
    String name = null;
    String phone = null;
    String address = null;
    private AddressManage addressManage;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        addressManage = (AddressManage) getIntent().getParcelableExtra(AddressManageActivity.CONTENT_ITEM);

        if (addressManage != null && addressManage.getName() != null && !"".equals(addressManage.getName())) {
            mHeadView.setTitle("编辑地址", true, false);
            mName.setText(addressManage.getName());
            mTel.setText(addressManage.getPhone());
            mAddress.setText(addressManage.getAddr());
            MODE = MODE_EDITE_ADDRESS;
        } else {
            mHeadView.setTitle("添加地址", true, false);
            MODE = MODE_ADD_ADDRESS;
        }

        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();

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
                doConfirmBtn();
                break;
        }
    }

    private void doConfirmBtn() {
        if (!volidate()) {
            return;
        }

        if (MODE_EDITE_ADDRESS == MODE) {
            // 编辑
            String aid = "";
            if (addressManage != null) {
                aid = addressManage.getId();
            }
            new AddAndEditAddressAsyncTask(mToken, aid, name, phone, pro, city, area, address).execute();

        } else if (MODE_ADD_ADDRESS == MODE) {
            // 添加
            new AddAndEditAddressAsyncTask(mToken, name, phone, pro, city, area, address).execute();
        }

    }

    private boolean volidate() {
        name = mName.getText().toString();
        phone = mTel.getText().toString();
        address = mAddress.getText().toString();

        if (name == null || phone == null || pro == null || city == null || area == null || address == null ||
                "".equals(name) || "".equals(phone) || "".equals(pro) || "".equals(city) || "".equals(area) || "".equals(address)) {
            HintUitl.toastShort(this, "输入框不为空");
            return false;
        }

        return true;
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
                pro = citySelected[0];
                city = citySelected[1];
                area = citySelected[2];
            }

            @Override
            public void onCancel() {
                Toast.makeText(AddAddressActivity.this, "已取消", Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 添加编辑收件地址
     */
    class AddAndEditAddressAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private String name;
        private String phone;
        private String prov;
        private String city;
        private String area;
        private String addr;
        private String str;

        public AddAndEditAddressAsyncTask(String token, String aid, String name, String phone, String prov, String city, String area, String addr) {
            super();

            this.token = token;
            this.aid = aid;
            this.name = name;
            this.phone = phone;
            this.prov = prov;
            this.city = city;
            this.area = area;
            this.addr = addr;
        }

        public AddAndEditAddressAsyncTask(String token, String name, String phone, String prov, String city, String area, String addr) {
            super();

            this.token = token;
            this.name = name;
            this.phone = phone;
            this.prov = prov;
            this.city = city;
            this.area = area;
            this.addr = addr;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                if (MODE_EDITE_ADDRESS == MODE) {
                    // 编辑
                    str = engine.editAddress(AddAddressActivity.this, token, aid, name, phone, prov, city, area, addr);

                } else if (MODE_ADD_ADDRESS == MODE) {
                    // 添加
                    str = engine.addAddress(AddAddressActivity.this, token, name, phone, prov, city, area, addr);
                }

            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(AddAddressActivity.this)) {
                return;
            }
            if (str != null && !str.equals("")) {
                HintUitl.toastShort(AddAddressActivity.this, str);
                setResult(RESULT_OK);
                finish();
            }

            super.onPostExecute(result);
        }
    }


}
