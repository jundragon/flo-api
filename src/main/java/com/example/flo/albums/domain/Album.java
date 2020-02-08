package com.example.flo.albums.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album {

    @Id @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "albums", cascade = ALL)
    private Set<Locale> locales = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = ALL)
    private List<Song> songs = new ArrayList<>();

    public Album(String title) {
        this.title = title;
    }

    // 연관 관계 메서드 (양방향) //
    public void addLocale(Locale locale) {
        locales.add(locale);
        locale.registerAlbum(this);
    }

    public void addSong(Song song) {
        songs.add(song);
        song.joinAlbum(this);
    }

    // 생성 메서드 //
    public static Album createAlbum(String title, List<String> locales, List<Song> songs) {
        Album album = new Album(title);

        for (String locale : locales) {
            if (locale.equals(Locale.ALL)) {
                // 모든 지역 서비스 코드를 추가
                Set<Locale> localeList = Arrays.stream(CountryCode.values())
                        .map(code -> new Locale(code.getCode()))
                        .collect(Collectors.toSet());

                album.locales = localeList;
                break;
            }

            album.addLocale(new Locale(locale));
        }
        for (Song song : songs) {
            album.addSong(song);
        }

        return album;
    }

}
