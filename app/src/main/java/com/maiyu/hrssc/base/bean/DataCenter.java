package com.maiyu.hrssc.base.bean;

import java.util.ArrayList;

/**
 * 应用的数据中心，单例模式，管理应用所有的数据，通知观察者数据改变
 * 1.个人信息
 * 2.需要上传的图片
 * 3.
 *
 * @author Jack
 */
public class DataCenter {
    public static final int TYPE_USER_INFO = 1;
    public static final int TYPE_UPLOADING_PHOTOS = 2;
    /**
     * 账户信息回调类型
     */
    public static final int TYPE_ACCOUNT_INFO = 3;

    /**
     * 个人信息
     */
    private User mUser;
    /**上传中的图片*/
    //  private HashMap<String, UploadableFile> mUploadingPhotos;

    /**
     * 数据观察者列表
     */
    private ArrayList<DataObserver> dataObserverList;

    /**
     * 单例模式获取对象实例
     */
    public static final DataCenter getInstance() {
        return DataCenterHolder.INSTANCE;
    }

    /**
     * 利用 classLoader 实现线程安全的懒加载单例模式
     *
     * @author Jack
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
     *
     * @param user
     */
    public void setUser(User user) {
        mUser.setId(user.getId());
        mUser.setName(user.getName());
        mUser.setSignature(user.getSignature());
        mUser.setHead(user.getHead());
        mUser.setPassword(user.getPassword());
        mUser.setStatus(user.getStatus());
        mUser.setSex(user.getSex());
        mUser.setBirthday(user.getBirthday());
        mUser.setMarry(user.getMarry());
        mUser.setMinority(user.getMinority());
        mUser.setId_card(user.getId_card());
        mUser.setAmount(user.getAmount());
        mUser.setPhone(user.getPhone());
        mUser.setEmail(user.getEmail());
        mUser.setLogin_time(user.getLogin_time());
        mUser.setCreate_time(user.getCreate_time());
        mUser.setToken(user.getToken());
        mUser.setLast_loginTime(user.getLast_loginTime());
        mUser.setLast_loginIp(user.getLast_loginIp());
        mUser.setSigned(user.getSigned());

        notifyObserver(TYPE_USER_INFO);
    }

    public void notifyAccountChange() {
        notifyObserver(TYPE_ACCOUNT_INFO);
    }


    /**
     * 获取用户信息, 只能使用用户信息, 禁止修改字段，修改用户信息请使用 setuser(user)
     */
    public User getuser() {
        return mUser;
    }

    /**
     * 获取用户信息, 只能使用用户信息, 禁止修改字段，修改用户信息请使用 setuser(user)
     */
    public boolean isLogin() {
        return mUser.getId() != -1;
    }

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
     *
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
     *
     * @author Jack
     */
    public static interface DataObserver {
        /**
         * 响应数据改变的监听函数
         *
         * @param type 改变的数据的类型, 各个观察者根据自己观察的数据类型进行响应
         * @param data
         */
        public void onDataChangedListener(int type, Object... data);
    }

}
