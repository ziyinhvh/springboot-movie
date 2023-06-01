package com.movie.api.handler;

import com.movie.api.annotation.DisableBaseResponse;
import com.movie.api.model.support.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一拦截Controller中所有方法的返回值
 * 封装后返回ResponseResult<T>
 */
@ControllerAdvice(basePackages = "com.movie.api")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class c) {
        //如果方法上带有DisableBaseResponse注解， 不处理返回false
        return !methodParameter.hasMethodAnnotation(DisableBaseResponse.class);
    }

    /**
     * 在Controller方法执行完毕，返回结果之前对返回结果进行封装
     *
     * @param o                  Controller方法的返回结果
     * @param methodParameter    Controller方法的参数信息，包括方法名、参数类型、注解等信息
     * @param mediaType          返回结果的媒体类型
     * @param aClass             返回结果的类型
     * @param serverHttpRequest  HTTP请求信息
     * @param serverHttpResponse HTTP响应信息
     * @return 经过封装的响应结果
     */
    @Override
    public ResponseResult<Object> beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果返回结果为null，则返回一个空的ResponseResult对象
        if (o == null) {
            return new ResponseResult<>();
        }
        // 否则将返回结果封装成ResponseResult对象，并返回该对象
        return new ResponseResult<>(o);
    }

}
