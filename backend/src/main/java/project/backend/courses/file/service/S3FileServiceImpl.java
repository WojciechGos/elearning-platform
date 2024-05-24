package project.backend.courses.file.service;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3FileServiceImpl implements FileService{


    private final AmazonS3 s3;

    @Value("{aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public String generateUploadUrl(String fileName) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);

        URL url = s3.generatePresignedUrl(bucketName, fileName, calendar.getTime());
        return url.toString();
    }

    @Override
    public String generateDownloadUrl(String fileName) {

        return null;
    }
}
