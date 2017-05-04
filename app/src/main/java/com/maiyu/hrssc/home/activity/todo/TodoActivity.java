package com.maiyu.hrssc.home.activity.todo;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待办
 */
public class TodoActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.divide_line2)
    View mDivideLine2;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private TodoFragmentPageAdapter mTodoFragmentPageAdapter;
    private TodoFragment[] fragments = new TodoFragment[3];

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_todo);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        fragments[0] = TodoFragment.newInstance(0);
        fragments[1] = TodoFragment.newInstance(1);
        fragments[2] = TodoFragment.newInstance(3);
    }

    @Override
    public void initData() {
        mHeadView.setTitle("合同签署", true, false);
        mTodoFragmentPageAdapter = new TodoFragmentPageAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mTodoFragmentPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void initOnClick(View v) {

    }

    class TodoFragmentPageAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"待签署", "待盖章", "已完成"};
        private Context context;

        public TodoFragmentPageAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }

}
