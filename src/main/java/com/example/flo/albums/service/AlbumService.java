package com.example.flo.albums.service;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.domain.Song;
import com.example.flo.albums.dto.AlbumDto;
import com.example.flo.albums.dto.SearchCondition;
import com.example.flo.albums.repository.AlbumRepository;
import com.querydsl.core.Tuple;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
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
}
