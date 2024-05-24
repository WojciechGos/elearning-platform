package project.backend.courses.file.service;

public interface FileService {

    String generateUploadUrl(String fileName);
    String generateDownloadUrl(String fileName);

}
