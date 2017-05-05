package com.maiyu.hrssc.home.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.adapter.TempleInputAdapter;
import com.maiyu.hrssc.home.bean.Template;
import com.maiyu.hrssc.util.HintUitl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteTempleActivity extends BaseActivity {


    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.temple_pattern)
    TextView mTemplePattern;
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    private TempleInputAdapter mAdapter;

    private TextView mRightButtonText;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_write_temple);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        String titleName = getIntent().getStringExtra("title_name");
        mHeadView.setTitle(titleName, true, true);
        mRightButtonText = mHeadView.getRightButtonText();
        mRightButtonText.setText("保存");
        mRightButtonText.setTextColor(ContextCompat.getColor(this, R.color.project_color_general_hyperlink));
        mRightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HintUitl.toastShort(WriteTempleActivity.this, "保存");
            }
        });


        mAdapter = new TempleInputAdapter(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        Template template = (Template) getIntent().getParcelableExtra("template");
        if (template != null) {
            mTemplePattern.setText(Html.fromHtml(template.getTemplate()));
            mAdapter.setData(setData(template));
        }
    }

    @Override
    public void initOnClick(View v) {

    }


    List<String> setData(Template template) {
        List<String> list = new ArrayList<>();
        if (template.getForm() != null) {
            String[] itemArr = template.getForm().split(";");
            for (int i = 0; i < itemArr.length; i++) {
                String lable = itemArr[i].substring(0, itemArr[i].indexOf("="));
                list.add(lable);
            }
        }
        return list;
    }

}
