package com.jonas.jpa.controller;

import com.jonas.jpa.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenjy
 * @createTime 2023/8/6 17:28
 * @description
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
public class MinioController {
    private final MinioService minioService;

    @PostMapping("/putObject")
    public void putObject(String bucketName, String objectName, MultipartFile file) {
        minioService.putObject(bucketName, objectName, file);
    }

    @PostMapping("/getObjectUrl")
    public String getObjectUrl(String bucketName, String objectName) {
        return minioService.getObjectUrl(bucketName, objectName);
    }
}
