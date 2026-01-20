package com.ll.galleyback.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ll.galleyback.dto.PhotoResponse;
import com.ll.galleyback.service.PhotoService;

import lombok.RequiredArgsConstructor;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class PhotoControllerTest {
    private final MockMvc mockMvc;
    
    @MockitoBean
    private PhotoService photoService;

    @Test
    @DisplayName("GET /api/photos")
    void list() throws Exception {
        List<PhotoResponse> photos = List.of(
                PhotoResponse.builder()
                        .id(1L)
                        .title("test")
                        .imageUrl("/uploads/test.jpg")
                        .build(),
                PhotoResponse.builder()
                        .id(2L)
                        .title("test2")
                        .imageUrl("/uploads/test2.jpg")
                        .build());
        given(photoService.getAllPhotos()).willReturn(photos);
        
        mockMvc.perform(get("/api/photos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(photos.get(0).getTitle()));
    }
}