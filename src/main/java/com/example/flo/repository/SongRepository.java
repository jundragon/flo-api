package com.example.flo.repository;

import com.example.flo.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findAllByIdIn(List<Long> ids);

}
