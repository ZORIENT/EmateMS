package com.zorient.etmate.controller;

import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    /*
    * 头像上传
    * */
    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        log.info("文件上传：{}",file.getOriginalFilename());
        String url = aliOSSUtils.upload(file);
        log.info("文件上传成功，文件访问路径：{}",url);

        return Result.success(url);
    }
}
