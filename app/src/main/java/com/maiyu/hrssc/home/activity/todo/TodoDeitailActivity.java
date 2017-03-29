package com.maiyu.hrssc.home.activity.todo;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.todo.dialog.ConfirmDialog;
import com.maiyu.hrssc.util.HintUitl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.status)
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
    @BindView(R.id.address_detail_text)
    TextView mAddressDetailText;
    @BindView(R.id.qianshu_address)
    RelativeLayout mQianshuAddress;
    @BindView(R.id.divier_line_3)
    View mDivierLine3;
    @BindView(R.id.beizhu_et)
    EditText mBeizhuEt;
    @BindView(R.id.dangmian_qianhsu)
    TextView mDangmianQS;
    @BindView(R.id.dianzi_qianhsu)
    TextView mDianziQS;
    @BindView(R.id.youji_qianhsu)
    TextView mYoujiQS;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_todo_deitail);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String titleName = getIntent().getStringExtra("title");
        mHeadView.setTitle(titleName+"详情", true, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.hetong_rl, R.id.qianshu_address, R.id.dangmian_qianhsu, R.id.dianzi_qianhsu, R.id.youji_qianhsu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hetong_rl:

                break;
            case R.id.qianshu_address:

                break;
            case R.id.dangmian_qianhsu:
                ConfirmDialog dialog = new ConfirmDialog(this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HintUitl.toastShort(TodoDeitailActivity.this, "确认");
                    }
                });
                dialog.setTitleText("确认当面签署？");
                dialog.setContentText("");
                dialog.show();

                break;
            case R.id.dianzi_qianhsu:
                    startActivity(new Intent(this, SignActivity.class));
                break;
            case R.id.youji_qianhsu:
                ConfirmDialog dialog1 = new ConfirmDialog(this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HintUitl.toastShort(TodoDeitailActivity.this, "确认");
                    }
                });
                dialog1.setTitleText("确认邮寄签署？");
                dialog1.setContentText("请在web端下载文档打印后邮寄回公司？");
                dialog1.show();
                break;
        }
    }
}
