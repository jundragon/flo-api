package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.domain.Song;
import com.example.demo.dto.SearchCondition;
import com.example.demo.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> searchAlbum(SearchCondition condition) {
        return albumRepository.searchAlbum(condition);
    }

    public List<Song> searchSong(SearchCondition condition) {
        return albumRepository.searchSong(condition);
    }

    public Page<Album> listAlbum(String locale, Pageable pageable) {
        return albumRepository.listAlbum(locale, pageable);
    }
}
