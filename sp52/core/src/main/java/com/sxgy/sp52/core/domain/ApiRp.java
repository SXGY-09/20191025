package com.sxgy.sp52.core.domain;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author SXGY_09
 * @description 请求响应Response
 * @date 2019-11-23 09:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRp implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 参数
     */
    private Object params;
    /**
     * 响应正文
     */
    private Object data;
    public static Integer SUCCESS_CODE = 1001;
    public static Integer FAIL_CODE = 1000;

    public ApiRp(ApiStatus apiCode) {
        this.code = apiCode.getCode();
        this.msg = apiCode.getMsg();
    }

    public static ApiRp success() {
        return new ApiRp(ApiStatus.SUCCESS);
    }

    public static ApiRp success(String msg) {
        ApiRp apiRp = ApiRp.success();
        apiRp.setMsg(msg);
        return apiRp;
    }

    public static ApiRp success(String msg, Object data) {
        ApiRp apiRp = ApiRp.success(msg);
        apiRp.setData(data);
        return apiRp;
    }

    public static ApiRp fail() {
        return new ApiRp(ApiStatus.FAIL);
    }

    public static ApiRp fail(String msg) {
        ApiRp apiRp = ApiRp.fail();
        apiRp.setMsg(msg);
        return apiRp;
    }

    public static ApiRp fail(String msg, Object data) {
        ApiRp apiRp = ApiRp.fail(msg);
        apiRp.setData(data);
        return apiRp;
    }

    public Map<String, Object> toMap() {
        return ImmutableMap.<String, Object>builder()
                .put("code", code)
                .put("msg", msg)
                .put("params", params == null ? "" : params)
                .put("data", data == null ? "" : data)
                .build();
    }
}
