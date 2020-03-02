package com.example.demo.controller;

import com.example.demo.domain.Album;
import com.example.demo.domain.Song;
import com.example.demo.dto.AlbumDto;
import com.example.demo.dto.SearchCondition;
import com.example.demo.dto.SongDto;
import com.example.demo.service.AlbumService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.Link.*;

@Validated
@RestController
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    /**
     * query parameters 요청 validate 핸들러
     * Internal Server Error 500 -> Bad Request 400
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e) {
        return "not valid due to validation error: " + e.getMessage();
    }

    /**
     * Album 목록 조회
     * @param locale
     * @param pageable
     * @param assembler
     * @return
     */
    @GetMapping("/api/albums")
    public ListResult list(@Valid @NotEmpty String locale, Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Album> albums = albumService.listAlbum(locale, pageable);
        List<AlbumDto> albumDtos = getAlbumDtos(albums.getContent());

        return new ListResult(HttpStatus.OK.value(), getPages(assembler, albums), albumDtos);
    }

    private JSONObject getPages(PagedResourcesAssembler assembler, Page<Album> albums) {
        PagedResources<Album> pr = assembler.toResource(albums);

        JSONObject page = new JSONObject();
        if (pr.hasLink(REL_PREVIOUS)) {
            page.put(REL_PREVIOUS, pr.getLink(REL_PREVIOUS).getHref());
            page.put(REL_FIRST, pr.getLink(REL_FIRST).getHref());
        }

        if (pr.hasLink(REL_NEXT)) {
            page.put(REL_NEXT, pr.getLink(REL_NEXT).getHref());
            page.put(REL_LAST, pr.getLink(REL_LAST).getHref());
        }
        return page;
    }

    /**
     * Album & Song 키워드 검색
     * @param condition
     * @return
     */
    @GetMapping("/api/search")
    public SearchResult search(@Valid SearchCondition condition) {
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
        private T pages;
        private T albums;
    }
}
