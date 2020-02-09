package com.example.demo.repository;

import com.example.demo.domain.Album;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlbumRepositoryTest {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    EntityManager em;

    @Before
    public void 테스트데이터생성() {
        albumRepository.save(new Album("앨범1"));
        albumRepository.save(new Album("앨범2"));
        albumRepository.save(new Album("앨범3"));
        albumRepository.save(new Album("앨범4"));
        albumRepository.save(new Album("앨범5"));
        albumRepository.save(new Album("앨범6"));
        albumRepository.save(new Album("앨범7"));
        albumRepository.save(new Album("앨범8"));
        albumRepository.save(new Album("앨범9"));
        albumRepository.save(new Album("앨범10"));
    }

    @Test
    public void 기본테스트() throws Exception {
        Album album = new Album("앨범 1");
        albumRepository.save(album);

        Album findAlbum = albumRepository.findById(album.getId()).get();
        assertThat(findAlbum).isEqualTo(album);
    }

    @Test
    public void 페이징테스트() throws Exception {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);

        // when
        Page<Album> page = albumRepository.findAll(pageRequest);

        // then
        List<Album> content = page.getContent();
        long totalElements = page.getTotalElements();

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(4);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();

    }

}