package com.example.flo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class SongDto {
    private Long id;
    private String title;
    private int track;
    private int length;

    @QueryProjection
    public SongDto(Long id, String title, int track, int length) {
        this.id = id;
        this.title = title;
        this.track = track;
        this.length = length;
    }
}
