package com.zhouyouwu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhouyouwu.mapper.TransferMapper;
import com.zhouyouwu.model.Transfer;
import com.zhouyouwu.model.vo.TransferSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class TransferService {
    @Autowired
    private TransferMapper transferMapper;

    public PageInfo<Transfer> getTransfer(String userid, TransferSearchParam searchParam) {

        PageHelper.startPage(searchParam.getPage(), searchParam.getSize());

        return new PageInfo<>(transferMapper.getTransfer(userid, searchParam));
    }
}
