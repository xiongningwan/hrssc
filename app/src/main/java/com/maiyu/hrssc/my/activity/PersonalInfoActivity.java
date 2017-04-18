package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.my.activity.bean.MyInfo;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息
 */
public class PersonalInfoActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.head_icon_view)
    CircleImageView mHeadIconView;
    @BindView(R.id.number)
    TextView mNumber;
    @BindView(R.id.tel)
    TextView mTel;
    @BindView(R.id.level)
    TextView mLevel;
    @BindView(R.id.hukou)
    TextView mHukou;
    @BindView(R.id.jinji_tel)
    TextView mJinjiTel;
    @BindView(R.id.email)
    TextView mEmail;
    @BindView(R.id.sex)
    TextView mSex;
    @BindView(R.id.id_card)
    TextView mIdCard;
    @BindView(R.id.date)
    TextView mDate;
    private LoadingDialog mLoadingDialog;
    private String mToken;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("个人资料", true, false);
    }

    @Override
    public void initData() {
        mLoadingDialog = new LoadingDialog(this);
        mToken = DataCenter.getInstance().getuser().getToken();
        new MyInfoAsyncTask(mToken).execute();
    }

    @Override
    public void initOnClick(View v) {

    }


    /**
     * 查询个人资料信息
     */
    class MyInfoAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private MyInfo mInfo;

        public MyInfoAsyncTask(String token) {
            super();

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
                mInfo = engine.getMyInfo(PersonalInfoActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(PersonalInfoActivity.this)) {
                return;
            }
            if (mInfo != null) {
                setData(mInfo);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(MyInfo info) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd hh:mm:ss");

        mName.setText(info.getName());
        mNumber.setText(info.getUin());
        mTel.setText(info.getPhone());
        mLevel.setText(info.getLevel());
        mHukou.setText(info.getHukou_prov() + info.getHukou_city());
        mJinjiTel.setText(info.getEmergency_phone());
        mEmail.setText(info.getEmail());

        if ("0".equals(info.getSex())) {
            mSex.setText("男");
        } else {
            mSex.setText("女");
        }

        mIdCard.setText(info.getId_card());
        mDate.setText(info.getBirthday());


        if (info.getHead() != null) {
            ImageLoaderUtil.loadImage(mHeadIconView, ConstantValue.FILE_SERVER_URI + info.getHead(), R.mipmap.timg);
        }
    }

}
