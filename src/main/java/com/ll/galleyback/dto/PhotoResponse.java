package com.ll.galleyback.dto;

import com.ll.galleyback.entity.Photo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PhotoResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;

    public static PhotoResponse fromEntity(Photo photo) {
        return PhotoResponse.builder()
                .id(photo.getId())
                .title(photo.getTitle())
                .description(photo.getDescription())
                .imageUrl(photo.getImageUrl())
                .build();
    }
}
