package com.ll.galleyback.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
        return PhotoResponse.fromEntity(photo);
    }

    @Transactional
    public PhotoResponse createPhoto(PhotoRequest photoRequest) {
        Photo photo = Photo.builder()
                .title(photoRequest.getTitle())
                .description(photoRequest.getDescription())
                .build();
        photoRepository.save(photo);
        return PhotoResponse.fromEntity(photo);
    }
}
