package com.maiyu.hrssc.base.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.adapter.CityAdapter;
import com.maiyu.hrssc.base.adapter.ZimuAdapter;
import com.maiyu.hrssc.base.bean.City;
import com.maiyu.hrssc.base.view.HeadView;
import com.maiyu.hrssc.home.frament.HomeFragment;
import com.maiyu.hrssc.util.HintUitl;
import com.maiyu.hrssc.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择城市列表
 */
public class CitysActivity extends BaseActivity {
    @BindView(R.id.head_view)
    HeadView mHeadView;
    @BindView(R.id.city_recycler_view)
    RecyclerView mCityRecyclerView;
    @BindView(R.id.zimu_recycler_view)
    RecyclerView mZimuRecyclerView;
    private CityAdapter mCityAdapter;
    private ZimuAdapter mZimuAdapter;
    ArrayList<City> mCitys;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_citys);
        ButterKnife.bind(this);
    }

    @Override
    public void initViews() {
        mHeadView.setTitle("办理城市", true, false);
        mCitys = getIntent().getParcelableArrayListExtra(HomeFragment.CITY_LIST);

        // 处理
        sortCitys(mCitys);

        mCityAdapter = new CityAdapter(this, new CitysOnclickLister());
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCityRecyclerView.setAdapter(mCityAdapter);
        mCityAdapter.setData(mCitys);




        mZimuAdapter = new ZimuAdapter(this, new ZimuOnclickLister());
        mZimuRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mZimuRecyclerView.setItemAnimator(new DefaultItemAnimator());



        String[] les = getResources().getStringArray(R.array.letter);
        List letterList = new ArrayList();
        for (int i = 0; i < les.length; i++) {
            letterList.add(les[i]);
        }
        mZimuAdapter.setData(letterList);

        mZimuRecyclerView.setAdapter(mZimuAdapter);
    }

    private void sortCitys(ArrayList<City> citys) {
        if(citys == null) {
            return;
        }


        Collections.sort(citys, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return (o1.getWord().toCharArray()[0] - o2.getWord().toCharArray()[0]);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {

    }


    class CitysOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
          City city = (City)v.getTag(R.id.key_tag_item_data);
            // sharepreference 保存城市

            String cityName = city.getCity();
            SharedPreferencesUtil.saveCityName(CitysActivity.this, cityName);
            Intent intent = new Intent();
            intent.putExtra("cityName",cityName);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    class ZimuOnclickLister implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String letter = (String)v.getTag(R.id.key_tag_item_data);
            HintUitl.toastShort(CitysActivity.this, ""+letter);
            // 遍历，查找列表中该匹配字母第一个
            if(mCitys == null) {
               return;
            }
            for(int i = 0; i < mCitys.size(); i++) {
                if(mCitys.get(i).getWord().toUpperCase().toCharArray()[0] == letter.toCharArray()[0]) {
                    mCityRecyclerView.scrollToPosition(i);
                    break;
                }
            }


        }
    }

}
