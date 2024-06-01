package project.backend.courses.utils.file.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevFileService implements FileService{
    @Override
    public String generateUploadUrl(String keyName, String contentType) {
        return null;
    }

    @Override
    public String generateDownloadUrl(String keyName, String contentType) {
        return null;
    }

    @Override
    public void deleteFile(String keyName) {

    }
}
