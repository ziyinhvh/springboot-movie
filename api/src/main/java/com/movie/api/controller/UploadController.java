package com.movie.api.controller;

import com.movie.api.annotation.DisableBaseResponse;
import com.movie.api.mapper.UploadMapper;
import com.movie.api.model.entity.Upload;
import com.movie.api.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 上传图片存放为二进制到mysql
 */
@RestController
@Api(tags = "上传接口")
@RequestMapping("/api/upload")
public class UploadController {

    @Value("${server.port}") // 获取server.port配置
    private String serverPort;

    @Resource // 注入UploadService
    private UploadService uploadService;

    @Resource // 注入UploadMapper
    private UploadMapper uploadMapper;

    /**
     * 上传图片接口
     * @param file 上传的图片文件
     * @return 上传图片的URL
     * @throws Exception 请求参数缺失或上传失败
     */
    @PostMapping("")
    @ApiOperation(value = "上传图片")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_WORKER')") // 需要管理员、用户或工作人员角色才能访问
    @DisableBaseResponse // 禁用BaseResponse
    public String upload(MultipartFile file) throws Exception {
        if (file == null) throw new Exception("请求参数缺失"); // 如果请求参数为空，则抛出异常
        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件"); // 如果文件为空，则抛出异常
        }
        return "http://localhost:" + serverPort + "/api/upload?id=" + uploadService.checkAndSaveUpload(file); // 返回上传图片的URL
    }

    /**
     * 删除图片接口
     * @param id 图片ID
     */
    @DeleteMapping("")
    @ApiOperation(value = "删除图片")
    public void delete(@RequestParam("id") String id) {
        uploadService.deleteById(id); // 调用UploadService的deleteById方法删除图片
    }

    /**
     * 获取图片接口
     * @param id 图片ID
     * @param response HTTP响应
     * @throws Exception 图片不存在
     */
    @GetMapping("")
    @ApiOperation(value = "获取图片")
    @PermitAll // 允许所有人访问
    @DisableBaseResponse // 禁用BaseResponse
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        if ("".equals(id)) {
            return;
        }
        Upload upload = uploadMapper.selectById(id); // 根据图片ID查询图片信息
        if (upload == null) {
            throw new Exception("图片不存在"); // 如果图片不存在，则抛出异常
        }
        byte[] data = upload.getBytes(); // 获取图片二进制数据
        response.setContentType("image/jpeg"); // 设置响应类型为JPEG图片
        response.setCharacterEncoding("UTF-8"); // 设置响应编码为UTF-8
        OutputStream outputStream = response.getOutputStream(); // 获取响应输出流
        InputStream in = new ByteArrayInputStream(data); // 创建字节输入流
        int len;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len); // 将图片数据写入响应输出流
        }
        outputStream.close(); // 关闭响应输出流
    }
}
