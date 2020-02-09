package com.example.flo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist {

    @Id
    @GeneratedValue
    @Column(name = "playlist_id")
    private Long id;

    private int userId;

    private String name;

    @ManyToMany
    @JoinTable(name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs = new ArrayList<>();

    public void addSongs(Song song){
        this.songs.add(song);
    }

    // 생성 메서드 //
    public static Playlist createPlaylist(String name, int userId, Song... songs) {

        Playlist playlist = new Playlist();
        playlist.name = name;
        playlist.userId = userId;

        for (Song song : songs) {
            playlist.addSongs(song);
        }

        return playlist;
    }

    public static Playlist createPlaylist(String name, int userId) {

        Playlist playlist = new Playlist();
        playlist.name = name;
        playlist.userId = userId;

        return playlist;
    }
}
