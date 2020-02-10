package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ElementCollection(targetClass = Locale.class)
    @Enumerated(EnumType.STRING)
    private Set<Locale> locales = new HashSet<>();

    @OneToMany(mappedBy = "album", cascade = ALL)
    private List<Song> songs = new ArrayList<>();

    public Album(String title) {
        this.title = title;
    }

    // 연관 관계 메서드 (양방향) //
    public void addSong(Song song) {
        songs.add(song);
        song.joinAlbum(this);
    }

    // 생성 메서드 //
    public static Album createAlbum(String title, List<String> locales, List<Song> songs) {
        Album album = new Album(title);

        for (String locale : locales) {
            if (locale.equals("all")) {
                // 모든 지역 서비스 코드를 추가
                album.locales = Arrays.stream(Locale.values())
                        .collect(Collectors.toSet());
                break;
            }

            album.locales.add(Locale.getEnum(locale));
        }
        for (Song song : songs) {
            album.addSong(song);
        }

        return album;
    }

}