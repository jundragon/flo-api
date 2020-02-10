package com.example.demo.service;

import com.example.demo.domain.Album;
import com.example.demo.repository.AlbumRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AlbumServiceTest {

    @Autowired AlbumService albumService;
    @Autowired
    AlbumRepository albumRepository;

    @Test
    public void 생성테스트() {
        // given
        Album album = new Album("테스트");

        // when
        Album save = albumRepository.save(album);

        // then
        assertEquals(album, save);
    }
}