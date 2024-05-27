package project.backend.courses.file.service;

import project.backend.courses.file.request.FileRequest;

public interface FileService {

    String generateUploadUrl(FileRequest fileRequest);
    String generateDownloadUrl(FileRequest fileRequest);

}
