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
    private String opType;
    private String linkAccount;
    //操作后余额
    private BigDecimal opBalance;

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

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getLinkAccount() {
        return linkAccount;
    }

    public void setLinkAccount(String linkAccount) {
        this.linkAccount = linkAccount;
    }

    public BigDecimal getOpBalance() {
        return opBalance;
    }

    public void setOpBalance(BigDecimal opBalance) {
        this.opBalance = opBalance;
    }
}
