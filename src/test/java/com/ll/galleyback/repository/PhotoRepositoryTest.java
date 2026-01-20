package com.ll.galleyback.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;

import com.ll.galleyback.entity.Photo;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class PhotoRepositoryTest {
    private final PhotoRepository photoRepository;

    @Test
    @DisplayName("사진 저장 성공")
    void save() {
        // given 필요한 데이터 설정
        Photo photo = Photo.builder()
                .title("test")
                .description("test")
                .imageUrl("/uploads/test.jpg")
                .build();

        // when 테스트 동작 수행
        Photo savedPhoto = photoRepository.save(photo);

        // then 결과 검증
        assertThat(savedPhoto.getId()).isNotNull();
        assertEquals(photo.getTitle(), savedPhoto.getTitle());
        assertEquals(photo.getDescription(), savedPhoto.getDescription());
        assertEquals(photo.getImageUrl(), savedPhoto.getImageUrl());
    }

    @Test
    @DisplayName("사진 목록 조회 성공")
    void findAll() {
        photoRepository.save(Photo.builder()
                .title("test")
                .description("test")
                .imageUrl("/uploads/test.jpg")
                .build());
        photoRepository.save(Photo.builder()
                .title("test2")
                .description("test2")
                .imageUrl("/uploads/test2.jpg")
                .build());

        List<Photo> foundPhoto = photoRepository.findAll();
        assertThat(foundPhoto.size()).isEqualTo(2);
    }
}
