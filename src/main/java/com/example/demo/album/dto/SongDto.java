package com.example.demo.album.dto;

import lombok.Data;

@Data
public class SongDto {
    private Long id;
    private String title;
    private int track;
    private int length;

    public SongDto(Long id, String title, int track, int length) {
        this.id = id;
        this.title = title;
        this.track = track;
        this.length = length;
    }
}
