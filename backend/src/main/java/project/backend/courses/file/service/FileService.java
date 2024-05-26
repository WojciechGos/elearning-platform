package project.backend.courses.file.service;

public interface FileService {

    String generateUploadUrl(String keyName);
    String generateDownloadUrl(String keyName);

}
