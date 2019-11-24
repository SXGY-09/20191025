package com.sxgy.sp52.core.util.internet;

import com.google.gson.Gson;
import com.sxgy.sp52.core.domain.ApiRp;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author SXGY_09
 * @description Http工具
 * @date 2019-11-23 15:12
 */
public class HttpUtil {
    public static Gson gson=new Gson();

    /**
     * Http返回数据
     * @param response
     * @param apiRp
     */
    public static void doReturn(HttpServletResponse response, ApiRp apiRp)  {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.append(gson.toJson(apiRp));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Http返回数据
     * @param response
     * @param str
     */
    public static void doReturnStr(HttpServletResponse response, String str)  {
        ApiRp apiRp = gson.fromJson(str,ApiRp.class);
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            if (apiRp.getCode()==401||apiRp.getCode()==403){
                response.setStatus(apiRp.getCode());
            }
            PrintWriter out = response.getWriter();
            out.append(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
