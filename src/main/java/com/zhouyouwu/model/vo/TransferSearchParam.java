package com.zhouyouwu.model.vo;

/**
 * @author Administrator
 */
public class TransferSearchParam {

    private String linkAccount;
    private String operationTime;
    //1付款 2收款
    private String opType;
    private Integer page = 1;
    private Integer size = 10;

    public String getLinkAccount() {
        return linkAccount;
    }

    public void setLinkAccount(String linkAccount) {
        this.linkAccount = linkAccount;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
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
