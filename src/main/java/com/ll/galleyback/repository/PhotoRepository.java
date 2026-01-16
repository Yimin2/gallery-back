package com.ll.galleyback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ll.galleyback.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}