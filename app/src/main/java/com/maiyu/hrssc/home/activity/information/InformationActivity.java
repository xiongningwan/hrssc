package com.maiyu.hrssc.home.activity.information;

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
 * 资讯
 */
public class InformationActivity extends BaseActivity {

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.divide_line2)
    View mDivideLine2;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private InfoFragmentPageAdapter mInfoFragmentPageAdapter;
    private InfoPageFragment[] fragments = new InfoPageFragment[4];

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        fragments[0] = InfoPageFragment.newInstance(1);
        fragments[1] = InfoPageFragment.newInstance(2);
        fragments[2] = InfoPageFragment.newInstance(3);
        fragments[3] = InfoPageFragment.newInstance(4);
    }

    @Override
    public void initData() {
        mHeadView.setTitle("资讯", true, false);
        mInfoFragmentPageAdapter = new InfoFragmentPageAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mInfoFragmentPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void initOnClick(View v) {

    }

    class InfoFragmentPageAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[]{"最新", "新闻", "公告", "通知"};
        private Context context;

        public InfoFragmentPageAdapter(FragmentManager fm, Context context) {
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
