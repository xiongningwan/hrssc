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
    //String HOST = "hrssctest.trydo.online"";//发布
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
   // String SERVER_URI = "http://hrssctest.wenhua407.top";
    //String FILE_SERVER_URI = "http://hrsscadmin.wenhua407.top";//测试
    // String SERVER_URI = "http://api.huijialicai.cn" + CONTEXT;//发布


    String SERVER_URI = "http://210.21.240.220:88/HRSSC_Server";
    String FILE_SERVER_URI = "http://210.21.240.220:88/HRSSC_ADMIN";
    /**
     * logo
     */


    /** 接口 path */
    /**
     * 登录
     */
    String path_activity_login_user_login = "/hrsscLoginController/userLogin";
    /**
     * 单点登录
     */
    String path_activity_login_user_login_moa = "/hrsscLoginController/userLoginMOA";
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
    String path_activity_information_detail = "/hrsscNewsController/getNewsById";
    /**
     * 签到
     */
    String path_fragment_my_sign = "/hrsscUserController/sign";
    /**
     * 查询是否已经签到
     */
    String path_fragment_my_signOrNot = "/hrsscUserController/signOrNot";
    /**
     * 修改密码
     */
    String path_activity_modify_pwd = "/hrsscLoginController/updatePassword";
    /**
     * 反馈
     */
    String path_activity_feedback = "/hrsscInfoController/feedback";
    /**
     * 地址管理列表
     */
    String path_activity_manage_address_get_list = "/hrsscInfoController/getMyAddress";
    /**
     * 添加收件地址
     */
    String path_activity_add_address = "/hrsscInfoController/addAddress";
    /**
     * 编辑收件地址
     */
    String path_activity_edit_address = "/hrsscInfoController/editAddress";
    /**
     * 设置默认收件地址
     */
    String path_activity_manage_set_default_address = "/hrsscInfoController/setDefaultAddress";
    /**
     * 删除收件地址
     */
    String path_activity_manage_del_address = "/hrsscInfoController/deleteAddress";
    /**
     * 快捷服务
     */
    String path_activity_fast_service_list = "/hrsscInfoController/getAllServices";
    /**
     * 问题分类
     */
    String path_activity_problem_class_list = "/hrsscHelpController/getHelpClass";
    /**
     * 根据分类/关键词   获取问题列表  已经按照创建时间倒序排列
     */
    String path_activity_problem_class_sub_list = "/hrsscHelpController/getQuestions";
    /**
     * 根据id查询问题详情
     */
    String path_activity_problem_class_sub_get_question_detail = "/hrsscHelpController/getQuestionDetail";
    /**
     * 找回密码第一步
     */
    String path_activity_forget_pwd_1 = "/hrsscLoginController/forgetPassword1";
    /**
     * 找回密码第二步
     */
    String path_activity_forget_pwd_2 = "/hrsscLoginController/forgetPassword2";
    /**
     * 发短信验证码
     */
    String path_activity_forget_pwd_2_get_msg_code = "/hrsscBasicController/sendAuthCode";
    /**
     * 查询个人资料信息
     */
    String path_activity_personal_info = "/hrsscUserController/getMyInfo";
    /**
     * 获取用户积分兑换的商品列表
     */
    String path_fragment_integration = "/hrsscProductController/getProducts";
    /**
     * 查询商品详情
     */
    String path_activity_product_item = "/hrsscProductController/getProductById";
    /**
     * 获取我的兑换记录   按照下单时间倒序排列
     */
    String path_activity_product_order = "/hrsscProductController/getMyProductOrder";
    /**
     * 获取订单详情
     */
    String path_activity_product_order_detail = "/hrsscProductController/getOrderDetail";
    /**
     * 兑换商品
     */
    String path_activity_duihuan = "/hrsscProductController/exchangeProduct";
    /**
     * 待我办理列表（即合同列表）
     */
    String path_activity_todo_findmycontract = "/hrsscContractController/findMyContract";
    /**
     * 获取业务办理 、 商城的banner
     */
    String path_activity_fragment_banner = "/hrsscHomePageController/getBanner";
    /**
     * 查询合同详情
     */
    String path_activity_toto_detail = "/hrsscContractController/findContractFlowById";
    /**
     * 签署合同
     */
    String path_activity_toto_detail_sign = "/hrsscContractController/signContractFlow";

    /**
     * 一级业务
     */
    String path_fragment_home_get_category_1 = "/hrsscBusinessController/getCategory1";
    /**
     * 获取一级业务下的二级业务
     */
    String path_business_get_category_2 = "/hrsscBusinessController/getCategory2";
    /**
     * 1.获取该业务 可以使用的全部模板   2.获取该城市的全部自取地址
     */
    String path_business_get_getTemplates = "/hrsscBusinessController/getTemplates";
    /**
     * 获取某个二级业务的基础信息（官方网站，体检、报到地址，联系人，联系方式）
     */
    String path_business_get_getWebsite = "/hrsscBusinessController/getWebsite";
    /**
     * 获取某个二级业务的办理说明（适用学位证明、工卡照片、预约入职）
     */
    String path_business_get_getLink = "/hrsscBusinessController/getLink";
    /**
     * 提交申请（适用证明类、学位证明、工卡照片、预约入职）
     */
    String path_business_submitApply = "/hrsscBusinessController/submitApply";
    /**
     * 上传图片的统一调用此接口
     */
    String path_uploadPicture = "/hrsscBasicController/uploadPicture";
    /**
     * 用于上传文件，如压缩包，文档，mp3等其他文件（图片除外）
     */
    String path_uploadFile = "/hrsscBasicController/uploadFile";
    /**
     * 查询我的申请 ，草稿箱中查询草稿也使用该接口
     */
    String path_getApplys = "/hrsscBusinessController/getApplys";
    /**
     * 待领取状态的申请  需要确认已领取申请
     */
    String path_gain = "/hrsscBusinessController/gain";
    /**
     * 删除业务
     */
    String path_deleteBusiness = "/hrsscBusinessController/deleteBusiness";
    /**
     * 查询业务详情
     */
    String path_findApplyDetail = "/hrsscBusinessController/findApplyDetail";
    /**
     * 获取体检结果
     */
    String path_getHealthCheck = "/hrsscBusinessController/getHealthCheck";
    /**
     * 首次加载个人社保
     */
    String path_getSocialSecurityFirst = "/hrsscSocialSecurityController/getSocialSecurityFirst";

    /**
     * 获取个人最新公积金缴交
     */
    String path_getPublicFundFirst = "/hrsscSocialSecurityController/getPublicFundFirst";
    /**
     * 获取公积金列表
     */
    String path_getPublicFunds = "/hrsscSocialSecurityController/getPublicFunds";
    /**
     * 查看社保记录
     */
    String path_getSocialSecurityDateWithTotalList = "/hrsscSocialSecurityController/getSocialSecurityDateWithTotalList";
    /**
     * 根据时间查询个人社保
     */
    String path_getSocialSecurityByDate = "/hrsscSocialSecurityController/getSocialSecurityByDate";
    /**
     * 获取评价的几个标签， 标签是后台可配置的
     */
    String path_getEvaluateTags = "/hrsscBusinessController/getEvaluateTags";
    /**
     * 提交评价
     */
    String path_evaluateApply = "/hrsscBusinessController/evaluateApply";
    /**
     * 版本更新
     */
    String path_getVersion = "/hrsscBasicController/getVersion";
    /**
     * 电子签署链接
     */
    String path_electronSign = "/hrsscContractController/electronSign";
    /**
     * 积分兑换规则
     */
    String path_toIntegralRulePage = "/hrsscSystemConfig/toIntegralRulePage";


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
     * /**
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
