
package com.ll.galleyback.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ll.galleyback.dto.PhotoResponse;
import com.ll.galleyback.entity.Photo;
import com.ll.galleyback.repository.PhotoRepository;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {
    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private PhotoService photoService;

    @Test
    @DisplayName("사진 목록 조회")
    void findAll() {
        List<Photo> photos = List.of(
                createPhoto("test", "/uploads/test.jpg"),
                createPhoto("test2", "/uploads/test2.jpg"));

        given(photoRepository.findAll()).willReturn(photos);

        List<PhotoResponse> result = photoService.getAllPhotos();

        assertThat(result).hasSize(2);
    }

    private Photo createPhoto(String title, String imageUrl) {
        return Photo.builder()
                .title(title)
                .imageUrl(imageUrl)
                .build();
    }

    @Test
    @DisplayName("사진 상세 조회")
    void findById() {
        Photo photo = createPhoto("test", "/uploads/test.jpg");
        given(photoRepository.findById(1L)).willReturn(Optional.of(photo));
        PhotoResponse result = photoService.getPhoto(1L);

        assertThat(result.getTitle()).isEqualTo(photo.getTitle());
        assertThat(result.getImageUrl()).isEqualTo(photo.getImageUrl());
    }
}