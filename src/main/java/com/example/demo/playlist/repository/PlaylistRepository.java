package com.example.demo.playlist.repository;

import com.example.demo.playlist.domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findByNameAndUserId(String name, int userId);
    List<Playlist> findByUserId(int userId);
}
