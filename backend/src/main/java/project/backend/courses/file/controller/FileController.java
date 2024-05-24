package project.backend.courses.file.controller;

import org.springframework.http.ResponseEntity;

public interface FileController {

    ResponseEntity<String> getUploadUrl();

    ResponseEntity<String> getDownloadUrl();
}
