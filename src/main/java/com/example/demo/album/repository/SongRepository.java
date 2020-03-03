package com.example.demo.album.repository;

import com.example.demo.album.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByIdIn(List<Long> ids);

}
