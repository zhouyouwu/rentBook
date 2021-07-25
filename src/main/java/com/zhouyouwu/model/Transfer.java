package com.zhouyouwu.model;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
public class Transfer {

    private String userid;
    private String operationTime;
    //本次操作金额
    private BigDecimal amount;
    //1转账，2收款
    private Integer opType;
    private String linkAccount;
    private String linkUsername;
    //操作后余额
    private BigDecimal opBalance;
    private String opDesc;
    private String pwd;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getLinkAccount() {
        return linkAccount;
    }

    public void setLinkAccount(String linkAccount) {
        this.linkAccount = linkAccount;
    }

    public String getLinkUsername() {
        return linkUsername;
    }

    public void setLinkUsername(String linkUsername) {
        this.linkUsername = linkUsername;
    }

    public BigDecimal getOpBalance() {
        return opBalance;
    }

    public void setOpBalance(BigDecimal opBalance) {
        this.opBalance = opBalance;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
