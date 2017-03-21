package com.maiyu.hrssc.base.bean;

import java.util.ArrayList;

/**
 * 应用的数据中心，单例模式，管理应用所有的数据，通知观察者数据改变
 * 1.个人信息
 * 2.需要上传的图片
 * 3.
 * @author Jack
 *
 */
public class DataCenter {
    public static final int TYPE_USER_INFO = 1;
    public static final int TYPE_UPLOADING_PHOTOS = 2;
    /**账户信息回调类型*/
    public static final int TYPE_ACCOUNT_INFO = 3;
    /**账户未登录广告*/
    public static final int TYPE_ACCOUNT_UNLOGIN_AD = 5;
    /**登出回调*/
    public static final int TYPE_LOGOUT_DATA_CHANGE = 6;
    /**转到短期产品列表 */
    public static final int TYPE_GO_TO_SHORT_PRODUCT = 7;
    /**产品单条刷新 */
    public static final int TYPE_PRODUCT_UPDATE_SIGLE_ROW = 8;
    /**合伙人提现数据更新 */
    public static final int TYPE_PARTER_DATA_CHANGE = 9;

    /**个人信息*/
    private User mUser;
    /**上传中的图片*/
    //  private HashMap<String, UploadableFile> mUploadingPhotos;

    /**数据观察者列表*/
    private ArrayList<DataObserver> dataObserverList;

    /**
     * 单例模式获取对象实例
     */
    public static final DataCenter getInstance() {
        return DataCenterHolder.INSTANCE;
    }

    /**
     * 利用 classLoader 实现线程安全的懒加载单例模式
     * @author Jack
     * 
     */
    private static class DataCenterHolder {
        private static final DataCenter INSTANCE = new DataCenter();
    }

    /**
     * 单例模式构造函数
     */
    private DataCenter() {
        mUser = new User();
        //   mUploadingPhotos = new HashMap<String, UploadableFile>();
        dataObserverList = new ArrayList<DataCenter.DataObserver>();
    }

    /**
     * 设置用户信息
     * @param user
     */
    public void setUser(User user) {
        mUser.setId(user.getId());
        mUser.setMobile(user.getMobile());
        mUser.setToken(user.getToken());
        mUser.setLevel(user.getLevel());
        mUser.setRealityName(user.getRealityName());
        mUser.setCode(user.getCode());
        mUser.setIncomeSpace(user.getIncomeSpace());
      //  mUser.setCanGoRewards(user.getCanGoRewards());
        mUser.setIsAssess(user.getIsAssess());
        mUser.setIsIdCard(user.getIsIdCard());
        notifyObserver(TYPE_USER_INFO);
    }

    public void notifyAccountChange() {
        notifyObserver(TYPE_ACCOUNT_INFO);
    }

    public void notifyLogoutDataChange() {
        notifyObserver(TYPE_LOGOUT_DATA_CHANGE);
    }

    /**
     * 账户未登录广告通知
     */
    public void notifyAccountUnloginAdChange() {
        notifyObserver(TYPE_ACCOUNT_UNLOGIN_AD);
    }

    /**
     * 跳转到短期产品列表
     */
    public void notifyGotoShortProduct() {
        notifyObserver(TYPE_GO_TO_SHORT_PRODUCT);
    }

    /**
     * 主页单条数据更新
     */
    public void notifyHomeDataChange() {
        notifyObserver(TYPE_PRODUCT_UPDATE_SIGLE_ROW);
    }
    /**
     *  合伙人提现数据更新
     */
    public void notifyParterDataChange() {
        notifyObserver(TYPE_PARTER_DATA_CHANGE);
    }

    /**
     * 获取用户信息, 只能使用用户信息, 禁止修改字段，修改用户信息请使用 setuser(user)
     */
    public User getuser() {
        return mUser;
    }

    /**
     * 获取用户信息, 只能使用用户信息, 禁止修改字段，修改用户信息请使用 setuser(user)
     * @param user
     */
    /*public boolean isLogin() {
        return mUser.getUserId() != -1;
    }*/

    /*  *//**
                        * 添加上传文件
                        * @param uri
                        */
    /*
    public void addUploadingFile(UploadableFile file) {
     mUploadingPhotos.put(file.getLocalAddressUri().getPath(), file);
     notifyObserver(TYPE_UPLOADING_PHOTOS, file);
    }

    *//**
      * 更新上传文件的信息
      * @param uri
      */
    /*
    public void modifyUploadingFile(UploadableFile file) {
     UploadableFile uploadableFile = mUploadingPhotos.get(file.getLocalAddressUri().getPath());
     if (uploadableFile != null) {
         uploadableFile.setUploadedPercent(file.getUploadedPercent());
         uploadableFile.setUploadingState(file.getUploadingState());
         notifyObserver(TYPE_UPLOADING_PHOTOS, uploadableFile);
     }
    }*/

    /**
     * 通知所有观察者
     * @param type
     */
    private void notifyObserver(int type, Object... data) {
        for (DataObserver observer : dataObserverList) {
            observer.onDataChangedListener(type, data);
        }
    }

    /**
     * 注册观察者
     */
    public void registerObserver(DataObserver observer) {
        dataObserverList.add(observer);
    }

    /**
     * 取消注册观察者
     */
    public void unregisterObserver(DataObserver observer) {
        dataObserverList.remove(observer);
    }

    /**
     * 数据观察者接口
     * @author Jack
     *
     */
    public static interface DataObserver {
        /**
         * 响应数据改变的监听函数
         * @param type 改变的数据的类型, 各个观察者根据自己观察的数据类型进行响应
         * @param data
         */
        public void onDataChangedListener(int type, Object... data);
    }

}
