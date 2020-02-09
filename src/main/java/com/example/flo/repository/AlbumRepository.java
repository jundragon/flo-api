package com.example.flo.repository;

import com.example.flo.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long>, AlbumRepositoryCustom {

}
