package com.ll.galleyback.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@ConditionalOnMissingBean(S3FileStorageService.class)
public class FileStorageServiceImpl implements FileStorageService {
    
    @Value("${file.upload-dir}")
    private String uploadPath;
    
    @Override
    public String upload(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadPath, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    @Override
    public void delete(String imageUrl) {
        try {
            Path filePath = Paths.get(uploadPath, imageUrl.replace("/uploads/", ""));
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("File delete failed", e);
        }
    }
}
