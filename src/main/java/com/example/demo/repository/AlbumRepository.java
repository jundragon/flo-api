package com.example.demo.repository;

import com.example.demo.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryCustom {

}
