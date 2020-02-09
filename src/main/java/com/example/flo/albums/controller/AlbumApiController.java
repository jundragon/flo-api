package com.example.flo.albums.controller;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.domain.Song;
import com.example.flo.albums.dto.AlbumDto;
import com.example.flo.albums.dto.SearchCondition;
import com.example.flo.albums.dto.SongDto;
import com.example.flo.albums.service.AlbumService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AlbumApiController {

    private final AlbumService albumService;

    @GetMapping("/api/albums")
    public ListResult list(String locale, Pageable pageable) {
        Page<Album> albums = albumService.listAlbum(locale, pageable);
        List<AlbumDto> albumDtos = getAlbumDtos(albums.getContent());
        return new ListResult(HttpStatus.OK.value(), albumDtos);
    }

    @GetMapping("/api/search")
    public SearchResult search(SearchCondition condition) {
        List<Album> searchAlbums = albumService.searchAlbum(condition);
        List<AlbumDto> albumDtos = getAlbumDtos(searchAlbums);

        List<Song> searchSongs = albumService.searchSong(condition);
        List<SongDto> songDtos = getSongDtos(searchSongs);

        return new SearchResult(albumDtos, songDtos);
    }

    private List<SongDto> getSongDtos(List<Song> searchSongs) {
        return searchSongs.stream()
                    .map(s -> new SongDto(
                            s.getId(),
                            s.getTitle(),
                            s.getTrack(),
                            s.getLength()
                    )).collect(Collectors.toList());
    }

    private List<AlbumDto> getAlbumDtos(List<Album> searchAlbums) {
        return searchAlbums.stream()
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
    }

    @Data
    @AllArgsConstructor
    private static class SearchResult<T> {
        private T albums;
        private T songs;
    }

    @Data
    @AllArgsConstructor
    private static class ListResult<T> {
        private int statusCode;
        private T data;
    }


}
