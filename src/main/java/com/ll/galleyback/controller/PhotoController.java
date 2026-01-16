package com.ll.galleyback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ll.galleyback.dto.PhotoRequest;
import com.ll.galleyback.dto.PhotoResponse;
import com.ll.galleyback.service.PhotoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping
    public List<PhotoResponse> photoList() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public PhotoResponse photoDetail(@PathVariable Long id) {
        return photoService.getPhoto(id);
    }

    @PostMapping
    public PhotoResponse photoSave(@Valid @ModelAttribute PhotoRequest request,
            @RequestParam MultipartFile file) {
        return photoService.savePhoto(request, file);
    }

    @DeleteMapping("/{id}")
    public void photoDelete(@PathVariable Long id) {
        photoService.deletePhoto(id);
    }
}
