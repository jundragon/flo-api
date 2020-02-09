package com.example.flo.repository;

import com.example.flo.domain.Album;
import com.example.flo.domain.Song;
import com.example.flo.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumRepositoryCustom {
    List<Album> searchAlbum(SearchCondition condition);
    List<Song> searchSong(SearchCondition condition);

    Page<Album> listAlbum(String locale, Pageable pageable);

}
