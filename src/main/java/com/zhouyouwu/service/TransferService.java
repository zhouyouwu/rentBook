package com.zhouyouwu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhouyouwu.exception.UserException;
import com.zhouyouwu.mapper.TransferMapper;
import com.zhouyouwu.mapper.UserMapper;
import com.zhouyouwu.model.Transfer;
import com.zhouyouwu.model.User;
import com.zhouyouwu.model.vo.TransferSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Administrator
 */
@Service
public class TransferService {
    @Autowired
    private TransferMapper transferMapper;
    @Autowired
    private UserMapper userMapper;

    public PageInfo<Transfer> getTransfer(TransferSearchParam searchParam) {

        PageHelper.startPage(searchParam.getPage(), searchParam.getSize());

        return new PageInfo<>(transferMapper.getTransfer(searchParam));
    }

    /**
     * 转账，避免oracle等数据库默认隔离为读已提交，设置为可重复读
     * @param transfer
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void transfer(Transfer transfer) throws UserException {
        User user = userMapper.getUserById(transfer.getUserid());
        if(user == null){
            throw new UserException("用户不存在");
        }

        User linkUser = userMapper.getUserById(transfer.getLinkAccount());
        if(linkUser == null){
            throw new UserException("收款人不存在");
        }

        if(user.getAccountBalance().compareTo(transfer.getAmount()) < 0){
            throw new UserException("余额不足");
        }

        BigDecimal opBalance = user.getAccountBalance().subtract(transfer.getAmount());
        userMapper.updateBalance(user.getUserid(), opBalance);

        BigDecimal linkOpBalance = linkUser.getAccountBalance().add(transfer.getAmount());
        userMapper.updateBalance(linkUser.getUserid(), linkOpBalance);

        transfer.setOpType(1);
        transfer.setOperationTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        transfer.setOpBalance(opBalance);
        transfer.setLinkUsername(linkUser.getUsername());
        transferMapper.transfer(transfer);

        Transfer linkTransfer = new Transfer();
        linkTransfer.setOpType(2);
        linkTransfer.setOperationTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        linkTransfer.setOpBalance(linkOpBalance);
        linkTransfer.setUserid(linkUser.getUserid());
        linkTransfer.setAmount(transfer.getAmount());
        linkTransfer.setLinkAccount(user.getUserid());
        linkTransfer.setLinkUsername(user.getUsername());
        linkTransfer.setOpDesc(transfer.getOpDesc());
        transferMapper.transfer(linkTransfer);
    }
}
