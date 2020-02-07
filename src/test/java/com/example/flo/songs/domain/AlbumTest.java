package com.example.flo.songs.domain;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AlbumTest {

    @Test
    public void 생성확인() throws Exception {
        // given
        String title = "아이유 1집";

        // when
        Album album = new Album(title);

        // then
        assertThat(album).isNotNull();
        assertThat(album.getTitle()).isEqualTo(title);
    }
}