package com.maiyu.hrssc.home.activity.todo;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.todo.bean.ContractFlow;
import com.maiyu.hrssc.home.activity.todo.dialog.ConfirmDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maiyu.hrssc.R.id.address_detail_text;
import static com.maiyu.hrssc.R.id.status;


public class TodoDeitailActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.id_number)
    TextView mIdNumber;
    @BindView(R.id.divier_line)
    View mDivierLine;
    @BindView(R.id.icon_xiangqing)
    ImageView mIconXiangqing;
    @BindView(R.id.title_name)
    TextView mTitleName;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.contract)
    TextView mContract;
    @BindView(status)
    TextView mStatus;
    @BindView(R.id.hetong_lable)
    TextView mHetongLable;
    @BindView(R.id.arrow_iv)
    ImageView mArrowIv;
    @BindView(R.id.hetong_rl)
    RelativeLayout mHetongRl;
    @BindView(R.id.divier_line_2)
    View mDivierLine2;
    @BindView(R.id.icon_dizhi)
    ImageView mIconDizhi;
    @BindView(address_detail_text)
    TextView mAddressDetailText;
    @BindView(R.id.qianshu_address)
    RelativeLayout mQianshuAddress;
    @BindView(R.id.divier_line_3)
    View mDivierLine3;
    @BindView(R.id.beizhu_et)
    TextView mBeizhuEt;
    @BindView(R.id.toto_qianming_ll)
    LinearLayout mTodoQianmingLL;
    @BindView(R.id.dangmian_qianhsu)
    TextView mDangmianQS;
    @BindView(R.id.dianzi_qianhsu)
    TextView mDianziQS;
    @BindView(R.id.youji_qianhsu)
    TextView mYoujiQS;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private String mId;
    private ContractFlow mContractFlow;
    ConfirmDialog dialog;
    private String mTitleNameString;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_todo_deitail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mTitleNameString = getIntent().getStringExtra("title");
        mId = getIntent().getStringExtra("id");
        String statusStr = getIntent().getStringExtra("status");
        mHeadView.setTitle(mTitleNameString + "详情", true, false);
        mLoadingDialog = new LoadingDialog(this);


        setViewStatus(statusStr);
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        if (mId != null) {
            new ContractFlowAsyncTask(mToken, mId).execute();
        }
    }

    void setViewStatus(String statusStr) {
        if(statusStr == null) {
            return;
        }

        if ("0".equals(statusStr)) { // 待签署
            mTodoQianmingLL.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.hetong_rl, R.id.qianshu_address, R.id.dangmian_qianhsu, R.id.dianzi_qianhsu, R.id.youji_qianhsu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hetong_rl:
                if (mContractFlow != null) {
                    seeContract(mContractFlow);
                }

                break;
            case R.id.qianshu_address:

                break;
            case R.id.dangmian_qianhsu:
                signContract("确认当面签署？", "", "1");

                break;
            case R.id.dianzi_qianhsu:
                // 电子签署

                // startActivity(new Intent(this, SignActivity.class));
                /*if (mId != null && mToken != null) {
                    new GetElectronSignAsyncTask(mToken, mId).execute();
                } else {
                    HintUitl.toastShort(this, "参数错误");
                }*/
                String url = ConstantValue.FILE_SERVER_URI + ConstantValue.path_electronSign + "?token=" + mToken + "&aid=" + mId;
                openWebActivity(url);

                break;
            case R.id.youji_qianhsu:
                signContract("确认邮寄签署？", "请在web端下载文档打印后邮寄回公司？", "2");
                break;
        }
    }

    /**
     * 获取合同详情
     */
    class ContractFlowAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String id;
        private ContractFlow mContractFlow;

        public ContractFlowAsyncTask(String token, String id) {
            super();

            this.token = token;
            this.id = id;
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
                mContractFlow = engine.getContractFlow(TodoDeitailActivity.this, token, id);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(TodoDeitailActivity.this)) {
                return;
            }
            if (mContractFlow != null) {
                setData(mContractFlow);
            }

            super.onPostExecute(result);
        }
    }

    public void setData(ContractFlow contractFlow) {
        mContractFlow = contractFlow;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mIdNumber.setText("NO." + contractFlow.getTpl_cid());
        mTitleName.setText(contractFlow.getTpl_name());

        Date dateTime = null;

        if ("0".equals(contractFlow.getStatus())) {
            mStatus.setText("待签署");
            try {
                dateTime = sdf.parse(contractFlow.getCreate_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("1".equals(contractFlow.getStatus())) {
            mStatus.setText("待盖章");
            try {
                dateTime = sdf.parse(contractFlow.getSign_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if ("2".equals(contractFlow.getStatus())) {
            mStatus.setText("已撤销");
            try {
                dateTime = sdf.parse(contractFlow.getCreate_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if ("3".equals(contractFlow.getStatus())) {
            mStatus.setText("已完成");
            try {
                dateTime = sdf.parse(contractFlow.getFinish_time());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        mTime.setText(sdf1.format(dateTime));

        mAddressDetailText.setText(contractFlow.getAddress());
        String document = contractFlow.getTpl_document();
        String contractName = getDocumentName(document);
        mContract.setText(contractName);
        mBeizhuEt.setText(contractFlow.getBrief());
    }


    private void seeContract(ContractFlow contractFlow) {
        String document = contractFlow.getTpl_document();
        String contractName = getDocumentName(document);

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", ConstantValue.FILE_SERVER_URI + contractFlow.getTpl_document());
        intent.putExtra("titleName", contractName);
        intent.putExtra("type", ConstantValue.TYPE_ORDINARY);
        startActivity(intent);
    }


    String getDocumentName(String document) {

        return document.substring(document.lastIndexOf("/") + 1, document.length());
    }


    /**
     * 签署合同
     */
    class SignContractAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String id;
        private String sign_way;
        private String str;

        public SignContractAsyncTask(String token, String id, String sign_way) {
            super();

            this.token = token;
            this.id = id;
            this.sign_way = sign_way;
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
                str = engine.signContractFlow(TodoDeitailActivity.this, token, id, sign_way);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(TodoDeitailActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(TodoDeitailActivity.this, str);
            }

            super.onPostExecute(result);
        }
    }


    void signContract(String title, String content, final String signWay) {
        dialog = new ConfirmDialog(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dissmiss();
                if (mId != null) {
                    new SignContractAsyncTask(mToken, mId, signWay).execute();
                }
            }
        });
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.show();
    }


    /**
     * 电子签署链接
     */
    class GetElectronSignAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String id;
        private String str;

        public GetElectronSignAsyncTask(String token, String id) {
            super();

            this.token = token;
            this.id = id;
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
                str = engine.electronSign(TodoDeitailActivity.this, token, id);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(TodoDeitailActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(TodoDeitailActivity.this, str);
                openWebActivity(str);
            }

            super.onPostExecute(result);
        }
    }

    void openWebActivity(String url) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("titleName", "");
        intent.putExtra("type", ConstantValue.TYPE_IMPORTANT);
        startActivity(intent);
    }
}
