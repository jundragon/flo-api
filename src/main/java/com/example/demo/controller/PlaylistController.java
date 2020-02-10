package com.example.demo.controller;

import com.example.demo.domain.Playlist;
import com.example.demo.service.PlaylistService;
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
public class PlaylistController {

    private final PlaylistService playlistService;

    /**
     * 사용자의 플레이리스트 조회
     * @param userId
     * @return
     */
    @GetMapping("/api/playlists/{userId}")
    public ResultPlaylists playlists(@PathVariable("userId") int userId) {
        List<Playlist> userPlaylists = playlistService.userList(userId);
        List<PlaylistDto> collect = userPlaylists.stream()
                .map(p -> new PlaylistDto(p.getId(), p.getName(), p.getUserId()))
                .collect(Collectors.toList());
        return new ResultPlaylists(collect.size(), collect);
    }

    /**
     * 플레이리스트 생성
     * @param request
     * @return
     */
    @PostMapping("/api/playlists")
    public CreatePlaylistResponse createPlaylist(@RequestBody @Valid CreatePlaylistRequest request) {

        Playlist playlist = Playlist.createPlaylist(request.getName(), request.getUserId());
        Long id = playlistService.create(playlist);
        return new CreatePlaylistResponse(id);
    }

    /**
     * 플레이리스트에 곡 추가, 앨범 내 모든 곡 추가 가능
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/playlists/{id}")
    public AddPlaylistResponse updatePlaylist(@PathVariable("id") Long id,
                                              @RequestBody @Valid AddPlaylistRequest request) {
        int count = playlistService.addSongs(id, request.album, request.songs);
        return new AddPlaylistResponse(count);
    }

    /**
     * 사용자의 플레이리스트 1건 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/playlists/{id}")
    public DeletePlaylistResponse deletePlaylist(@PathVariable("id") Long id) {
        String playlistName = playlistService.deleteProjectlist(id);
        return new DeletePlaylistResponse(id, playlistName);
    }

    // TODO 테스트를 위해 아래의 DTO 밖으로 빼야 함.

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

    @Data
    @AllArgsConstructor
    private static class AddPlaylistRequest {
        private Long album;
        private List<Long> songs;
    }

    @Data
    @AllArgsConstructor
    private static class AddPlaylistResponse {
        private int count;
    }

}
