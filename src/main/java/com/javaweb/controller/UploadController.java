package com.javaweb.controller;

import com.javaweb.pojo.Result;
import com.javaweb.utils.R2Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private R2Utils r2Utils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名:{}", image.getOriginalFilename());
        String url = r2Utils.upload(image);
        log.info("文件上传完成，文件访问的url:{}", url);
        return Result.success(url);
    }
}
