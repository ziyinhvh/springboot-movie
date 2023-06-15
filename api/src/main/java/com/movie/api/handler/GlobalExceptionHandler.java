package com.movie.api.handler;

import com.movie.api.annotation.DisableBaseResponse;
import com.movie.api.model.support.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 捕获controller异常
 * controller抛出异常执行下边的函数
 * 返回Response写入ApiResult
 */

@ResponseBody   // 将方法返回的对象自动转换成 JSON 格式返回给客户端
@RestControllerAdvice   // 声明该类是一个全局异常处理器
public class GlobalExceptionHandler {
    // 获取日志对象
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 声明该方法处理 Exception 类型的异常
    @ExceptionHandler(value = Exception.class)
    // 声明该方法返回的不是 ResponseResult 类型，而是直接返回一个对象
    @DisableBaseResponse
    // 异常处理方法
    public Object handleException(Exception e) {
        // 如果抛出的异常是 AccessDeniedException 类型
        if (e.getClass().equals(AccessDeniedException.class)) {
            // 返回一个状态码为 403 的 ResponseResult 对象
            return new ResponseResult<>(403, "你没有访问权限");
        }
        // 记录异常信息
        logger.error(e.getMessage());
        // 返回一个状态码为 400 的 ResponseResult 对象
        return new ResponseResult<>(400, e.getMessage());
    }

}