package com.maiyu.hrssc.base.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;


public class HeadView extends RelativeLayout implements OnClickListener {

    private Context mContext;
    private View rightButton;
    private View leftButton;
    private TextView titleTextView;
    private OnClickListener onRightButtonClickListener;
    private TextView rightButtonText;

    public HeadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getUi(context);
    }

    public HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getUi(context);
    }

    public HeadView(Context context) {
        super(context);
        getUi(context);
    }

    private void getUi(Context context) {
        mContext = context;
        View view = View.inflate(context, R.layout.view_detail_header, this);

        titleTextView = (TextView) view.findViewById(R.id.title_text);
        leftButton = view.findViewById(R.id.title_left_button);
        rightButton = view.findViewById(R.id.title_right_button);
        rightButtonText = (TextView)view.findViewById(R.id.right_button_text);


        rightButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == leftButton) {
            ((Activity) mContext).finish();
        } else if (v == rightButton) {
            if (onRightButtonClickListener != null) {
                onRightButtonClickListener.onClick(v);
            }

        }
    }
    public void setOnRightButtonClickListener(OnClickListener l) {
        onRightButtonClickListener = l;
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
     * 设置rightButton是否能够点击
     */
    public void setRightButtonEnable(boolean rightButtonEnable) {
        rightButton.setEnabled(rightButtonEnable);
    }

    public int getRightButtonId() {
        return rightButton.getId();
    }
}
