package com.sxgy.sp52.core.domain;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author SXGY_09
 * @description 状态码/信息
 * @date 2019-11-23 09:28
 */
@Getter
public enum ApiStatus implements Serializable {
    CODE_200(200, "操作成功"),
    CODE_400(400, "请求参数错误，无法解析"),
    CODE_401(401, "未授权"),
    CODE_403(403, "服务器拒绝请求（权限不足，请联系管理员）"),
    CODE_404(404, "资源不存在"),
    CODE_405(405, "请求方式有误"),
    CODE_409(409, "冲突"),
    CODE_417(417, "执行失败"),
    CODE_500(500, "服务器内部错误"),
    //通用业务处理失败
    FAIL(1000, "处理失败"),
    //通用业务处理成功
    SUCCESS(1001, "处理成功");

    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code = 0;
    /**
     * 状态信息
     */
    private String msg = null;

    ApiStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
