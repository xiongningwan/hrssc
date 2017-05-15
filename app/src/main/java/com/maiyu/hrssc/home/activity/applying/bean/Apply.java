package com.maiyu.hrssc.home.activity.applying.bean;

/**
 * 申请列表条目项
 */

public class Apply {
    private String id;//:62,                //申请id
    private String result;//:,            //被驳回时的说明
    private String status;//:6,             //状态
    private String name;//:薪资证明办理,  //业务名称
    private String create_time;//:2017-05-04 18:34:04, //发起时间
    private String print_code;//:940433275172   //打印码
    private String pageid;//:1,                     //模板id
    private String get_way;//:2                     //领取方式 //前端根据pageid和get_way判断是否显示打印码。  只有pageid==1 && get_way==2时显示打印码
    private String work_order;//:”1111sssssssssss”   //工单号 ， 去评价页面时展示
    private String cid1;//：1, //一级业务ID
    private String cid2;//:2       //二级业务ID       一级业务ID和二级业务ID用于判断【重新提交】时跳转哪个页面


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPrint_code() {
        return print_code;
    }

    public void setPrint_code(String print_code) {
        this.print_code = print_code;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getGet_way() {
        return get_way;
    }

    public void setGet_way(String get_way) {
        this.get_way = get_way;
    }

    public String getWork_order() {
        return work_order;
    }

    public void setWork_order(String work_order) {
        this.work_order = work_order;
    }

    public String getCid1() {
        return cid1;
    }

    public void setCid1(String cid1) {
        this.cid1 = cid1;
    }

    public String getCid2() {
        return cid2;
    }

    public void setCid2(String cid2) {
        this.cid2 = cid2;
    }
}
