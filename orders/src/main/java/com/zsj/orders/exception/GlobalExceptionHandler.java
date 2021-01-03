package com.zsj.orders.exception;

import com.zsj.common.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 系统异常
     */
    @ExceptionHandler({Exception.class})
    public ResultData handleException(Exception e) {
        log.error("服务器错误，请联系管理员"+e.getMessage(), e);
        return ResultData.error(500, "服务器错误，请联系管理员");
    }

    /**
     * 自定义异常直接抛出错误原因
     */
    @ExceptionHandler({LogicException.class})
    public ResultData handleLogicException(Exception e) {
        log.error("逻辑错误！"+e.getMessage());
        return ResultData.error(500, e.getMessage());
    }

    /**
     * 请求类型错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultData handleHttpRequestMethodNotSupportedException(Exception e) {
        log.error("请求方法不存在"+e.getMessage());
        return ResultData.error(500, "请求类型错误");
    }



    /**
     * 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultData exceptionHandler(MethodArgumentNotValidException e) {
        log.error("=====================================");
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return ResultData.error(410, errorMesssage);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public ResultData validationExceptionHandler(BindException e) {
        log.error("---------------------------");
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return ResultData.error(410, errorMesssage);
    }
}
