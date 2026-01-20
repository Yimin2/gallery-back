package com.ll.galleyback.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;


import com.ll.galleyback.entity.Photo;
import com.ll.galleyback.repository.PhotoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class PhotoIntegrationTest {
    private final MockMvc mockMvc;
    private final PhotoRepository photoRepository;
    @Test
    @DisplayName("사진 목록 조회")
    void findAll() throws Exception {
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

        mockMvc.perform(get("/api/photos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("test"));
    }
}
