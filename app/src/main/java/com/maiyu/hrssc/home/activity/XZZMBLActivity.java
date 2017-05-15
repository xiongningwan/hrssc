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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.dagger.photopicker.PhotoPicker;

public class XZZMBLActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city)
    ImageView mCityIv;
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
    @BindView(R.id.spinnsr_view)
    Spinner mSpinner;

    private String mId;
    private String mTitle;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private FormData mFormData;
    private String mCity;
    private String mGet_way = "";

    private String mRecipient = "";
    private String mAddress = "";
    private String mAddress_info = "";
    private String mTpl_tid = "";
    private String mTpl_form = "";
    private List<String> mImageList = new ArrayList<String>();
    private List<String> mAttachFileList = new ArrayList<String>();
    private ArrayList<String> mImageListDisplays = new ArrayList<String>();
    private int mCount = 0;


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_xzzmbl);
        ButterKnife.bind(this);
        checkPermission();
    }

    @Override
    public void initViews() {
        mToken = DataCenter.getInstance().getuser().getToken();
        mCity = SharedPreferencesUtil.getCityName(this);

        mId = getIntent().getStringExtra("id");
        mTitle = getIntent().getStringExtra("name");
        mLoadingDialog = new LoadingDialog(this);
        mHeadView.setTitle(mTitle, true, true);

        mCityName.setText(mCity);

        TextView rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("提交");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(XZZMBLActivity.this, "提交", Toast.LENGTH_SHORT).show();
                doSubmit();
            }
        });


        final String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> mSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.custom_simple_spinner_item, languages);
        mSpinner.setAdapter(mSpinnerAdapter);

        setSpecialParam(mTitle);
    }


    /**
     * 提交
     */
    void doSubmit() {
        int set = SharedPreferencesUtil.getSpecialParamSet(this);
        String aid = "0";//如果是新的申请，aid缺省为0
        String type = "1";//0-保存草稿  1-提交申请
        String brief = mSimpleDescText.getText().toString(); // 描述
        String comment = mEditText.getText().toString(); // 备注
        String language = mSpinner.getSelectedItem().toString().equals("中文") ? "0" : "1";

        String paths = getSbString(mImageList);
        String attachs = getSbString(mAttachFileList);

        // 设置特殊字段不为空的情况
        if (1 == set || 2 == set || 3 == set || 4 == set || 7 == set || 8 == set || 9 == set) {
            if (brief.equals("")) {
                HintUitl.toastShort(this, "请填写描述");
                return;
            }
        }

        if (1 == set && comment.equals("")) {
            HintUitl.toastShort(this, "请填写备注");
            return;
        }

        if (mToken == null) {
            HintUitl.toastShort(this, "error: token为空!");
            return;
        }
        if (mCity == null) {
            HintUitl.toastShort(this, "error: 城市为空!");
            return;
        }
        if (mId == null) {
            HintUitl.toastShort(this, "error: 二级菜单id为空!");
            return;
        }
        if (mGet_way == null || mGet_way.equals("")) {
            HintUitl.toastShort(this, "请选择领取方式");
            return;
        }

        new SubmitApplyAsyncTask(aid, mToken, type, mCity, mId, mGet_way,
                mAddress, mAddress_info, mRecipient, mTpl_tid, mTpl_form,
                brief, comment, language, paths, attachs).execute();
    }


    String getSbString(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            sb.append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); //调用 字符串的deleteCharAt() 方法,删除最后一个多余的
        }
        return sb.toString();
    }


    @Override
    public void initData() {
        new Category2AsyncTask(mToken, mId, mCity).execute();
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
                //startActivityForResult(new Intent(this, ChooseTempleActivity.class), 102);
                if (mFormData.getTemplates() != null) {
                    Intent intent = new Intent(this, ChooseTempleActivity.class);
                    intent.putParcelableArrayListExtra("templates", (ArrayList<? extends Parcelable>) mFormData.getTemplates());
                    startActivityForResult(intent, 102);
                }
                break;
            case R.id.choose_language_rl:
                //  startActivityForResult(new Intent(this, XZZMBLActivity.class), 102);

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
                //mPickImageView.updatePickImageView(allSelectedPicture);
                mImageList.clear();
                mImageListDisplays.clear();
                for (int i = 0; i < allSelectedPicture.size(); i++) {
                    File file = new File(allSelectedPicture.get(i));
                    new uploadPicture(mToken, file).execute();
                }

            } else if (requestCode == 101 && data != null) {
                setGetWay(data);

            } else if (requestCode == 102) {
                setModule(data);

            } else if (requestCode == 104) {
                mSimpleDescText.setText(data != null ? data.getStringExtra(EtTextActivity.TEXT) : "");
            }

        }
    }

    void setGetWay(Intent data) {
        if (data == null) {
            return;
        }
        String style = data.getStringExtra("style");
        if ("自助领取".equals(style)) {
            mGet_way = "0";
        } else if ("邮寄".equals(style)) {
            mGet_way = "1";
        } else if ("自助打印".equals(style)) {
            mGet_way = "2";
        }
        mGetStyleText.setText(style);
        SelfAddress selfAddress = (SelfAddress) data.getParcelableExtra("selfAddress");
        if (selfAddress != null) {
            mAddress = selfAddress.getAddress();
            mAddress_info = selfAddress.getAddress_info();
            HintUitl.toastShort(this, selfAddress.getAddress());
        }

        mRecipient = data.getStringExtra("recipient");
        if (mRecipient == null) {
            mRecipient = "";
        }
    }

    void setModule(Intent data) {
        if (data == null) {
            return;
        }
        String tpl_name = data.getStringExtra("tpl_Name");
        mTpl_tid = data.getStringExtra("tpl_tid");
        mTpl_form = data.getStringExtra("tpl_form");
        mChooseMouldText.setText(tpl_name);
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
                str = engine.submitApply(XZZMBLActivity.this, aid, token, type, city, cid2, get_way,
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
            if (checkException(XZZMBLActivity.this)) {
                return;
            }
            if (str != null) {
                HintUitl.toastShort(XZZMBLActivity.this, str);
                startActivity(new Intent(XZZMBLActivity.this, SucceedActivity.class));
            }

            super.onPostExecute(result);
        }
    }


    /**
     * 上传图片的统一调用此接口
     */
    class uploadPicture extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private File file;
        private String path;

        public uploadPicture(String token, File file) {
            super();
            this.token = token;
            this.file = file;
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
                path = engine.uploadPicture(XZZMBLActivity.this, token, file);
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
            if (path != null) {

                mImageList.add(path);
                mImageListDisplays.add(file.getPath());
                mPickImageView.updatePickImageView(mImageListDisplays);
            }

            super.onPostExecute(result);
        }
    }


    void setSpecialParam(String title) {

        if ("户口卡借用".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 1);
            mEditText.setHint("是否使用集体户：是/否");
        } else if ("市内户口迁入".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 2);
        } else if ("户口迁出至市内".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 3);
        } else if ("市外户口迁入".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 4);
        } else if ("学位验证".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 5);
        } else if ("工卡照片".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 6);
        } else if ("预约入职".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 7);
        } else if ("居住证办理".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 8);
        } else if ("档案借阅".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 9);
        }
    }


}
