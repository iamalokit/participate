package com.alokit.participate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

@Configuration
public class MinioConfig {
    @Value("${minio.accessKey}")
    String accessKey;
    @Value("${minio.secretKey}")
    String secretKey;
    @Value("${minio.endpoint}")
    String endpoint;

    @Bean
    public MinioClient generateMinioClient() {
        try {
            MinioClient client = MinioClient.builder()
            		.endpoint(endpoint)
            		.credentials(accessKey, secretKey)
            		.build();
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}