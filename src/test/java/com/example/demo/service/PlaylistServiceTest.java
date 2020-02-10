package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.domain.Playlist;
import com.example.demo.repository.PlaylistRepository;
import org.aspectj.apache.bcel.util.Play;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaylistServiceTest {

    @Autowired PlaylistService playlistService;
    @Autowired
    PlaylistRepository playlistRepository;

    @Test
    public void 생성테스트() {
        // given
        Playlist playlist = Playlist.createPlaylist("플레이리스트", 1);

        // when
        Playlist save = playlistRepository.save(playlist);

        // then
        assertEquals(playlist, save);
    }

    @Test(expected = IllegalStateException.class)
    public  void 같은_사용자_플레이리스트_중복_예외발생_테스트() throws Exception {
        // given
        String duplicationName = "project";

        Playlist playlist1 = Playlist.createPlaylist(duplicationName, 1);
        Playlist playlist2 = Playlist.createPlaylist(duplicationName, 1);


        // when
        Long p1 = playlistService.create(playlist1);
        Long p2 = playlistService.create(playlist2);

        // then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public  void 다른_사용자_플레이리스트_중복_예외발생_NO_테스트() throws Exception {
        // given
        String duplicationName = "project";

        Playlist playlist1 = Playlist.createPlaylist(duplicationName, 1);
        Playlist playlist2 = Playlist.createPlaylist(duplicationName, 2);


        // when
        Long p1 = playlistService.create(playlist1);
        Long p2 = playlistService.create(playlist2);

        // then
        // 이름이 같아도 예외 발생 안함
    }

}