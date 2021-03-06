package com.maiyu.hrssc.home.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.maiyu.hrssc.base.view.dialog.LoadingDialog;
import com.maiyu.hrssc.home.activity.applying.bean.ApplyDetail;
import com.maiyu.hrssc.home.activity.applying.bean.FindApplyDetailData;
import com.maiyu.hrssc.home.activity.applying.dialog.DraftsDialog;
import com.maiyu.hrssc.home.bean.FormData;
import com.maiyu.hrssc.home.bean.SelfAddress;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.BitmapUtils;
import com.maiyu.hrssc.util.EngineFactory;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.dagger.photopicker.PhotoPicker;

import static com.maiyu.hrssc.R.id.choose_language_rl;
import static com.maiyu.hrssc.R.id.choose_mould_rl;
import static com.maiyu.hrssc.R.id.choose_mould_text;
import static com.maiyu.hrssc.R.id.get_style_text;
import static com.maiyu.hrssc.R.id.pcik_image_view;
import static com.maiyu.hrssc.R.id.simple_desc_text;

public class XZZMBLActivity extends BaseActivity {

    @BindView(R.id.title_left_button)
    RelativeLayout mTitleLeftButton;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.title_right_button)
    RelativeLayout mTitleRightButton;
    @BindView(R.id.right_button_text)
    TextView mRightButtonText;
    @BindView(R.id.city)
    ImageView mCityIv;
    @BindView(R.id.city_name)
    TextView mCityName;
    @BindView(R.id.blsm_rl)
    RelativeLayout mBlsmRl;
    @BindView(get_style_text)
    TextView mGetStyleText;
    @BindView(R.id.xh)
    ImageView mXh;
    @BindView(R.id.get_style_rl)
    RelativeLayout mGetStyleRl;
    @BindView(choose_mould_text)
    TextView mChooseMouldText;
    @BindView(R.id.choose_mould_xh)
    ImageView mChooseMouldXh;
    @BindView(choose_mould_rl)
    RelativeLayout mChooseMouldRl;
    @BindView(R.id.choose_language_text)
    TextView mChooseLanguageText;
    @BindView(R.id.choose_language_xh)
    ImageView mChooseLanguageXh;
    @BindView(choose_language_rl)
    RelativeLayout mChooseLanguageRl;
    @BindView(simple_desc_text)
    TextView mSimpleDescText;
    @BindView(R.id.simple_desc_xh)
    ImageView mSimpleDescXh;
    @BindView(R.id.simple_desc_rl)
    RelativeLayout mSimpleDescRl;
    @BindView(R.id.desc_lable_textView)
    TextView mDescLableTextView;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(pcik_image_view)
    PickImageView mPickImageView;
    @BindView(R.id.spinnsr_view)
    Spinner mSpinner;

    @BindView(R.id.desc_image_divider)
    View mDescImageDivider;
    @BindView(R.id.language_desc_divider)
    View mLanguageDescDivider;
    @BindView(R.id.mould_language_divider)
    View mMouldLanguageDivider;
    @BindView(R.id.style_mould_divider)
    View mStyleMouldDivider;
    @BindView(R.id.tupianxuantian)
    TextView mTupianxuantian;
    @BindView(R.id.line_beizhu)
    View mLine_beizhu;


    private String mId;
    private String mTitle;
    private LoadingDialog mLoadingDialog;
    private String mToken;
    private FormData mFormData;
    private String mCity;
    private String mGet_way = "4";

    private String mRecipient = "";
    private String mAddress = "";
    private String mAddress_info = "";
    private String mTpl_tid = "";
    private String mTpl_form = "";
    private List<String> mImageList = new ArrayList<String>();
    private List<String> mAttachFileList = new ArrayList<String>();
    private ArrayList<String> mImageListDisplays = new ArrayList<String>();
    private int mCount = 0;
    private DraftsDialog mDialog;
    private String mAid = "0"; // 申请id，如果是【已驳回】【重新提交】或者【草稿箱】里面重新提交，这个时候需传递申请id,后台会做一个修改操作。如果是新的申请，aid缺省为0
    private int mYear;
    private int mMonth;
    private int mDay;

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
        mTitleText.setText(mTitle);

        mCityName.setText(mCity);

        mRightButtonText.setText("提交");
        mRightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        mTitleRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(XZZMBLActivity.this, "提交", Toast.LENGTH_SHORT).show();
                String type = "1";//0-保存草稿  1-提交申请
                doSubmit(type);
            }
        });


        mTitleLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipDraftDialog();
            }
        });


        final String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> mSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.custom_simple_spinner_item, languages);
        mSpinner.setAdapter(mSpinnerAdapter);

        setSpecialParam(mTitle);


        // 如果是草稿箱来的
        mAid = getIntent().getStringExtra("aid");
        if (mAid != null) {
            new FindApplyDataDataAsyncTask(mToken, mAid).execute();
        } else {
            mAid = "0"; // 新申请
        }


        mPickImageView.setUpdateOterDataListener(new PickImageView.UpdateOuterDataListener() {
            @Override
            public void updateData(int postion) {
                mImageList.remove(postion);
                mImageListDisplays.remove(postion);
            }
        });
    }


    /**
     * 提交
     */
    void doSubmit(String type) {
        int set = SharedPreferencesUtil.getSpecialParamSet(this);
        String brief = "";
        if (!"预约入职".equals(mTitle)) {
            brief = mSimpleDescText.getText().toString(); // 描述
        } else {
            brief = mYear + "-" + mMonth + "-" + mDay; // 描述
        }


        String comment = mEditText.getText().toString(); // 备注


        String language = getLaguageParam();

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

        new SubmitApplyAsyncTask(mAid, mToken, type, mCity, mId, mGet_way,
                mAddress, mAddress_info, mRecipient, mTpl_tid, mTpl_form,
                brief, comment, language, paths, attachs).execute();
    }


    String getLaguageParam() {
        String languageStr = mSpinner.getSelectedItem().toString();
        if (languageStr.equals("中文")) {
            return "0";
        } else if (languageStr.equals("英文")) {
            return "1";
        } else {
            return "2";
        }
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

    @OnClick({R.id.blsm_rl, R.id.get_style_rl, choose_mould_rl, choose_language_rl, R.id.simple_desc_rl})
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
            case choose_mould_rl:
                //startActivityForResult(new Intent(this, ChooseTempleActivity.class), 102);
                if (mFormData.getTemplates() != null) {
                    Intent intent = new Intent(this, ChooseTempleActivity.class);
                    intent.putParcelableArrayListExtra("templates", (ArrayList<? extends Parcelable>) mFormData.getTemplates());
                    startActivityForResult(intent, 102);
                }
                break;
            case choose_language_rl:
                //  startActivityForResult(new Intent(this, XZZMBLActivity.class), 102);

                break;
            case R.id.simple_desc_rl:
                if (!"预约入职".equals(mTitle)) {
                    startActivityForResult(new Intent(this, EtTextActivity.class), 104);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapUtils.deleteCacheFile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_SELECTED) {
                ArrayList<String> allSelectedPicture = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
                // mPickImageView.updatePickImageView(allSelectedPicture);
                //  Log.i("PickImageView", "allSelectedPicture:" + allSelectedPicture.size());
                //mImageList.clear();
                // mImageListDisplays.clear();
                // Log.i("REQUEST_SELECTED", "allSelectedPicture:"+allSelectedPicture.size() +"***" + allSelectedPicture.toString());
                ArrayList<String> temPathList = new ArrayList<>();
                for(int i = 0; i < allSelectedPicture.size(); i++) {
                    String tempPath = BitmapUtils.compressImageUpload(allSelectedPicture.get(i));
                    temPathList.add(tempPath);
                }



                for (int i = 0; i < temPathList.size(); i++) {
                    File file = new File(temPathList.get(i));
                    if (file.getPath().contains("http")) {
                        continue;
                    }
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

    boolean getSetFirstCamera() {
        SharedPreferences sp = getSharedPreferences("user_info_camera", Context.MODE_PRIVATE);
        long cameraFalg = sp.getLong("CAMERA", 0);
        Log.i("PickImageView", "cameraFalg:" + cameraFalg);
        if (1 == cameraFalg) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("CAMERA", 0);
            editor.commit();
        }

        mCount++;
        Log.i("PickImageView", "mCount:" + mCount);
        if (mCount == 1 && cameraFalg == 1) {
            return true;
        } else {
            return false;
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
            HintUitl.toastShort(this, selfAddress.getAddress_info());
        }

        mRecipient = data.getStringExtra("recipient");
        if (mRecipient == null) {
            mRecipient = "";
        } else {
            HintUitl.toastShort(this, mRecipient);
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
                finish();

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
                //    Log.i("REQUEST_SELECTED", "mImageListDisplays:"+mImageListDisplays.size() +"***" + mImageListDisplays.toString());
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
            // 为了和ios一样  隐藏 领取方式  选择模版 开具语言
            mGetStyleRl.setVisibility(View.GONE);
            mChooseMouldRl.setVisibility(View.GONE);
            mChooseLanguageRl.setVisibility(View.GONE);
            mSimpleDescRl.setVisibility(View.GONE);
            mDescImageDivider.setVisibility(View.GONE);
            mLanguageDescDivider.setVisibility(View.GONE);
            mMouldLanguageDivider.setVisibility(View.GONE);
            mStyleMouldDivider.setVisibility(View.GONE);
            mTupianxuantian.setText("图片");
            mLine_beizhu.setVisibility(View.GONE);
            mEditText.setHint("");

        } else if ("工卡照片".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 6);
            // 为了和ios一样  隐藏 领取方式  选择模版 开具语言
            mGetStyleRl.setVisibility(View.GONE);
            mChooseMouldRl.setVisibility(View.GONE);
            mChooseLanguageRl.setVisibility(View.GONE);
            mSimpleDescRl.setVisibility(View.GONE);
            mDescImageDivider.setVisibility(View.GONE);
            mLanguageDescDivider.setVisibility(View.GONE);
            mMouldLanguageDivider.setVisibility(View.GONE);
            mStyleMouldDivider.setVisibility(View.GONE);
            mTupianxuantian.setText("图片");
            mLine_beizhu.setVisibility(View.GONE);
            mEditText.setHint("");
        } else if ("预约入职".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 7);
            // 为了和ios一样  隐藏 领取方式  选择模版 开具语言
            mGetStyleRl.setVisibility(View.GONE);
            mChooseMouldRl.setVisibility(View.GONE);
            mChooseLanguageRl.setVisibility(View.GONE);
            mPickImageView.setVisibility(View.GONE);

            mDescImageDivider.setVisibility(View.GONE);
            mLanguageDescDivider.setVisibility(View.GONE);
            mMouldLanguageDivider.setVisibility(View.GONE);
            mStyleMouldDivider.setVisibility(View.GONE);
            mTupianxuantian.setText("图片");
            mTupianxuantian.setVisibility(View.GONE);
            mLine_beizhu.setVisibility(View.GONE);
            mEditText.setHint("");

            mDescLableTextView.setText("预约入职日期");
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            mSimpleDescText.setText("请选择日期");
            mSimpleDescRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(XZZMBLActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            // TODO Auto-generated method stub
                            mYear = year;
                            mMonth = month;
                            mDay = day;
                            //更新EditText控件日期 小于10加0
                            mSimpleDescText.setText(new StringBuilder()
                                    .append(mYear)
                                    .append("年")
                                    .append((mMonth + 1) < 10 ? 0 + (mMonth + 1) : (mMonth + 1))
                                    .append("月")
                                    .append((mDay < 10) ? 0 + mDay : mDay)
                                    .append("日"));
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });


        } else if ("居住证办理".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 8);
        } else if ("档案借阅".equals(title)) {
            SharedPreferencesUtil.saveSpecialParamSet(this, 9);
        } else {
            SharedPreferencesUtil.saveSpecialParamSet(this, 10);
        }
    }

    void tipDraftDialog() {
        if (mDialog == null) {
            mDialog = new DraftsDialog(this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 保存草稿
                    mDialog.closeDialog();
                    String type = "0";//0-保存草稿  1-提交申请
                    doSubmit2(type);
                }
            });
        }
        mDialog.show();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        tipDraftDialog();
    }


    /**
     * 提交到草稿箱
     */
    void doSubmit2(String type) {
        int set = SharedPreferencesUtil.getSpecialParamSet(this);
        String brief = "";
        if (!"预约入职".equals(mTitle)) {
            brief = mSimpleDescText.getText().toString(); // 描述
        } else {
            brief = mYear + "-" + mMonth + "-" + mDay; // 描述
        }

        String comment = mEditText.getText().toString(); // 备注
        String language = getLaguageParam();

        String paths = getSbString(mImageList);
        String attachs = getSbString(mAttachFileList);


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

        new SubmitApplyAsyncTask(mAid, mToken, type, mCity, mId, mGet_way,
                mAddress, mAddress_info, mRecipient, mTpl_tid, mTpl_form,
                brief, comment, language, paths, attachs).execute();
    }


    /**
     * 获取业务详情
     */
    class FindApplyDataDataAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private String aid;
        private FindApplyDetailData findApplyDetailData;

        public FindApplyDataDataAsyncTask(String token, String aid) {
            super();

            this.token = token;
            this.aid = aid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IBizEngine engine = EngineFactory.get(IBizEngine.class);
            try {
                findApplyDetailData = engine.findApplyDetail(XZZMBLActivity.this, token, aid);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (checkException(XZZMBLActivity.this)) {
                return;
            }
            if (findApplyDetailData != null) {
                setData(findApplyDetailData);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(FindApplyDetailData findApplyDetailData) {
        ApplyDetail applyDetail = findApplyDetailData.getApply();
        if (applyDetail != null) {
            setGetWay(applyDetail);
            setTemple(applyDetail);
            setLanguage(applyDetail);
            mSimpleDescText.setText(applyDetail.getBrief());
            mEditText.setText(applyDetail.getComment());
            setImages(applyDetail.getImages());
        }
    }

    void setGetWay(ApplyDetail applyDetail) {
        // 领取方式：0:自取 1:邮寄, 2打印
        if ("0".equals(applyDetail.getGet_way())) {
            mGetStyleText.setText("自助领取");
            mGet_way = "0";

        } else if ("1".equals(applyDetail.getGet_way())) {
            mGetStyleText.setText("邮寄");
            mGet_way = "1";

        } else if ("2".equals(applyDetail.getGet_way())) {
            mGetStyleText.setText("自助打印");
            mGet_way = "2";
        }
    }

    void setTemple(ApplyDetail applyDetail) {
        mChooseMouldText.setText(applyDetail.getTpl_name());
        mTpl_tid = applyDetail.getTpl_tid();
        mTpl_form = applyDetail.getTpl_form();
    }

    void setLanguage(ApplyDetail applyDetail) {
        //语言：0中文 1英文 2中英文
        if ("0".equals(applyDetail.getLanguage())) {
            mSpinner.setSelection(0);
        } else if ("1".equals(applyDetail.getLanguage())) {
            mSpinner.setSelection(1);
        } else {
            mSpinner.setSelection(2);
        }
    }

    void setImages(String images) {
        if (images == null) {
            return;
        }
        ArrayList<String> imageList = new ArrayList<String>();
        String[] imageArr = images.split(";");
        for (int i = 0; i < imageArr.length; i++) {
            if (!imageArr[i].contains("http")) {
                imageList.add(ConstantValue.FILE_SERVER_URI + imageArr[i]);
            } else {
                imageList.add(imageArr[i]);
            }
        }
        mImageList.clear();
        mImageList.addAll(imageList);
        mImageListDisplays.clear();
        mImageListDisplays.addAll(imageList);
        mPickImageView.updatePickImageView(imageList);
    }

}
