package com.zhouyouwu.controller;

import com.zhouyouwu.exception.UserException;
import com.zhouyouwu.model.ResultObject;
import com.zhouyouwu.model.Transfer;
import com.zhouyouwu.model.vo.TransferSearchParam;
import com.zhouyouwu.service.TransferService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Log4j
@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping("getTransfer.do")
    public Object getTransfer(@RequestBody TransferSearchParam searchParam) {

        return ResultObject.ok(transferService.getTransfer(searchParam));
    }

    @PostMapping("transfer.do")
    public Object transfer(@RequestBody Transfer transfer) throws UserException {

        transferService.transfer(transfer);
        return ResultObject.ok("转账成功");
    }
}
