package com.zhouyouwu.model.vo;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
public class ShowUser {

    private String userid;
    private String username;
    private BigDecimal accountBalance;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
