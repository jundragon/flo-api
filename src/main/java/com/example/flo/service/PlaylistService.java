package com.example.flo.service;

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

    public List<Playlist> userList(int userId) {
        return playlistRepository.findByUserId(userId);
    }

    private void validateDuplicatePlaylist(Playlist playlist) {
        List<Playlist> findPlaylists = playlistRepository.findByNameAndUserId(playlist.getName(), playlist.getUserId());
        if (!findPlaylists.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 플레이리스트입니다.");
        }
    }

    @Transactional
    public String deleteProjectlist(Long id) {
        Optional<Playlist> playlist = playlistRepository.findById(id);

        if (!playlist.isPresent()) {
            throw new IllegalStateException("삭제 대상 플레이리스트가 없습니다.");
        }

        String playlistName = playlist.get().getName();
        playlistRepository.delete(playlist.get());
        return playlistName;
    }
}
