package project.backend.courses.file.controller;

import org.springframework.http.ResponseEntity;
import project.backend.courses.file.request.FileRequest;

public interface FileController {

    ResponseEntity<String> getUploadUrl(FileRequest fileRequest);

    ResponseEntity<String> getDownloadUrl(FileRequest fileRequest);
}
