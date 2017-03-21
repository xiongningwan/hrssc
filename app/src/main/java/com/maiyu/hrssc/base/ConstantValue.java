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
    String HOST = "172.18.8.35"; //测试
    //String HOST = "172.18.56.208"; //唐磊
    //String HOST = "172.18.56.169"; //陈俊
    //String HOST = "172.18.56.208"; //唐磊
    //String HOST = "healthapp.jhjhome.com";//发布
    /**
     * 项目 context
     */
    String CONTEXT = "/hjlc";

    /**
     * 服务器端口
     */
    // String port = "6983"; //蒋涵
     String port = "8080"; // 开发
    // String port = "8081"; //陈俊 

    /**
     * 服务器地址
     */
    // String SERVER_URI = "http://" + HOST + ":" + port + CONTEXT;
    // String SERVER_URI = "http://test.api.huijialicai.cn"+ CONTEXT;
     String SERVER_URI = "http://api.huijialicai.cn" + CONTEXT;//发布

    /**
     * logo
     */
    //String LOGO_URI = "http://test.img.huijialicai.cn//group1/M00/00/01/rBAIAVe-lZ-AHbVYAAA1mCmDOuQ273.png";
    String LOGO_URI = "http://img.huijialicai.cn/group1/M00/00/03/rBAIBVfHwJmAS6M-AAA1mCmDOuQ706.png";
    /** 接口 path */
    /**
     * 登录
     */
    String path_activity_login_user_login = "/user/login.json";
    /**
     * 验证码登录
     */
    String path_activity_login_user_login_by_code = "/user/loginByCode.json";
    /**
     * 注册
     */
    String path_activity_register_user_register = "/user/register.json";
    /**
     * 获取验证码
     */
    String path_activity_register_user_getVerifyCode = "/sms.json";
    /**
     * 查询产品列表
     */
    String path_activity_main_product_list = "/product/index_list.json";
    /**
     * 查询单个产品
     */
    String path_activity_main_product_get_single_row = "/product/getSingleRow.json";
    /**
     * 查询产品详情
     */
    String path_activity_main_product_detail = "/product/detail";
    /**
     * 找回登录密码
     */
    String path_activity_forgetpwd_user_find_pwd = "/user/findLoginPassword.json";
    /**
     * 设置登录密码
     */
    String path_activity_setloginpwd_user_set_pwd = "/user/setLoginPassword.json";
    /**
     * 修改登录密码
     */
    String path_activity_resetloginpwd_user_reset_pwd = "/user/updateLoginPassword.json";
    /**
     * 修改交易密码
     */
    String path_activity_modifypaypwd_user_modify_pwd = "/user/updatePayPassword.json";
    /**
     * 设置交易密码
     */
    String path_activity_setpaypwd_user_set_pwd = "/user/createPayPassword.json";
    /**
     * 找回交易密码  验证资料
     */
    String path_activity_forget_paypwd_data_check = "/user/findPayPasswordCheck.json";
    /**
     * 找回交易密码  确认找回
     */
    String path_activity_forget_paypwd_confirm_find_back = "/user/findPayPassword.json";
    /**
     * 账户显示
     */
    String path_fragment_account_user_account_data = "/user/accountData.json";
    /**
     * 获取个人信息
     */
    String path_activity_personal_get_userInfo = "/user/userInfo.json";
    /**
     * 获取资产总计相关信息
     */
    String path_activity_total_amount_get_total_amount_info = "/user/totalAmount.json";
    /**
     * 获取账户余额统计
     */
    String path_activity_balance_get_statistics = "/user/accountDetail.json";
    /**
     * 获取账户余额列表
     */
    String path_activity_balance_get_list = "/user/accountDetailLists.json";
    /**
     * 获取提现页面数据
     */
    String path_activity_with_draw_cash_get_cash_page_data = "/user/cashPage.json";
    /**
     * 提现
     */
    String path_activity_with_draw_cash_request_cash = "/user/requestCash.json";
    /**
     * 获取奖励统计
     */
    String path_activity_reward_get_statistics = "/reward/baseInfo.json";
    /**
     * 设置回款方式
     */
    String path_activity_set_back_account_type_set_income_space = "/user/setIncomeSpace.json";
    /**
     * 获取惠玩页面数据
     */
    String path_fragment_play_page_base_info = "/user/baseInfo.json";
    /**
     * 获取用户vip等级数据
     */
    String path_activity_vip_lv_info = "/vip/userLevelInfo.json";
    /**
     * 检查更新版本
     */
    String path_check_version = "/app/version/check.json";
    /**
     * 获取广告列表
     */
    String path_get_adv_list = "/adv/list.json";

    /**
     * 获取奖励列表
     */
    String path_activity_reward_get_list = "/reward/list.json";
    /**
     * 结算奖励佣金
     */
    String path_activity_settle_reward_settle_reward = "/reward/settle.json";
    /**
     * 获取邀请投资人数
     */
    String path_frament_play_invite_people_count = "/invite/count.json";
    /**
     * 获取vip等级规则信息
     */
    String path_activity_vip_list_get_list = "/vip/infoList.json";
    /**
     * 获取获取邀请列表
     */
    String path_activity_my_invite_get_list = "/invite/list.json";
    /**
     * 是否是注册用户
     */
    String path_activity_modify_pwd_is_user = "/user/isUser.json";
    /**
     * 是否能够设置用户密码
     */
    String path_activity_set_pay_pwd_is_add = "/user/isAddPayPwd.json";
    /**
     * 每天登录一次增加惠币
     */
    String path_add_pay_coin = "/user/applyCoin.json";


    /**债权转让*/
    /**
     * 转让常见问题
     */
    String path_h5_transfer_zone_faq = "/transfer/FAQ";
    /**
     * 转让协议地址
     */
    String path_h5_transfer_protocol_pack = "/transfer/transferPact";
    /**
     * 信息披露列表
     */
    String path_h5_fragment2_home_info_notice_list = "/infoNotice/list";
    /**
     * 安全保障
     */
    String path_h5_fragment2_home_info_notice_security_guarantee = "/infoNotice/SecurityGuarantee";
    /**
     * 了解我们
     */
    String path_h5_fragment2_home_info_notice_about_us = "/infoNotice/aboutUs";
    /**
     * 查看评测结果
     */
    String path_h5_fragment2_home_info_notice_question_page_3 = "/question/page?page=3";
    /**
     * 去评测
     */
    String path_h5_fragment2_home_info_notice_question_page_1 = "/question/page?page=1";

    /**
     * 可转让列表
     */
    String path_activity_my_transfer_zone_can_transfer_list = "/transfer/transfer_list.json";
    /**
     * 其他状态转让列表
     */
    String path_activity_my_transfer_zone_other_status_transfer_list = "/transfer/orderTransferList.json";
    /**
     * 转让页面数据
     */
    String path_activity_transfer_transfer_transfer_info = "/transfer/transferInfo.json";
    /**
     * 请求转让
     */
    String path_activity_transfer_do_transfer_info = "/transfer/doTransferInfo.json";
    /**
     * 我的转让专区数据统计
     */
    String path_activity_transfer_transfer_stat = "/transfer/tranferStat.json";


    /**
     * 记录 产品详情、活动分享、产品分享 的日志统计
     */
    String path_share_stat = "/share/stat.json";
    /**
     * 推荐产品
     */
    String path_fragment2_home_product_hot = "/product/hot.json";
    /**
     * 数据看板
     */
    String path_fragment2_home_statistics = "/statistics.json";
    /**
     * 查看风险评测结果
     */
    String path_fragment2_home_question_result = "/question/result.json";
    /**
     * 下载产品协议
     */
    String path_webview_document_download_product_pact = "/pact/download";
    /**
     * 合伙人账户
     */
    String path_parter_parter_acitivity_account = "/parenter/reward/account.json";
    /**
     * 我的团队数量
     */
    String path_activity_my_team_team_count = "/teamCount.json";
    /**
     * 我的团队列表
     */
    String path_activity_my_team_team_list = "/teamList.json";
    /**
     * 推荐规则图片
     */
    String path_activity_invite_newcommer_reward_img = "/rewardImg.json";
    /**
     * 合伙人奖励基本信息
     */
    String path_activity_parter_reward_mine_amount = "/parenter/reward/mine/amount.json";
    /**
     * 合伙人奖励信息列表
     */
    String path_activity_parter_reward_mine_reward_list = "/parenter/reward/mine/rewardList.json";
    /**
     * 合伙人奖励结算
     */
    String path_activity_parter_reward_mine_v1_settle = "/reward/v1/settle.json";
    /**
     * 消息公告列表
     */
    String path_activity_message_info_list = "/info/list.json";
    /**
     * 消息公告条数
     */
    String path_activity_message_info_total = "/info/total.json";
    /**
     * 消息公告全部已读
     */
    String path_activity_message_info_total_read = "/info/totalRead.json";
    /**
     * 消息公告未读条数
     */
    String path_activity_message_info_unread_count = "/info/unReadCount.json";



    /** h5接口*/
    /**
     * 进入邀请注册页面
     */
    String path_h5_invite_register = "/invite/register";
    /**
     * 渠道代理注册
     */
    String path_h5_agent_register = "/agent/register";
    /**
     * 资产明细列表 userId type=3
     */
    String path_h5_user_assets_list = "/user/assetsLists";
    /**
     * 交易记录列表 userId type=3
     */
    String path_h5_user_transfer_list = "/user/transferLists";
    /**
     * 我的银行卡 userId type=3
     */
    String path_h5_user_my_bank = "/user/myBank";
    /**
     * 惠家理财用户服务协议(投资人版) type = 0
     */
    String path_h5_register_pact = "/register/pact";
    /**
     * 设置交易密码 userId type=3
     */
    String path_h5_user_create_pay_password = "/user/createPayPassword";
    /**
     * 惠家理财余额账户服务协议  type=0
     */
    String path_h5_user_account_pact = "/user/accountPact";
    /**
     * 绑卡认证 userId type=3
     */
    String path_h5_user_bind_bank = "/user/bindBank";
    /**
     * 收益列表 userId type=3
     */
    String path_h5_user_profit_list = "/user/profitLists";
    /**
     * 帮助中心
     */
    String path_h5_user_help_center = "/user/helpCenter";
    /**
     * 邀请规则
     */
    String path_h5_user_invite_rule = "/user/rule";
    /**
     * 邀请规则改动为后台配置
     */
    String path_h5_reawrd_recommeRule_rule = "/reward/recommeRule ";
    /**
     * 结算规则
     */
    String path_h5_reawrd_reward_rule = "/reward/settleRule ";
    /**
     * 奖励明细
     */
    String path_h5_reawrd_detail = "/reward/detail";
    /**
     * 分享产品详情
     */
    String path_h5_share_product = "/share/product";
    /**
     * 转让资产产品详情
     */
    String path_h5_fragment_home_transfer_transfer_detail = "/transfer/detail";
    /**
     * 可转让资产详情
     */
    String path_h5_transfer_transfer_detail = "/transfer/transfer_detail";
    /**
     * 债权转让产品详情(转让中、转让成功、流标)
     */
    String path_h5_transfer_order_transfer_detail = "/transfer/orderTransferDetail";
    /**
     * 首页转让专区产品列表
     */
    String path_fragment_home_product_transfer_list = "/product/transfer_list.json";
    /**
     * 3.80首页转让专区产品列表(V1版)
     */
    String path_fragment_home_product_transfer_list1 = "/product/v1/transfer_list.json";
    /**
     * 债权转让成功后，查看转让记录
     */
    String path_h5_transfer_user_transfer_detail = "/user/transferDetail";
    /**
     * 债权转让资产详情原型
     */
    String path_h5_transfer_user_assets_detail = "/user/assetsDetail";

    /**
     * 合伙人推荐页面
     */
    String path_h5_parter_reward_rule = "/rewardRuleView";
    /**
     * 合伙人奖励明细
     */
    String path_h5_parter_reward_list = "/user/partner_rewardList";
    /**
     * 消息详细内容
     */
    String path_h5_info_detail_content = "/info/content";


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
     * 发送短信验证码类型，找回登录密码
     **/
    public static final int MSG_CODE_TYPE_GET_BACK_LGOIN_PWD_MODULE = 2;
    /**
     * 发送短信验证码类型，找回支付密码
     **/
    public static final int MSG_CODE_TYPE_GET_BACK_PAY_PWD_MODULE = 3;
    /**
     * 发送短信验证码类型，短信登录
     **/
    public static final int MSG_CODE_TYPE_CODE_LOGIN_MODULE = 4;

    /**
     * 发送短信验证码类型，注册
     **/
    public static final int MSG_CODE_TYPE_REGISTER_TYPE = 1;
    /**
     * 发送短信验证码类型，找回登录密码
     **/
    public static final int MSG_CODE_TYPE_GET_BACK_LGOIN_PWD_TYPE = 3;
    /**
     * 发送短信验证码类型，找回支付密码
     **/
    public static final int MSG_CODE_TYPE_GET_BACK_PAY_PWD_TYPE = 4;
    /**
     * 发送短信验证码类型，短信登录
     **/
    public static final int MSG_CODE_TYPE_CODE_LOGIN_TYPE = 15;

    /**
     * 手势密码点的状态,正常状态
     **/
    public static final int POINT_STATE_NORMAL = 0;
    /**
     * 手势密码点的状态,按下状态
     **/
    public static final int POINT_STATE_SELECTED = 1;
    /**
     * 手势密码点的状态,错误状态
     **/
    public static final int POINT_STATE_WRONG = 2;

    /**
     * 客户端类型 0安卓1iso
     */
    final String CLIENT_TYPE_ANDROID = "0";

    /** 其他 */

    /**
     * 网络连接状态
     */
    public int NETWORK_STATE_CONNECTED = 1;
    public int NETWORK_STATE_DISCONNECTED = 2;


    public static String DOWNLOAD_DIR = Environment.getExternalStorageDirectory() + "/" + "JHJFinance";

}
