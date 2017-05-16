package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ApplyDetail {
    private String uid;// 1,                            //用户id
    private String result;// sss,                     //被驳回的理由
    private String attachs;// ,                       //附件
    private String link;// http;////www.baidu.com,      //帮助链接，是绝对链接，直接跳转
    private String address_info;// 深圳市南山区粤海大道xxhao,//自取地址详细
    private String work_order;// aa05a30d64d340,      //工单号
    private String print_code;// 941766832704,        //打印码
    private String tpl_content;// <h2>123</h2>, //模板内容，直接展示html即可
    private String recipient;// ,               //邮寄地址
    private String city;// 深圳市,              //城市
    private String postal_number;// ,           //运单号
    private String id;// 67,                      //申请id
    private String deal_name;// 管理员3,        //办理业务员名称
    private String name;// 薪资证明办理,        //业务名称
    private String tpl_form;// 姓名=王强;年龄=18,//表单参数
    private String tpl_document;// /uploadFiles/contract/20170504/183616/北京计划生育证明（中文）-小明.doc,       //表单生成文档的路径，下载的话需要拼接文件上传的域名
    private String pageid;// 1,                   //使用的页面类型
    private String print_status;// 2,             //打印状态  0-打印失败 1-打印成功 2-未打印
    private String status;// 1,       //状态：0待审核，1待办理，2待领取，3待评价，4已完成,5-已驳回 ，6-草稿箱
    private String cid2;// 1,          //二级业务id
    private String aid_deal;// usernaem,//办理员用户名 （不展示，展示的是业务员名称）
    private String cid1;// 1,           //一级业务id
    private String postal_type;// -1,  //邮件类型 -1 无快递公司      0 其他快递
    private String tpl_tid;// 17,      //模板id
    private String ext1;// ,         //扩展字段1
    private String ext2;// ,         //扩展字段2
    private String audit_name;// 审核员1,//审核员名称
    private String address;// 南山区科研大厦,//自取地址名称
    private String create_time;// 2017-05-04 18;//36;//16,//创建申请时间
    private String images;// ,        //图片
    private String audit_time;// 2017-05-10 14;//52;//38,//审核时间
    private String get_way;// 1,      //领取方式：0;//自取 1;//邮寄, 2打印
    private String language;// 0,      //语言：0中文 1英文
    private String comment;// 备注,  //备注
    private String brief;// 描述,    //描述
    private String aid_audit;// 管理员1//审核员用户名（不展示，展示的是审核员名称）


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAttachs() {
        return attachs;
    }

    public void setAttachs(String attachs) {
        this.attachs = attachs;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getWork_order() {
        return work_order;
    }

    public void setWork_order(String work_order) {
        this.work_order = work_order;
    }

    public String getPrint_code() {
        return print_code;
    }

    public void setPrint_code(String print_code) {
        this.print_code = print_code;
    }

    public String getTpl_content() {
        return tpl_content;
    }

    public void setTpl_content(String tpl_content) {
        this.tpl_content = tpl_content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_number() {
        return postal_number;
    }

    public void setPostal_number(String postal_number) {
        this.postal_number = postal_number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeal_name() {
        return deal_name;
    }

    public void setDeal_name(String deal_name) {
        this.deal_name = deal_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTpl_form() {
        return tpl_form;
    }

    public void setTpl_form(String tpl_form) {
        this.tpl_form = tpl_form;
    }

    public String getTpl_document() {
        return tpl_document;
    }

    public void setTpl_document(String tpl_document) {
        this.tpl_document = tpl_document;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getPrint_status() {
        return print_status;
    }

    public void setPrint_status(String print_status) {
        this.print_status = print_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }

    public String getAid_deal() {
        return aid_deal;
    }

    public void setAid_deal(String aid_deal) {
        this.aid_deal = aid_deal;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getPostal_type() {
        return postal_type;
    }

    public void setPostal_type(String postal_type) {
        this.postal_type = postal_type;
    }

    public String getTpl_tid() {
        return tpl_tid;
    }

    public void setTpl_tid(String tpl_tid) {
        this.tpl_tid = tpl_tid;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getAudit_name() {
        return audit_name;
    }

    public void setAudit_name(String audit_name) {
        this.audit_name = audit_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }

    public String getGet_way() {
        return get_way;
    }

    public void setGet_way(String get_way) {
        this.get_way = get_way;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAid_audit() {
        return aid_audit;
    }

    public void setAid_audit(String aid_audit) {
        this.aid_audit = aid_audit;
    }
}
