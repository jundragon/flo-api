package com.example.flo.service;

import com.example.flo.domain.Album;
import com.example.flo.domain.Song;
import com.example.flo.dto.SearchCondition;
import com.example.flo.repository.AlbumRepository;
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
