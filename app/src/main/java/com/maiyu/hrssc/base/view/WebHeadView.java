package com.maiyu.hrssc.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;


public class WebHeadView extends RelativeLayout implements OnClickListener {

    private Context mContext;
    private View rightButton;
    private View leftButton;
    private TextView titleTextView;
    private boolean isCloseTip;
    private OnClickListener onRightButtonClickListener;

    public WebHeadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getUi(context);
    }

    public WebHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getUi(context);
    }

    public WebHeadView(Context context) {
        super(context);
        getUi(context);
    }

    private void getUi(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.view_detail_web_header, this);

        rightButton = view.findViewById(R.id.title_right_button);
        titleTextView = (TextView) view.findViewById(R.id.title_text);
        leftButton = view.findViewById(R.id.title_left_button);

        rightButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    public void setOnRightButtonClickListener(OnClickListener l) {
        onRightButtonClickListener = l;
    }


    /**
     * 是否弹窗提示
     * @param isCloseTip
     */
    public void setCloseTip(boolean isCloseTip) {
        this.isCloseTip = isCloseTip;
    }

    /**
     * 设置详情 head 的 title 文字
     * @param title
     */
    public void setTitle(String title, boolean leftButtonVisible, boolean rightButtonVisible) {
        titleTextView.setText(title);

        if (leftButtonVisible) {
            leftButton.setVisibility(View.VISIBLE);
        } else {
            leftButton.setVisibility(View.GONE);
        }

        if (rightButtonVisible) {
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.GONE);
        }
    }

    /**
     * 设置详情 head 的 title 文字
     * @param title
     */
    public void setTitle(String title, String rightButtonName, boolean leftButtonVisible, boolean rightButtonVisible) {
        titleTextView.setText(title);

        if (leftButtonVisible) {
            leftButton.setVisibility(View.VISIBLE);
        } else {
            leftButton.setVisibility(View.GONE);
        }

        if (rightButtonVisible) {
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.GONE);
        }
    }

    /**
     * 设置详情 head 的 title 文字
     * @param title
     */
    public void setTitle(String title, boolean leftButtonVisible, boolean rightButtonVisible, boolean rightButtonEnable) {
        titleTextView.setText(title);

        if (leftButtonVisible) {
            leftButton.setVisibility(View.VISIBLE);
        } else {
            leftButton.setVisibility(View.GONE);
        }

        if (rightButtonVisible) {
            rightButton.setVisibility(View.VISIBLE);
        } else {
            rightButton.setVisibility(View.GONE);
        }
        rightButton.setEnabled(rightButtonEnable);
    }

    /**
     * 获取标题
     * @return
     */
    public String getTitle() {
        return titleTextView.getText().toString();
    }

    /**
     * 设置rightButton是否能够点击
     */
    public void setRightButtonEnable(boolean rightButtonEnable) {
        rightButton.setEnabled(rightButtonEnable);
    }

    public int getRightButtonId() {
        return rightButton.getId();
    }
    
    
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;

  /*  public void onCall(){
           if (Build.VERSION.SDK_INT >= 23) {
               int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext,Manifest.permission.CALL_PHONE);
               if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
            	   ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
                   return;
               }else{
                   //上面已经写好的拨号方法
            	   AppUtil.phoneCall(mContext);
               }
           } else {
               //上面已经写好的拨号方法
        	   AppUtil.phoneCall(mContext);
           }
       }
    */
    
}
