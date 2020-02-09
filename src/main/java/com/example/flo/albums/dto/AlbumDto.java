package com.example.flo.albums.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumDto {
    private Long id;
    private String title;
    private List<SongDto> songs = new ArrayList<>();

    @QueryProjection
    public AlbumDto(Long id, String title, List<SongDto> songs) {
        this.id = id;
        this.title = title;
        this.songs = songs;
    }
}
