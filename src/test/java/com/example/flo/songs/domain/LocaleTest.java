package com.example.flo.songs.domain;

import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(JUnitParamsRunner.class)
public class LocaleTest {
    @Test
    public void 생성확인() throws Exception {
        // given
        Album album1 = new Album("아이유 1집");
        Album album2 = new Album("아이유 2집");
        Album album3 = new Album("트와이스 1집");

        // when
        Locale locale = new Locale("ko");
        locale.addAlbums(album1);
        locale.addAlbums(album2);
        locale.addAlbums(album3);
        
        // then
        assertThat(locale).isNotNull();
        assertEquals("ko로 생성된 지역 서비스", "ko", locale.getCountryCode().getCode());
        assertEquals("앨범의 개수 확인", 3, locale.getAlbums().size());
    }
}