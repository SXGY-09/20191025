package com.sxgy.sp52.core.exception;

import com.sxgy.sp52.core.domain.ApiRp;
import lombok.Data;

/**
 * @author SXGY_09
 * @description 业务异常父类
 * @date 2019-11-23 17:37
 */
@Data
public class ApiException extends RuntimeException{
    private ApiRp apiRp = ApiRp.fail();
    public ApiException(ApiRp apiRp){
        super(apiRp.getMsg());
        this.apiRp=apiRp;
    }
    public ApiException(String msg){
        super(msg);
        this.apiRp.setMsg(msg);
    }
    public ApiException(Integer code, String msg){
        super(msg);
        this.apiRp.setCode(code);
        this.apiRp.setMsg(msg);
    }
}
