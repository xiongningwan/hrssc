package com.maiyu.hrssc.home.activity.applying;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.activity.BaseActivity;
import com.maiyu.hrssc.base.view.HeadView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申请
 */
public class ApplyingActivity extends BaseActivity implements DQSFragment.OnFragmentInteractionListener{

    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.divide_line2)
    View mDivideLine2;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private ApplyingFragmentPageAdapter mApplyingFragmentPageAdapter;

    private  final int COUNT = 6;
    private Fragment[] fragments = new Fragment[COUNT];
    private TextView mRightButtonText;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_applying);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        fragments[0] = DQSFragment.newInstance(1);
        fragments[1] = DBLFragment.newInstance(2);
        fragments[2] = DLQFragment.newInstance(3);
        fragments[3] = DPJFragment.newInstance(4);
        fragments[4] = YWCFragment.newInstance(5);
        fragments[5] = BHFragment.newInstance(6);
    }

    @Override
    public void initData() {
        mHeadView.setTitle("我的申请", true, true);
        mRightButtonText = mHeadView.getRightButtonText();
        mRightButtonText.setText("草稿箱(0)");
        mRightButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplyingActivity.this, DraftActivity.class));
            }
        });


        mApplyingFragmentPageAdapter = new ApplyingFragmentPageAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mApplyingFragmentPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void initOnClick(View v) {

    }

    @Override
    public void onFragmentInteraction(int count) {
        mRightButtonText.setText("草稿箱("+count+")");
    }

    class ApplyingFragmentPageAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = COUNT;
        private String tabTitles[] = new String[]{"待审核", "待办理", "待领取", "待评价", "已完成", "已驳回"};
        private Context context;

        public ApplyingFragmentPageAdapter(FragmentManager fm, Context context) {
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
