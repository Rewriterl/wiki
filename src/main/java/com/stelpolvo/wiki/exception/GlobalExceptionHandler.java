package com.stelpolvo.wiki.exception;

import com.stelpolvo.wiki.aspect.LogAspect;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.utils.ThrowableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public Resp handleException(Throwable e) {
        // 打印堆栈信息
        LOG.info("------------- 发生异常: {} -------------", ThrowableUtil.getStackTrace(e));
        return buildResponseEntity(e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Resp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        LOG.info("------------- 发生异常 -------------", ThrowableUtil.getStackTrace(e).getBytes(StandardCharsets.UTF_8));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if (msg.equals(message)) {
            message = str[1] + ":" + message;
        }
        return buildResponseEntity(message);
    }

    /**
     * 统一返回
     */
    private Resp buildResponseEntity(String message) {
        return Resp.error(message, null);
    }
}
