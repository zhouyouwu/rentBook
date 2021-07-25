package com.zhouyouwu.model.vo;

/**
 * @author Administrator
 */
public class TransferSearchParam {

    private String userid;
    private String linkAccount;
    private String opTimeStart;
    private String opTimeEnd;
    //1付款 2收款
    private String opType;
    private Integer page = 1;
    private Integer size = 10;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLinkAccount() {
        return linkAccount;
    }

    public void setLinkAccount(String linkAccount) {
        this.linkAccount = linkAccount;
    }

    public String getOpTimeStart() {
        return opTimeStart;
    }

    public void setOpTimeStart(String opTimeStart) {
        this.opTimeStart = opTimeStart;
    }

    public String getOpTimeEnd() {
        return opTimeEnd;
    }

    public void setOpTimeEnd(String opTimeEnd) {
        this.opTimeEnd = opTimeEnd;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
