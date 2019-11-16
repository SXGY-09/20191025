package com.sxgy.sp52.core;

import com.sxgy.sp52.core.security.Md5Util;
import org.junit.Test;

/**
 * @author SXGY_09
 * @description 功能测试
 * @date 2019-11-16 09:55
 */
public class Test1 {
    @Test
    public void md5(){
        String password="789987";
        System.out.println(Md5Util.MD5Encode(password,"utf-8",true));
    }
}
