package com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.view.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;


/**
 * Created by Aspsine on 2015/9/2.
 */
public class ClassicLoadMoreFooterView extends SwipeLoadMoreFooterLayout {
    private ProgressBar progressBar;

    private int mFooterHeight;

    public ClassicLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFooterHeight = 150;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onPrepare() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            progressBar.setVisibility(GONE);
        }
    }

    @Override
    public void onLoadMore() {
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        progressBar.setVisibility(GONE);
    }

    @Override
    public void onReset() {
        progressBar.setVisibility(GONE);
    }
}
