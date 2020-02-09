package com.example.demo.repository;

import com.example.demo.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByIdIn(List<Long> ids);

}
