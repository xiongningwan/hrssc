package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.AddressManagerAdapter;
import com.maiyu.hrssc.base.bean.AddressManage;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.OnRefreshListener;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理
 */
public class AddressManageActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {
    private static final int LIST_ITEM_EDIT = 104;
    private static final int ADD_ADDRESS = 105;
    public static final String CONTENT_ITEM = "content_item";
    @BindView(R.id.head_view)
    HeadView mHeadView;
    Button mDuihuanBtn;
    @BindView(R.id.llybuttom)
    LinearLayout mLlybuttom;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeToLoadLayout mRefreshLayout;
    private int mPage = 1;
    private int mCount = 5;
    private final int init = 1;
    private final int isRefreshing = 2;
    private final int isLoadMoreing = 3;
    private int status = init;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private AddressManagerAdapter mAdapter;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_address_manage);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("收件地址管理", true, false);

        // 设置刷新加载
        mRefreshLayout.setRefreshEnabled(true);
        mRefreshLayout.setLoadMoreEnabled(true);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);

        // 设置列表
        mAdapter = new AddressManagerAdapter(this, new EditOnclickLister(), new CheckedLister(), new DelOnclickLister());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        String style = getIntent().getStringExtra("style");
        if("邮寄".equals(style)) {
            setResult(RESULT_OK);
        }
        new AddressListAsyncTask(mToken, String.valueOf(mPage), String.valueOf(mCount)).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.duihuan_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.duihuan_btn:
                // 添加地址
                Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
                intent.putExtra(CONTENT_ITEM, new AddressManage());
                startActivityForResult(intent, ADD_ADDRESS);
                break;
        }
    }

    /**
     * 编辑地址
     */
    private class EditOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 编辑地址
            AddressManage addressManage = (AddressManage) v.getTag(R.id.key_tag_item_data);
            Intent intent = new Intent(AddressManageActivity.this, AddAddressActivity.class);
            intent.putExtra(CONTENT_ITEM, addressManage);
            startActivityForResult(intent, LIST_ITEM_EDIT);
        }
    }

    /**
     * 删除地址
     */
    private class DelOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 删除地址
            AddressManage addressManage = (AddressManage) v.getTag(R.id.key_tag_item_data);
            int position = mAdapter.getPosition(addressManage);
            mAdapter.removeItem(position);
            // 执行请求
            new DelAndSetDefaultAddressAsyncTask(mToken, addressManage.getId(), true).execute();
        }
    }

    /**
     * 默认选中
     */
    private class CheckedLister implements CompoundButton.OnCheckedChangeListener {


        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // 默认选中
                AddressManage addressManage = (AddressManage) buttonView.getTag(R.id.key_tag_item_data);
                mAdapter.setALlNoChecked();
                buttonView.setChecked(isChecked);
                // 执行请求
                new DelAndSetDefaultAddressAsyncTask(mToken, addressManage.getId(), false).execute();
            }


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case LIST_ITEM_EDIT:
                    // 刷新列表
                    onRefresh();
                    break;
                case ADD_ADDRESS:
                    // 刷新列表
                    onRefresh();
                    break;
            }
        }
    }

    @Override
    public void onLoadMore() {
        mPage++;
        status = isLoadMoreing;
        initData();
        refreshOrLoadMoreComplete();
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        status = isRefreshing;
        initData();

        refreshOrLoadMoreComplete();
    }


    /**
     * 刷新加载更多完成
     */
    private void refreshOrLoadMoreComplete() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (mRefreshLayout.isLoadingMore()) {
            mRefreshLayout.setLoadingMore(false);
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
                list = engine.getManageAddressList(AddressManageActivity.this, token, page, rows);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(AddressManageActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<AddressManage> list) {
        if (status == init) {
            mAdapter.setData(list);
        } else if (status == isRefreshing) {
            mAdapter.setData(list);
        } else if (status == isLoadMoreing) {
            mAdapter.loadMoreData(list);
        }
    }


    /**
     * 删除和设置默认地址
     */
    class DelAndSetDefaultAddressAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private boolean isdel;
        private String str;

        public DelAndSetDefaultAddressAsyncTask(String token, String aid, boolean isdel) {
            super();

            this.token = token;
            this.aid = aid;
            this.isdel = isdel;
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
                if (isdel) {
                    str = engine.delAddress(AddressManageActivity.this, token, aid);
                } else {
                    str = engine.setDefaultAddress(AddressManageActivity.this, token, aid);
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
            if (checkException(AddressManageActivity.this)) {
                return;
            }
            if (str != null && !str.equals("")) {
                HintUitl.toastShort(AddressManageActivity.this, str);
            }

            super.onPostExecute(result);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
