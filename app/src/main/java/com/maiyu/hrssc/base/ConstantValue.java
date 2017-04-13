package com.maiyu.hrssc.base;

import android.os.Environment;

public interface ConstantValue {
    /**
     * http 参数编码
     */
    String ENCODING = "UTF-8";

    /**
     * 服务器主机地址
     */
    String HOST = "hrssctest.trydo.online"; //测试
    //String HOST = "healthapp.jhjhome.com";//发布
    /**
     * 项目 context
     */
    String CONTEXT = "/hjlc";

    /**
     * 服务器端口
     */
     String port = "8080"; // 开发

    /**
     * 服务器地址
     */
    // String SERVER_URI = "http://" + HOST + ":" + port + CONTEXT;
     String SERVER_URI = "http://hrssctest.trydo.online";
    // String SERVER_URI = "http://api.huijialicai.cn" + CONTEXT;//发布

    /**
     * logo
     */


    /** 接口 path */
    /**
     * 登录
     */
    String path_activity_login_user_login = "/hrsscLoginController/userLogin";
    /**
     * 城市列表
     */
    String path_fragment_home_get_citys = "/hrsscBasicController/getCitys";
    /**
     * 首页信息
     */
    String path_fragment_home_get_page_data = "/hrsscHomePageController/getBannerAndNews";
    /**
     * 消息列表
     */
    String path_activity_message_list = "/hrsscMessageController/getMessages";
    /**
     * 消息详情
     */
    String path_activity_message_detail = "/hrsscMessageController/getMessageDetail";
    /**
     * 删除一条消息
     */
    String path_activity_message_delete_item = "/hrsscMessageController/deleteMessage";
    /**
     * 资讯分类
     */
    String path_activity_information_get_news_class = "/hrsscNewsController/getNewClass";
    /**
     * 资讯列表
     */
    String path_fragment_information_page_get_news_list = "/hrsscNewsController/getNewsByClass";
    /**
     * 资讯详情
     */
    String path_activity_information_detail= "/hrsscNewsController/getNewsById";
    /**
     * 签到
     */
    String path_fragment_my_sign= "/hrsscUserController/sign";



    /**
     * 进入WebView的参数
     */
    public static final String STRING_URL = "url";
    public static String TYPE = "type";
    public static int TYPE_ORDINARY = 0;
    public static int TYPE_PRODUCT = 1;
    public static int TYPE_IMPORTANT = 2;
    public static int TYPE_HOME_TRANSFER = 3;
    public static int TYPE_MY_TRANSFER_ZONE_OTHER = 4;
    public static int TYPE_MY_TRANSFER_ZONE_CAN = 5;
    public static int TYPE_MY_TRANSFER_ASSETS = 6;
    public static String PRODUCT_ID = "id";
    public static String TRANSFER_ID = "transfer_id";

    /**
     * 发送短信验证码类型，注册
     **/
    public static final int MSG_CODE_TYPE_REGISTER_MODULE = 0;
    /**


    /**
     * 客户端类型 0安卓1iso
     */
    final String CLIENT_TYPE_ANDROID = "android";

    /** 其他 */

    /**
     * 网络连接状态
     */
    public int NETWORK_STATE_CONNECTED = 1;
    public int NETWORK_STATE_DISCONNECTED = 2;


    public static String DOWNLOAD_DIR = Environment.getExternalStorageDirectory() + "/" + "hrssc";

}
