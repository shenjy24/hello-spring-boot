package com.jonas.jpa.service;

import com.jonas.bean.BizException;
import com.jonas.bean.SystemCode;
import com.jonas.jpa.repository.minio.MinioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shenjy
 * @createTime 2023/8/6 17:25
 * @description
 */
@Service
@RequiredArgsConstructor
public class MinioService {
    private final MinioRepository minioRepository;

    public void putObject(String bucketName, String objectName, MultipartFile multipartFile) {
        try {
            minioRepository.putObject(bucketName, objectName, multipartFile);
        } catch (Exception e) {
            throw new BizException(SystemCode.SERVER_ERROR);
        }
    }

    public String getObjectUrl(String bucketName, String objectName) {
        try {
            return minioRepository.getObjectUrl(bucketName, objectName);
        } catch (Exception e) {
            throw new BizException(SystemCode.SERVER_ERROR);
        }
    }
}
