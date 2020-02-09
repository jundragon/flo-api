package com.example.flo.service;

import com.example.flo.domain.Member;
import com.example.flo.domain.Playlist;
import com.example.flo.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Transactional
    public Long create(Playlist playlist) {
        validateDuplicatePlaylist(playlist);
        playlistRepository.save(playlist);
        return playlist.getId();
    }

    private void validateDuplicatePlaylist(Playlist playlist) {
        List<Playlist> findPlaylists = playlistRepository.findByName(playlist.getName());
        if (!findPlaylists.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 플레이리스트입니다.");
        }
    }
}
