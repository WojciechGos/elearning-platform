package project.backend.courses.utils.file.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;


@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3FileServiceImpl implements FileService {

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Presigner presigner;
    private final S3Client s3Client;

    @Override
    public String generateUploadUrl(String keyName, String contentType) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .contentType(contentType)
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))  // The URL expires in 10 minutes.
                .putObjectRequest(objectRequest)
                .build();


        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

        return presignedRequest.url().toExternalForm();

    }

    @Override
    public String generateDownloadUrl(String keyName, String contentType) {


        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .responseContentType(contentType)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))  // The URL will expire in 10 minutes.
                .getObjectRequest(objectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
//            logger.info("Presigned URL: [{}]", presignedRequest.url().toString());
//            logger.info("HTTP method: [{}]", presignedRequest.httpRequest().method());

        return presignedRequest.url().toExternalForm();

    }

    @Override
    public void deleteFile(String keyName) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3Client.deleteObject(deleteObjectRequest);

    }
}
