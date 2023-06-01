package com.movie.api.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 该类为响应工具类，用于向客户端返回 JSON 数据
 */
public final class ResponseUtil {

    /**
     * 将对象转换为 JSON 格式并写入 HttpServletResponse 中
     *
     * @param response HttpServletResponse 对象
     * @param o        要转换为 JSON 的对象
     */
    public static void writeJson(HttpServletResponse response, Object o) {
        // 设置跨域请求头信息
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        // 设置响应的内容类型为 JSON 格式
        response.setContentType("application/json;charset=utf-8");
        // 设置响应状态码为 200
        response.setStatus(200);
        try {
            PrintWriter writer = response.getWriter();
            // 将对象转换为 JSON 格式并写入 HttpServletResponse 中
            writer.write(JSON.toJSONString(o));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("write json error");
            e.printStackTrace();
        }
    }

}
