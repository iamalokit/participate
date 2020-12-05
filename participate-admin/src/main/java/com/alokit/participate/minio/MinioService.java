package com.alokit.participate.minio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.messages.Bucket;

@Service
public class MinioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MinioService.class);
	@Autowired
	MinioClient minioClient;

	@Value("${minio.bucketName}")
	private String bucketName;
	
	@Value("${minio.endpoint}")
    private String endpoint;

	public List<Bucket> getAllBuckets() {
		try {
			return minioClient.listBuckets();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public String uploadObject(String objectName, MultipartFile file) {
		String url = "";
		try {
			boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
			if(!isExist) {
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			}
			PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucketName).object(objectName)
					.contentType(file.getContentType())
					.stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();

			minioClient.putObject(putObjectArgs);
			url = endpoint + "/" + bucketName + "/" + objectName;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("Exception occurred while uploading file to minio", e.getMessage());
		}
		return url;
	}

	public boolean deleteObject(String objectName) {
		try {
			minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}