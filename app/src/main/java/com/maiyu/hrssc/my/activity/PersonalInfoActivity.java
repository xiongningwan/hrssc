package com.maiyu.hrssc.my.activity;

import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.text.SimpleDateFormat;

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
        User user = DataCenter.getInstance().getuser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-HH-dd hh:mm:ss");


        mName.setText(user.getName());
        mNumber.setText("" + user.getId());
        mTel.setText(user.getPhone());
        mLevel.setText("");
        mHukou.setText("");
        mJinjiTel.setText("");
        mEmail.setText(user.getEmail());

        if ("0".equals(user.getSex())) {
            mSex.setText("男");
        } else {
            mSex.setText("女");
        }

        mIdCard.setText(user.getId_card());
        mDate.setText(sdf.format(Long.parseLong(user.getCreate_time())));


        if (user.getHead() != null) {
            ImageLoaderUtil.loadImage(mHeadIconView, ConstantValue.FILE_SERVER_URI + user.getHead(), R.mipmap.timg);
        }
    }

    @Override
    public void initOnClick(View v) {

    }

}
