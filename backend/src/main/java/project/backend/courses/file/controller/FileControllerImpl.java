package project.backend.courses.file.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import project.backend.courses.file.request.FileRequest;
import project.backend.courses.file.service.FileService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/files")
public class FileControllerImpl implements FileController {

    private final FileService fileService;

    @Override
    @GetMapping("/upload")
    public ResponseEntity<String> getUploadUrl(@RequestBody FileRequest fileRequest) {
        return new ResponseEntity<>(fileService.generateUploadUrl(fileRequest), HttpStatus.OK);
    }

    @Override
    @GetMapping("/download")
    public ResponseEntity<String> getDownloadUrl(@RequestBody FileRequest fileRequest) {
        return new ResponseEntity<>(fileService.generateDownloadUrl(fileRequest), HttpStatus.OK);
    }
}
