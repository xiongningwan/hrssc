package com.maiyu.hrssc.base.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.activity.WebActivity;
import com.maiyu.hrssc.base.bean.Adv;
import com.maiyu.hrssc.base.bean.Banners;
import com.maiyu.hrssc.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 广告轮播的显示view
 *
 * @author xiongning
 *         <p>
 *         2015-8-31
 */
public class AdvertisementImageBanner extends RelativeLayout implements OnPageChangeListener {
    private Context mContext;
    private ViewPager mBannerPagerView;
    private LinearLayout mBannnerIndicatorIconView;
    //private static final String selectedColor = "#aa00badf";
    //private static final String unselectedColor = "#aa333333";
    private static final String selectedColor = "#129aee";
    private static final String unselectedColor = "#bbbbbf";
    private static final int INTERVAL = 5500;
    private static final int SCROLL_WHAT = 0;
    List<String> mPictureAddresses = new ArrayList<String>();
    List<String> mTitle = new ArrayList<String>();
    List<String> mURl = new ArrayList<String>();

    List<String> mAdDesc = new ArrayList<String>();


    private List<ImageView> mCacheImageViews = new ArrayList<ImageView>();
    private ScrollHandler mHandler;
    private BannerViewPagerAdapter bannerViewPagerAdapter;
    private boolean mIsAutoScroll;
    private TextView mBannnerIndicatorTitleTextView;
    public static final String TAG = "AdvImageBanner";
    public int type;

