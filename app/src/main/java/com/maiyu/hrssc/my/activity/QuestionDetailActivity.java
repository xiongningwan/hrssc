package com.maiyu.hrssc.my.activity;

import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.ISpecialEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.my.activity.bean.Question;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 问题详情详情
 */
public class QuestionDetailActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.content_rl)
    RelativeLayout mContentRl;
    @BindView(R.id.no_content)
    RelativeLayout mNOcontent;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat msdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_question_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("问题详情", true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }


    @Override
    public void initData() {
        String qid = getIntent().getStringExtra(HelpCenterSubActivity.QID);
        if (qid != null) {
            new QuestionDetailAsyncTask(mToken, qid).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    /**
     * 获取问题详情
     */
    class QuestionDetailAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String qid;
        private Question question;

        public QuestionDetailAsyncTask(String token, String qid) {
            super();

            this.token = token;
            this.qid = qid;
        }

        @Override
        protected void onPreExecute() {
            mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ISpecialEngine engine = EngineFactory.get(ISpecialEngine.class);
            try {
                question = engine.getQuestiontDetail(QuestionDetailActivity.this, token, qid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(QuestionDetailActivity.this)) {
                return;
            }
            if (question != null) {
                setData(question);
            } else {
                mContentRl.setVisibility(View.GONE);
                mNOcontent.setVisibility(View.VISIBLE);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(Question question) {
        mTitle.setText(question.getTitle());
        mContent.setText(Html.fromHtml(question.getContent()));
        try {
            Date dateTime = msdf.parse(question.getCreate_time());
            mTime.setText(msdf1.format(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
