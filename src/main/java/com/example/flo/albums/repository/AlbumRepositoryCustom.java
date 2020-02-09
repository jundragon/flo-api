package com.example.flo.albums.repository;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.dto.SearchCondition;

import java.util.List;

public interface AlbumRepositoryCustom {
    public List<Album> search(SearchCondition condition);
}
