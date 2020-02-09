package com.example.flo.controller;

import com.example.flo.domain.Member;
import com.example.flo.domain.Playlist;
import com.example.flo.service.MemberService;
import com.example.flo.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PlaylistApiController {

    private final PlaylistService playlistService;
    private final MemberService memberService;

    @PostMapping("/api/playlist")
    public CreatePlaylistResponse createPlaylist(@RequestBody @Valid CreatePlaylistRequest request) {

        Long userId = (long) request.getUserId();

        // FIXME : 사용자는 항상 있다고 가정
        Optional<Member> findMember = memberService.findMember(userId);
        Member member = findMember.orElse(new Member());
        memberService.join(member);

        Playlist playlist = Playlist.createPlaylist(request.getName(), member);
        Long id = playlistService.create(playlist);
        return new CreatePlaylistResponse(id);
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
}
