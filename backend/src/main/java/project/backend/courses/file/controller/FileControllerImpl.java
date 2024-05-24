package project.backend.courses.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import project.backend.courses.file.service.FileService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/files")
public class FileControllerImpl implements FileController {


    private final FileService fileService;

    @Override
    @GetMapping("/upload")
    public ResponseEntity<String> getUploadUrl() {
        return new ResponseEntity<>(fileService.generateUploadUrl("test"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getDownloadUrl() {
        return null;
    }
}
