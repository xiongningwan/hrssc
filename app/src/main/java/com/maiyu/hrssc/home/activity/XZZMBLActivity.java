package com.maiyu.hrssc.home.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IBizEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.image.PickImageView;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.bean.FormData;
import com.maiyu.hrssc.home.bean.SelfAddress;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.dagger.photopicker.PhotoPicker;

public class XZZMBLActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city)
    ImageView mCity;
    @BindView(R.id.city_name)
    TextView mCityName;
    @BindView(R.id.blsm_rl)
    RelativeLayout mBlsmRl;
    @BindView(R.id.get_style_text)
    TextView mGetStyleText;
    @BindView(R.id.xh)
    ImageView mXh;
    @BindView(R.id.get_style_rl)
    RelativeLayout mGetStyleRl;
    @BindView(R.id.choose_mould_text)
    TextView mChooseMouldText;
    @BindView(R.id.choose_mould_xh)
    ImageView mChooseMouldXh;
    @BindView(R.id.choose_mould_rl)
    RelativeLayout mChooseMouldRl;
    @BindView(R.id.choose_language_text)
    TextView mChooseLanguageText;
    @BindView(R.id.choose_language_xh)
    ImageView mChooseLanguageXh;
    @BindView(R.id.choose_language_rl)
    RelativeLayout mChooseLanguageRl;
    @BindView(R.id.simple_desc_text)
    TextView mSimpleDescText;
    @BindView(R.id.simple_desc_xh)
    ImageView mSimpleDescXh;
    @BindView(R.id.simple_desc_rl)
    RelativeLayout mSimpleDescRl;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.pcik_image_view)
    PickImageView mPickImageView;
    private String mId;
    private String mTitle;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private FormData mFormData;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_xzzmbl);
        ButterKnife.bind(this);
        checkPermission();
    }

    @Override
    public void initViews() {
        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, true);

        TextView rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("提交");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(XZZMBLActivity.this, "提交", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(XZZMBLActivity.this, SucceedActivity.class));
            }
        });
    }

    @Override
    public void initData() {
        mToken = DataCenter.getInstance().getuser().getToken();
        String city = SharedPreferencesUtil.getCityName(this);
        new Category2AsyncTask(mToken, mId, city).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.blsm_rl, R.id.get_style_rl, R.id.choose_mould_rl, R.id.choose_language_rl, R.id.simple_desc_rl})
    public void onClick(View view) {
        if (mFormData == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.blsm_rl:
                if (mFormData.getLink() != null) {
                    openWebActivity();
                }

                break;
            case R.id.get_style_rl: {
                if (mFormData.getAddrs() != null) {
                    Intent intent = new Intent(this, GetStyleActivity.class);
                    intent.putParcelableArrayListExtra("addres", (ArrayList<? extends Parcelable>) mFormData.getAddrs());
                    startActivityForResult(intent, 101);
                }
            }
            break;
            case R.id.choose_mould_rl:
                startActivityForResult(new Intent(this, ChooseTempleActivity.class), 102);

                break;
            case R.id.choose_language_rl:
                // startActivityForResult(new Intent(this, XZZMBLActivity.class), 102);

                break;
            case R.id.simple_desc_rl:
                startActivityForResult(new Intent(this, EtTextActivity.class), 104);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_SELECTED) {
                ArrayList<String> allSelectedPicture = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                mPickImageView.updatePickImageView(allSelectedPicture);

            } else if (requestCode == 101 && data != null) {
                mGetStyleText.setText(data.getStringExtra("style"));
                SelfAddress selfAddress = (SelfAddress) data.getParcelableExtra("selfAddress");
                if (selfAddress != null) {
                    HintUitl.toastShort(this, selfAddress.getAddress());
                }

            } else if (requestCode == 102) {


            } else if (requestCode == 104) {
                mSimpleDescText.setText(data != null ? data.getStringExtra(EtTextActivity.TEXT) : "");
            }

        }


    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.permission_rationale),
                    101);
        }
    }


    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(XZZMBLActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * 获取该业务 可以使用的全部模板   2.获取该城市的全部自取地址
     */
    class Category2AsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String cid2;
        private String city;
        private FormData formData;

        public Category2AsyncTask(String token, String cid2, String city) {
            super();
            this.token = token;
            this.cid2 = cid2;
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
                formData = engine.getTemplates(XZZMBLActivity.this, token, cid2, city);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mLoadingDialog.getDialog().dismiss();
            if (checkException(XZZMBLActivity.this)) {
                return;
            }
            if (formData != null) {

                setFormData(formData);

            }

            super.onPostExecute(result);
        }
    }

    void setFormData(FormData formData) {
        mFormData = formData;
    }


    void openWebActivity() {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", mFormData.getLink());
        intent.putExtra("titleName", "帮助说明");
        intent.putExtra("type", ConstantValue.TYPE_IMPORTANT);
        startActivity(intent);
    }

}
