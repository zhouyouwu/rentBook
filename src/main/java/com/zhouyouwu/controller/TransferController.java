package com.zhouyouwu.controller;

import com.zhouyouwu.model.vo.TransferSearchParam;
import com.zhouyouwu.service.TransferService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@Log4j
@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("getTransfer.do")
    public Object getTransfer(@RequestParam("userid") String userid,
                              @RequestBody TransferSearchParam searchParam) {

        return transferService.getTransfer(userid, searchParam);
    }
}
