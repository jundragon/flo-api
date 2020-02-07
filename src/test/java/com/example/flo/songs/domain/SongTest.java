package com.example.flo.songs.domain;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class SongTest {

    @Test
    public void 생성확인() throws Exception {
        // given
        String title = "무릎";
        int track = 1;
        int length = 200;

        Album album = new Album("아이유 1집");

        // when
        Song song = new Song(title, track, length, album);

        // then
        assertThat(song).isNotNull();
        assertEquals("곡 타이틀 확인", title, song.getTitle());
        assertEquals("곡 트랙 확인", track, song.getTrack());
        assertEquals("곡 길이 확인", length, song.getLength());
        assertEquals("앨범 확인", album, song.getAlbum());
    }
}