package com.example.flo.albums.repository;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.domain.Song;
import com.example.flo.albums.dto.SearchCondition;

import java.util.List;

public interface AlbumRepositoryCustom {
    public List<Album> searchAlbum(SearchCondition condition);
    public List<Song> searchSong(SearchCondition condition);
}
