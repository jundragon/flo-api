package com.example.demo.album.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Song {

    @Id @GeneratedValue
    @Column(name = "song_id")
    private Long id;

    private String title;

    @Min(1)
    private int track;
    @Min(0)
    private int length;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

//    @ManyToMany(mappedBy = "songs")
//    private List<Playlist> playlists = new ArrayList<>();

    public Song(String title, int track, int length) {
        this.title = title;
        this.track = track;
        this.length = length;
    }

    public void joinAlbum(Album album) {
        this.album = album;
    }
}