package com.ycyoes.test.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

/**
 * @description Minio上传测试
 * @author ycyoes
 * @version 2021-11-03
 */
public class FileUploader {
    public static void main(String[] args) throws Exception {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://172.16.100.236:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("bsc").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("bsc").build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("bsc")
                            .object("1.jpg")
                            .filename("e:/1.jpg")
                            .build());
            System.out.println(
                    "'e:/1.jpg' is successfully uploaded as "
                            + "object '1.jpg' to bucket 'asiatrip'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
//            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
