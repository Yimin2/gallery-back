package com.ll.galleyback.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ll.galleyback.dto.PhotoRequest;
import com.ll.galleyback.dto.PhotoResponse;
import com.ll.galleyback.entity.Photo;
import com.ll.galleyback.repository.PhotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final FileStorageService fileStorageService;

    public List<PhotoResponse> getAllPhotos() {
        return photoRepository.findAll().stream()
                .map(PhotoResponse::fromEntity)
                .toList();
    }

    public PhotoResponse getPhoto(Long id) {
        Photo photo = findByid(id);
        return PhotoResponse.fromEntity(photo);
    }

    @Transactional
    public PhotoResponse savePhoto(PhotoRequest photoRequest, MultipartFile file) {
        String imageUrl = fileStorageService.upload(file);
        Photo photo = Photo.builder()
                .title(photoRequest.getTitle())
                .description(photoRequest.getDescription())
                .imageUrl(imageUrl)
                .build();
        photoRepository.save(photo);
        return PhotoResponse.fromEntity(photo);
    }

    @Transactional
    public void deletePhoto(Long id) {
        Photo photo = findByid(id);
        fileStorageService.delete(photo.getImageUrl());
        photoRepository.delete(photo);
    }

    private Photo findByid(Long id) {
        return photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo not found"));
    }
}
