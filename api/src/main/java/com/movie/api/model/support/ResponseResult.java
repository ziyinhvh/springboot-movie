package com.movie.api.model.support;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 响应结果封装类，用于封装请求的响应结果
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private Integer code; // 响应结果状态码

    private boolean success; // 响应结果是否成功

    private String msg; // 响应结果信息

    private T data; // 响应结果数据

    /**
     * 无参构造方法，用于构造一个默认的响应结果
     */
    public ResponseResult() {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = null;
    }

    /**
     * 构造方法，用于构造一个带有数据的成功响应结果
     *
     * @param data 响应结果中的数据
     */
    public ResponseResult(T data) {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = data;
    }

    /**
     * 构造方法，用于构造一个带有数据和信息的成功响应结果
     *
     * @param msg  响应结果信息
     * @param data 响应结果中的数据
     */
    public ResponseResult(String msg, T data) {
        this.code = 200;
        this.success = true;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 构造方法，用于构造一个错误的响应结果
     *
     * @param code 响应结果状态码
     * @param msg  响应结果信息
     */
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
        this.data = null;
    }

}
