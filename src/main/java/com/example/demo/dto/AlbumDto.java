package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlbumDto {
    private Long id;
    private String title;
    private List<SongDto> songs = new ArrayList<>();

    public AlbumDto(Long id, String title, List<SongDto> songs) {
        this.id = id;
        this.title = title;
        this.songs = songs;
    }
}
