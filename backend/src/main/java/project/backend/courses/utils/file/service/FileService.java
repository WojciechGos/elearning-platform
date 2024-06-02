package project.backend.courses.utils.file.service;

public interface FileService {

    String generateUploadUrl(String keyName, String contentType);
    String generateDownloadUrl(String keyName, String contentType);
    void deleteFile(String keyName);
}
