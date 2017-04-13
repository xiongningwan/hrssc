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
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.engine.IUserEngine;
import com.maiyu.hrssc.base.exception.NetException;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.activity.information.bean.NewsClass;
import com.maiyu.hrssc.util.BaseAsyncTask;
import com.maiyu.hrssc.util.EngineFactory;

import java.util.ArrayList;
import java.util.List;

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
       /* fragments[0] = InfoPageFragment.newInstance(1);
        fragments[1] = InfoPageFragment.newInstance(2);
        fragments[2] = InfoPageFragment.newInstance(3);
        fragments[3] = InfoPageFragment.newInstance(4);*/
    }

    @Override
    public void initData() {
        mHeadView.setTitle("资讯", true, false);


        String token = DataCenter.getInstance().getuser().getToken();
        new GetNewsClassAsyncTask(token).execute();
    }

    @Override
    public void initOnClick(View v) {

    }

    class InfoFragmentPageAdapter extends FragmentPagerAdapter {
        //final int PAGE_COUNT = 4;
        // private String tabTitles[] = new String[]{"最新", "新闻", "公告", "通知"};
        List<String> tableTitlesList = new ArrayList<>();
        List<InfoPageFragment> fragmentList = new ArrayList<>();
        private Context context;

        public InfoFragmentPageAdapter(FragmentManager fm, Context context, List<NewsClass> list) {
            super(fm);
            this.context = context;
            for (int i = 0; i < list.size(); i++) {
                NewsClass newsClass = list.get(i);
                tableTitlesList.add(newsClass.getName());
                fragmentList.add(InfoPageFragment.newInstance(newsClass.getId()));
            }

        }

        @Override
        public Fragment getItem(int position) {
           // return fragments[position];
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //return PAGE_COUNT;
            return tableTitlesList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
           // return tabTitles[position];
            return tableTitlesList.get(position);
        }

    }


    /**
     * 获取资讯分类
     */
    class GetNewsClassAsyncTask extends BaseAsyncTask<Void, Void, Void> {
        private String token;
        private List<NewsClass> list;

        public GetNewsClassAsyncTask(String token) {
            super();

            this.token = token;
        }

        @Override
        protected void onPreExecute() {
            //mLoadingDialog.getDialog().show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            IUserEngine engine = EngineFactory.get(IUserEngine.class);
            try {
                list = engine.getNewsClass(InformationActivity.this, token);
            } catch (NetException e) {
                exception = e;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // mLoadingDialog.getDialog().dismiss();
            if (checkException(InformationActivity.this)) {
                return;
            }
            if (list != null) {
                setData(list);
            }

            super.onPostExecute(result);
        }
    }

    private void setData(List<NewsClass> list) {
        mInfoFragmentPageAdapter = new InfoFragmentPageAdapter(getSupportFragmentManager(), this, list);
        mViewPager.setAdapter(mInfoFragmentPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0, true);
    }

}
