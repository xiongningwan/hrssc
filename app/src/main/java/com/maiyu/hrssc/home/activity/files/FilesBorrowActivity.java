package com.maiyu.hrssc.home.activity.files;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.SucceedActivity;
import com.maiyu.hrssc.home.bean.Category2;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 档案借阅
 */
public class FilesBorrowActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.mBrief_et)
    EditText mBriefEt;
    private String mToken;
    private String mId;
    private String mTitle;
    private List<Category2> mCateGory2List;
    private LoadingDialog mLoadingDialog;
    private Category2 mCategory2;
    private String mCity;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_files_borrow);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, true);
        TextView rightText = mHeadView.getRightButtonText();
        rightText.setText("提交");
        rightText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        mCity = SharedPreferencesUtil.getCityName(this);
        if (mId != null) {
            String city = SharedPreferencesUtil.getCityName(this);
            new Category2AsyncTask(mToken, mId, city).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }

    /**
     * 获取二级业务
     */
    class Category2AsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String cid;
        private String city;
        private List<Category2> list;

        public Category2AsyncTask(String token, String cid, String city) {
            super();
            this.token = token;
            this.cid = cid;
            this.city = city;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                list = engine.getCategory2(FilesBorrowActivity.this, token, cid, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(FilesBorrowActivity.this)) {
                return;
            }
            if (list != null) {

                setCatgory1(list);

            }

            super.onPostExecute(result);
        }
    }

    void setCatgory1(List<Category2> list) {
        if (list.size() < 1) {
            return;
        }
        mCateGory2List = list;
        Category2 item0 = list.get(0);
        if (item0 != null) {
            mCategory2 = item0;
        }
    }

    /**
     * 提交
     */
    void doSubmit() {
        if (mCategory2 == null) {
            return;
        }

        String aid = "0";//如果是新的申请，aid缺省为0
        String type = "1";//0-保存草稿  1-提交申请
        String cid2 = mCategory2.getId(); // 二级业务id
        String mGet_way = "4"; // 缺省
        String mAddress = "";
        String mAddress_info = "";
        String mRecipient = "";
        String mTpl_tid = "0";
        String mTpl_form = "";
        String brief = mBriefEt.getText().toString(); // 描述
        String comment = ""; // 备注
        String language = "0";

        String paths = "";
        String attachs = "";


        if (brief.equals("")) {
            HintUitl.toastShort(this, "请填写申请说明");
            return;
        }


        if (mToken != null && mCity != null && mId != null && mGet_way != null && !mGet_way.equals("")) {

            new SubmitApplyAsyncTask(aid, mToken, type, mCity, cid2, mGet_way,
                    mAddress, mAddress_info, mRecipient, mTpl_tid, mTpl_form,
                    brief, comment, language, paths, attachs).execute();
        }
    }

    /**
     * 申请表单
     */
    class SubmitApplyAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String aid;
        private String token;
        private String type;
        private String city;
        private String cid2;
        private String get_way;
        private String address;
        private String address_info;
        private String recipient;
        private String tpl_tid;
        private String tpl_form;
        private String brief;
        private String comment;
        private String language;
        private String images;
        private String attachs;
        private String str;

        public SubmitApplyAsyncTask(String aid, String token, String type, String city, String cid2, String get_way,
                                    String address, String address_info, String recipient, String tpl_tid, String tpl_form,
                                    String brief, String comment, String language, String images, String attachs) {
            super();
            this.aid = aid;
            this.token = token;
            this.type = type;
            this.city = city;
            this.cid2 = cid2;
            this.get_way = get_way;
            this.address = address;
            this.address_info = address_info;
            this.recipient = recipient;
            this.tpl_tid = tpl_tid;
            this.tpl_form = tpl_form;
            this.brief = brief;
            this.comment = comment;
            this.language = language;
            this.images = images;
            this.attachs = attachs;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                str = engine.submitApply(FilesBorrowActivity.this, aid, token, type, city, cid2, get_way,
                        address, address_info, recipient, tpl_tid, tpl_form,
                        brief, comment, language, images, attachs);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(FilesBorrowActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(FilesBorrowActivity.this, str);
                startActivity(new Intent(FilesBorrowActivity.this, SucceedActivity.class));
            }

            super.onPostExecute(result);
        }
    }

}
