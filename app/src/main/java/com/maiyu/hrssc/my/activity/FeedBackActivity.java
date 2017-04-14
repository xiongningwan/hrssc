package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
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

/**
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.feed_back_et)
    EditText mFeedBackEt;
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    private LoadingDialog mLoadingDialog;
    private String mToken;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("意见反馈", true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    @OnClick(R.id.confirm_btn)
    public void onClick() {
        String feedBackStr = mFeedBackEt.getText().toString();

        if (feedBackStr == null || "".equals(feedBackStr)) {
            HintUitl.toastShort(this, "输入框不能为空");
            return;
        }

        new FeedBackAsyncTask(mToken, "", feedBackStr).execute();
    }

    /**
     * 意见反馈
     */
    class FeedBackAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String title;
        private String content;
        private String str;

        public FeedBackAsyncTask(String token, String title, String content) {
            super();
            this.token = token;
            this.title = title;
            this.content = content;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingDialog.getDialog().show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                str = engine.feedBack(FeedBackActivity.this, token, title, content);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(FeedBackActivity.this)) {
                return;
            }
            if (str != null && !str.equals("")) {
                HintUitl.toastShort(FeedBackActivity.this, str);
                finish();
            }

            super.onPostExecute(result);
        }
    }
}
