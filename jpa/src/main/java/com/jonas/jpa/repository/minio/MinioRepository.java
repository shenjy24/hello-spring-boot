package com.jonas.jpa.repository.minio;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author shenjy
 * @createTime 2023/8/6 17:09
 * @description
 */
@Repository
@RequiredArgsConstructor
public class MinioRepository {

    private final MinioClient minioClient;

    private final int PART_SIZE = 10485760;

    public boolean bucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    public void putObject(String bucketName, String objectName, MultipartFile multipartFile) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)  // 可以携带目录
                        .stream(inputStream, multipartFile.getInputStream().available(), PART_SIZE)
                        .contentType(multipartFile.getContentType())
                        .build());
    }

    public String getObjectUrl(String bucketName, String objectName) throws Exception {
        if (!bucketExists(bucketName)) {
            return "";
        }

        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(10, TimeUnit.MINUTES)  //default 10 min
                        .build());
    }
}
