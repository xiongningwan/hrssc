package com.maiyu.hrssc.base.activity;

import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.Messages;
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

import static com.maiyu.hrssc.base.activity.MessagesActivity.MESSAGE_ITEM_ID;

/**
 * 消息详情
 */
public class MessageDetailActivity extends BaseActivity {
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
        setContentView(R.layout.activity_message_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("消息详情", true, false);
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
    }


    @Override
    public void initData() {
        String mid = getIntent().getStringExtra(MESSAGE_ITEM_ID);
        if (mid != null) {
            new MessagesListAsyncTask(mToken, mid).execute();
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    /**
     * 获取消息列表
     */
    class MessagesListAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String mid;
        private Messages messages;

        public MessagesListAsyncTask(String token, String mid) {
            super();

            this.token = token;
            this.mid = mid;
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
                messages = engine.getMessageDetial(MessageDetailActivity.this, token, mid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(MessageDetailActivity.this)) {
                return;
            }
            if (messages != null) {
                setData(messages);
            } else {
                mContentRl.setVisibility(View.GONE);
                mNOcontent.setVisibility(View.VISIBLE);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(Messages messages) {
        mTitle.setText(messages.getTitle());
        mContent.setText(Html.fromHtml(messages.getContent()));


        try {
            Date dateTime = msdf.parse(messages.getCreate_time());
            mTime.setText(msdf1.format(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