    public AdvertisementImageBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inintView(context);
    }

    public AdvertisementImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        inintView(context);
    }

    public AdvertisementImageBanner(Context context) {
        super(context);
        inintView(context);
    }

    private void inintView(Context context) {
        mContext = context;
        inflateChildView();
        getChildView();

        mHandler = new ScrollHandler();
        bannerViewPagerAdapter = new BannerViewPagerAdapter();
        mBannerPagerView.setAdapter(bannerViewPagerAdapter);
        mBannerPagerView.setOnPageChangeListener(this);
    }

    private void inflateChildView() {
        View.inflate(mContext, R.layout.view_ad_image_view, this);

    }

    private void getChildView() {
        mBannerPagerView = (ViewPager) findViewById(R.id.vp_detail_banner_pager);
        mBannnerIndicatorIconView = (LinearLayout) findViewById(R.id.ll_detail_bannner_indicator_icon);
        mBannnerIndicatorTitleTextView = (TextView) findViewById(R.id.ad_language_2);
    }

    public void setViewData(ArrayList<Banners> arrayList) {
        mPictureAddresses.clear();
        mTitle.clear();
        mURl.clear();
        // mAdDesc.clear();


        mPictureAddresses.addAll(transformImageData(arrayList));
        mTitle.addAll(transformTitleData(arrayList));
        mURl.addAll(transformURLData(arrayList));
        //  mAdDesc.addAll(transformAdDescData(arrayList));


        mBannnerIndicatorIconView.removeAllViews();
        View iconView = null;
        int width = mContext.getResources().getDimensionPixelSize(R.dimen.base8dp);
        int height = mContext.getResources().getDimensionPixelSize(R.dimen.base8dp);
        int widthGap = mContext.getResources().getDimensionPixelSize(R.dimen.base8dp);

        if (mPictureAddresses.size() > 1) {
            for (int i = 0; i < mPictureAddresses.size(); i++) {
                iconView = new View(mContext);

                if (i == 0) {
                    iconView.setBackgroundResource(R.mipmap.yuan_lan);
                } else {
                    iconView.setBackgroundResource(R.mipmap.yuan_bai);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                params.setMargins(widthGap, 0, 0, 0);
                iconView.setLayoutParams(params);
                mBannnerIndicatorIconView.addView(iconView);
            }
        }

        bannerViewPagerAdapter.notifyDataSetChanged();
        triggerScroll();
    }

    /**
     * 是否有指示器
     *
     * @param b
     */
    public void setIsIndicator(boolean b) {
        if (b) {
            mBannnerIndicatorIconView.setVisibility(View.VISIBLE);

        } else {
            mBannnerIndicatorIconView.setVisibility(View.GONE);

        }
    }

    /**
     * 设置 ViewPager 是否可以自动滚动
     */
    public void setAutoScroll(boolean roll) {
        mIsAutoScroll = roll;
        triggerScroll();
    }

    private void triggerScroll() {
        if (mIsAutoScroll && mPictureAddresses.size() > 1) {
            mHandler.removeMessages(SCROLL_WHAT);
            mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
        } else {
            mHandler.removeMessages(SCROLL_WHAT);
        }
    }

    /**
     * 自动滚动的消息 handler
     *
     * @author Jack
     */
    public class ScrollHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            next();
            if (mIsAutoScroll && mPictureAddresses.size() > 1) {
                // mPictureAddresses > 1，并且开启了自动滚动才会自动滚动
                mHandler.removeMessages(SCROLL_WHAT);
                mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
            }
        }
    }

    /**
     * 设置 ViewPager 显示到下一个 itemView
     */
    private void next() {
        int item = mBannerPagerView.getCurrentItem() + 1;
        mBannerPagerView.setCurrentItem(item, true);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(SCROLL_WHAT);
                break;

            case MotionEvent.ACTION_UP:
                if (mIsAutoScroll) {
                    mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                if (mIsAutoScroll) {
                    mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);

    }

    /*    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 触摸的时候停止自动滚动
        Log.d(TAG, "ev.getActionMasked:"+ev.getActionMasked());
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN"+"mIsAutoScroll:"+mIsAutoScroll);
                mHandler.removeMessages(SCROLL_WHAT);
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP"+"mIsAutoScroll:"+mIsAutoScroll);
                if (mIsAutoScroll) {
                    mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "ACTION_CANCEL"+"mIsAutoScroll:"+mIsAutoScroll);
                if (mIsAutoScroll) {
                    mHandler.sendEmptyMessageDelayed(SCROLL_WHAT, INTERVAL);
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }*/

    public class BannerViewPagerAdapter extends PagerAdapter {
        private int mChildCount = 0;

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if (mPictureAddresses.size() <= 1) {
                return 1;
            }
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (position > 1) {
                position = position % mPictureAddresses.size();
            }
            // LogHelper.i(TAG, "remove position:" + position);
            container.removeView((View) object);
            mCacheImageViews.add((ImageView) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount > 0) {
                mChildCount--;
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            if (position > 1) {
                position = position % mPictureAddresses.size();
            }

            if (mCacheImageViews.isEmpty()) {
                imageView = new ImageView(mContext);
                imageView.setScaleType(ScaleType.FIT_XY);
                imageView.setImageResource(R.mipmap.user_profile_image_default);

                // 在加上点击事件
                imageView.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (mURl.size() > 0) {
                            int currentPositon = mBannerPagerView.getCurrentItem() % mURl.size();

                            if (mURl.get(currentPositon) != null && !mURl.get(currentPositon).equals("")) {

                                Intent intent = new Intent(mContext, WebActivity.class);
                                intent.putExtra("url", mURl.get(currentPositon));
                                intent.putExtra("titleName", mTitle.get(currentPositon));
                                intent.putExtra("type", ConstantValue.TYPE_ORDINARY);
                                mContext.startActivity(intent);
                            }

                        }

                    }
                });

                //  LogHelper.i(TAG, "new imageview");
            } else {
                imageView = mCacheImageViews.remove(0);
                //   LogHelper.i(TAG, "get imageview:" + imageView.toString());
            }

            if (mPictureAddresses.size() > 0) {
                ImageLoaderUtil.loadImage(imageView, mPictureAddresses.get(position),
                        R.mipmap.user_profile_image_default, R.mipmap.user_profile_image_default,
                        R.mipmap.user_profile_image_default);
            }

            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

            //LogHelper.i(TAG, "add position:" + position);
            //  LogHelper.i(TAG, "current position:" + mBannerPagerView.getCurrentItem());

            container.addView(imageView);
            return imageView;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置当前位置图片指示器
        for (int i = 0; i < mBannnerIndicatorIconView.getChildCount(); i++) {
            View view = mBannnerIndicatorIconView.getChildAt(i);
            view.setBackgroundResource(R.mipmap.yuan_bai);
        }
        if (mBannnerIndicatorIconView.getChildCount() > 1) {
            position = position % mPictureAddresses.size();
            mBannnerIndicatorIconView.getChildAt(position).setBackgroundResource(R.mipmap.yuan_lan);
        }

        if (mTitle.size() > 0) {
            position = position % mTitle.size();
            mBannnerIndicatorTitleTextView.setText(mTitle.get(position));
        }
    }

    /**
     * 转换 image 地址 list 的数据格式，并对 image 地址按照指定顺序排列
     *
     * @param arrayList
     * @return
     */
    private List<String> transformImageData(ArrayList<Banners> arrayList) {
        //  ImageComparator comparator = new ImageComparator();
        //   Collections.sort(arrayList, comparator);

        ArrayList<String> list = new ArrayList<String>();
        for (Banners ad : arrayList) {
            list.add(ad.getImage());
        }
        return list;
    }

    /**
     * 转换成广告url地址，并根据id排序
     *
     * @param arrayList
     * @return
     */
    private List<String> transformURLData(ArrayList<Banners> arrayList) {
        // ImageComparator comparator = new ImageComparator();
        //  Collections.sort(arrayList, comparator);

        ArrayList<String> list = new ArrayList<String>();
        for (Banners ad : arrayList) {
            list.add(ad.getLink());
        }
        return list;
    }

    /**
     * 根据id排序，转换title
     *
     * @param arrayList
     * @return
     */
    private List<String> transformTitleData(ArrayList<Banners> arrayList) {
        //  ImageComparator comparator = new ImageComparator();
        //  Collections.sort(arrayList, comparator);

        ArrayList<String> list = new ArrayList<String>();
        for (Banners ad : arrayList) {
            list.add(ad.getName());
        }
        return list;
    }


    /**
     * 按照 image id 比较大小
     *
     * @author Jack
     */
    public class ImageComparator implements Comparator<Adv> {

        @Override
        public int compare(Adv lhs, Adv rhs) {
            return (int) (lhs.getId() - rhs.getId());
        }

    }

}
