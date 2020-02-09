package com.example.flo.albums.controller;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.dto.AlbumDto;
import com.example.flo.albums.dto.SearchCondition;
import com.example.flo.albums.dto.SongDto;
import com.example.flo.albums.service.AlbumService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AlbumApiController {

    private final AlbumService albumService;

    @GetMapping("/api/search")
    public Result search(SearchCondition condition) {
        List<Album> searchAlbums = albumService.searchAlbum(condition);
        List<AlbumDto> collect = searchAlbums.stream()
                .map(a -> new AlbumDto(
                        a.getId(),
                        a.getTitle(),
                        a.getSongs().stream()
                                .map(s -> new SongDto(
                                        s.getId(),
                                        s.getTitle(),
                                        s.getTrack(),
                                        s.getLength()
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());

        return new Result(collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    private static class Result<T> {
        private int count;
        private T data;
    }
}
