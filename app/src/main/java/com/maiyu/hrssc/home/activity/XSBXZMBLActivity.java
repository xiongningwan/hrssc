package com.maiyu.hrssc.home.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.image.PickImageView;
import com.maiyu.hrssc.base.view.HeadView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.dagger.photopicker.PhotoPicker;

/**
 * 现实表现
 */
public class XSBXZMBLActivity extends BaseActivity {

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


    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_xzzmbl);
        ButterKnife.bind(this);
        checkPermission();
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("现实表现证明办理", true, true);
        TextView rightButtonText = mHeadView.getRightButtonText();
        rightButtonText.setText("提交");
        rightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        rightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(XZZMBLActivity.this, "提交", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(XSBXZMBLActivity.this, SucceedActivity.class));
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }

    @OnClick({R.id.blsm_rl, R.id.get_style_rl, R.id.choose_mould_rl, R.id.choose_language_rl, R.id.simple_desc_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blsm_rl:

                break;
            case R.id.get_style_rl:
                startActivityForResult(new Intent(this, GetStyleActivity.class), 101);

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

            } else if (requestCode == 102) {


            } else if (requestCode == 104) {
                mSimpleDescText.setText(data!=null?data.getStringExtra(EtTextActivity.TEXT):"");
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
                            ActivityCompat.requestPermissions(XSBXZMBLActivity.this, new String[]{permission}, requestCode);
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


}
