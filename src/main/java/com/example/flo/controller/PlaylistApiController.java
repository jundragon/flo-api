package com.example.flo.controller;

import com.example.flo.domain.Playlist;
import com.example.flo.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PlaylistApiController {

    private final PlaylistService playlistService;

    @GetMapping("/api/playlists/{id}")
    public ResultPlaylists playlists(@PathVariable("id") int id) {
        List<Playlist> userPlaylists = playlistService.userList(id);
        List<PlaylistDto> collect = userPlaylists.stream()
                .map(p -> new PlaylistDto(p.getId(), p.getName(), p.getUserId()))
                .collect(Collectors.toList());
        return new ResultPlaylists(collect.size(), collect);
    }

    @PostMapping("/api/playlists")
    public CreatePlaylistResponse createPlaylist(@RequestBody @Valid CreatePlaylistRequest request) {

        Playlist playlist = Playlist.createPlaylist(request.getName(), request.getUserId());
        Long id = playlistService.create(playlist);
        return new CreatePlaylistResponse(id);
    }

    @DeleteMapping("/api/playlists/{id}")
    public DeletePlaylistResponse deletePlaylist(@PathVariable("id") Long id) {
        String playlistName = playlistService.deleteProjectlist(id);
        return new DeletePlaylistResponse(id, playlistName);
    }

    @Data
    @AllArgsConstructor
    private static class CreatePlaylistResponse {
        private Long id;
    }

    @Data
    @AllArgsConstructor
    private static class CreatePlaylistRequest {

        @NotEmpty
        private String name;
        private int userId;
    }

    @Data
    @AllArgsConstructor
    private static class PlaylistDto {
        private Long id;
        private String name;
        private int userId;
    }

    @Data
    @AllArgsConstructor
    private static class ResultPlaylists<T> {
        private int count;
        private T playlists;
    }

    @Data
    @AllArgsConstructor
    private static class DeletePlaylistResponse {
        private Long id;
        private String name;
    }
}
