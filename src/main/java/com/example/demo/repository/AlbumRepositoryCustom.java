package com.example.demo.repository;

import com.example.demo.domain.Album;
import com.example.demo.domain.Song;
import com.example.demo.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumRepositoryCustom {
    List<Album> searchAlbum(SearchCondition condition);
    List<Song> searchSong(SearchCondition condition);

    Page<Album> listAlbum(String locale, Pageable pageable);

}
