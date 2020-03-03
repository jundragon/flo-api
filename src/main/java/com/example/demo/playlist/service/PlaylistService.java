package com.example.demo.playlist.service;

import com.example.demo.album.domain.Album;
import com.example.demo.playlist.domain.Playlist;
import com.example.demo.album.domain.Song;
import com.example.demo.album.repository.AlbumRepository;
import com.example.demo.playlist.repository.PlaylistRepository;
import com.example.demo.album.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

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

    @Transactional
    public int addSongs(Long id, Long album_id, List<Long> songs) {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        if (!playlist.isPresent()) {
            throw new IllegalStateException("플레이리스트가 없습니다.");
        }

        // Album 에 있는 곡을 다 가져와서 songList 에 추가
        Optional<Album> findAlbum = albumRepository.findById(album_id);

        List<Song> songList = new ArrayList<>();
        if (findAlbum.isPresent()) {
            songList = findAlbum.get().getSongs();
        }

        if (!songs.isEmpty()) {
            List<Song> findSongs = songRepository.findAllByIdIn(songs);
            if (!findSongs.isEmpty()) {
                songList.addAll(findSongs);
            }
        }

        if (!songList.isEmpty()) {
            for (Song song : songList) {
                playlist.get().addSong(song);
            }
        }

        return songList.size(); // 추가한 곡의 갯수를 반환
    }
}
