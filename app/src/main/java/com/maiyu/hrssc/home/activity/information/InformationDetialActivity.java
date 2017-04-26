package com.maiyu.hrssc.home.activity.information;

import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.News;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationDetialActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.have_content_rl)
    RelativeLayout mHaveContentRl;
    @BindView(R.id.have_no_content_iv)
    ImageView mHaveNoContentIv;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    SimpleDateFormat msdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat msdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_information_detial);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("资讯详情", true, false);
        mLoadingDialog = new LoadingDialog(this);

        mToken = DataCenter.getInstance().getuser().getToken();
    }

    @Override
    public void initData() {
        String nid = getIntent().getStringExtra("nid");
        if (nid != null) {
            new InfoDetailAsyncTask(mToken, nid).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    /**
     * 获取资讯详情
     */
    class InfoDetailAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String nid;
        private News news;

        public InfoDetailAsyncTask(String token, String nid) {
            super();

            this.token = token;
            this.nid = nid;
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
                news = engine.getInfoDetail(InformationDetialActivity.this, token, nid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(InformationDetialActivity.this)) {
                return;
            }
            if (news != null) {
                setData(news);
            } else {
                mHaveContentRl.setVisibility(View.GONE);
                mHaveNoContentIv.setVisibility(View.VISIBLE);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(News news) {
        mTitle.setText(news.getTitle());
        mContent.setText(Html.fromHtml(news.getContent()));

        try {
            Date time = msdf.parse(news.getCreate_time());
            mTime.setText(msdf1.format(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
