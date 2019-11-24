package com.sxgy.sp52.core.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonSyntaxException;
import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.domain.ApiStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.NoTransactionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;

/**
 * @author SXGY_09
 * @description 描述
 * @date 2019-11-23 17:41
 */
@RestControllerAdvice
@RestController
@ApiIgnore
@Slf4j
public class GlobalExceptionHandler implements ErrorController {
    private static final String ERROR_PATH = "/error";

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 捕获404
     * @param request 请求
     * @return 响应
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiRp errorHandler(HttpServletRequest request) {
        return new ApiRp(ApiStatus.CODE_404);
    }

    /**
     * 全局系统异常处理
     * @param request 请求
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity globalExceptionHandler(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);
        ApiRp apiRp = new ApiRp(ApiStatus.CODE_500);
        if (e instanceof NoHandlerFoundException) {
            apiRp = new ApiRp(ApiStatus.CODE_404);

        } else if (e instanceof SocketTimeoutException) {
            apiRp = new ApiRp(ApiStatus.CODE_404);
            apiRp.setMsg("连接超时");

        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            apiRp = new ApiRp(ApiStatus.CODE_405);
        } else if (e instanceof JsonSyntaxException
                || e instanceof JsonMappingException
                || e instanceof HttpMessageNotReadableException) {
            apiRp = new ApiRp(ApiStatus.CODE_400);

        } else if (e instanceof DuplicateKeyException) {
            apiRp.setMsg("数据库操作异常: " + e.getMessage());
        } else if (e instanceof NoTransactionException) {
            apiRp.setMsg("事务回滚异常: " + e.getMessage());
        }
        return ResponseEntity.status(apiRp.getCode()).body(apiRp);
    }

    /**
     * 全局业务异常处理
     * @param e 异常
     * @return 响应
     */
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity businessExceptionHandler(ApiException e) {
        log.error(String.valueOf(e));
        ApiRp apiRp = e.getApiRp();
        if (apiRp.getCode() > 500) {
            return ResponseEntity.status(200).body(apiRp);
        } else {
            return ResponseEntity.status(apiRp.getCode()).body(apiRp);
        }
    }
}
