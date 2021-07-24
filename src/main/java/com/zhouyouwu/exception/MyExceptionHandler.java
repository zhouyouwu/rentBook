package com.zhouyouwu.exception;

import com.zhouyouwu.model.ResultObject;
import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Administrator
 */
@Log4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(UserException.class)
    public Object userException(UserException e){

        log.error(e.getMessage());
        return ResultObject.fail(e.getMessage());
    }
}
