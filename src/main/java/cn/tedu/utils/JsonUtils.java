package cn.tedu.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtils {
    /**
     * 响应结果
     * @param response
     * @param obj
     * @throws  IOException
     */
    public static void printResult(HttpServletResponse response,Object obj) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getWriter(),obj);
    }
    /**
     *  把json转换成对象
     * @param request
     * @param tClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T parseJSON2Object(HttpServletRequest request,Class<T> tClass)throws IOException{
        //表单数据进行转换成对象
        return JSON.parseObject(request.getInputStream(),tClass);
    }
}
