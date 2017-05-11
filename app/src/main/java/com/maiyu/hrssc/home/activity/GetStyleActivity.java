package com.maiyu.hrssc.home.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.AddressManageActivity;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.bean.SelfAddress;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetStyleActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.zzdy_rl)
    RelativeLayout mZzdyRl;
    @BindView(R.id.zzlq_rl)
    RelativeLayout mZzlqRl;
    @BindView(R.id.yj_rl)
    RelativeLayout mYjRl;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private int mPage = 1;
    private int mCount = 15;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_get_style);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("领取方式", true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.head_view, R.id.zzdy_rl, R.id.zzlq_rl, R.id.yj_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head_view:
                break;
            case R.id.zzdy_rl:
                Intent data = new Intent();
                data.putExtra("style", "自助打印");
                setResult(RESULT_OK, data);
                finish();
                break;
            case R.id.zzlq_rl: {
                ArrayList<SelfAddress> addrs = getIntent().getParcelableArrayListExtra("addres");
                Intent intent = new Intent(this, ChooseAddressActivity.class);
                intent.putParcelableArrayListExtra("addres", addrs);
                startActivityForResult(intent, 111);

            }
            break;
            case R.id.yj_rl: {
                Intent data2 = new Intent();
                data2.putExtra("style", "邮寄");
                //setResult(RESULT_OK, data2);
                // startActivityForResult(new Intent(this, DeliveryAddressActivity.class), 112);
                startActivityForResult(new Intent(this, AddressManageActivity.class), 112);
            }
            break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 111:
                    if (data != null) {
                        SelfAddress selfAddress = (SelfAddress) data.getParcelableExtra("selfAddress");
                        data.putExtra("style", "自助领取");
                        setResult(RESULT_OK, data);
                        HintUitl.toastShort(this, data != null ? selfAddress.getAddress() : "未选取到地址");
                    }
                    break;
                case 112:
                    if (data != null) {
                        new AddressListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
                    }
                    break;

            }
        }
    }


    /**
     * 获取地址列表
     */
    class AddressListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String page;
        private String rows;
        private String token;
        private List<AddressManage> list;

        public AddressListAsyncTask(String token, String page, String rows) {
            super();

            this.page = page;
            this.rows = rows;
            this.token = token;
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
                list = engine.getManageAddressList(GetStyleActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(GetStyleActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<AddressManage> list) {
        for (AddressManage am : list) {
            if ("1".equals(am.getIs_default())) {
                Intent intent = new Intent();
                intent.putExtra("style", "邮寄");
                intent.putExtra("recipient", am.getProv() + am.getCity() + am.getArea() + am.getAddr());
                setResult(RESULT_OK, intent);
            }
        }

    }
}
