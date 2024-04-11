package com.datn.watch.uploadingfiles.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FileStoragePropertiesDto {
    @Data
    @AllArgsConstructor
    public static class UploadFileRequest {
        @NotNull
        private MultipartFile file;
    }
    @Data
    @AllArgsConstructor
    public static class UploadFileResponse {
        private String fileName;
        private String fileDownloadUri;
        private String fileType;
        private long size;
    }

}
