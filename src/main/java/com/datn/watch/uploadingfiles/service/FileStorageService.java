package com.datn.watch.uploadingfiles.service;

import com.pet.market.api.uploadingfiles.client.S3Client;
import com.pet.market.api.uploadingfiles.dto.FileStoragePropertiesDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private final S3Client s3Client;

    public FileStorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public Object uploadFile(String folder, MultipartFile file) {
        String fileName = s3Client.uploadFile(folder, file);
        return new FileStoragePropertiesDto.UploadFileResponse(fileName, fileName,
                file.getContentType(), file.getSize());
    }
}
