package com.example.flo.songs.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Locale {

    @Id @GeneratedValue
    @Column(name = "locale_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 4)
    @NotNull
    private CountryCode countryCode; // [all, ko, en, ja ...]

    @ManyToMany
    @JoinTable(name = "locale_album",
            joinColumns = @JoinColumn(name = "locale_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albums = new ArrayList<>();

    public Locale(String countryCode) {
        this.countryCode = CountryCode.getEnum(countryCode);
    }

    // 비즈니스 로직 //
    public void addAlbums(Album album) {
        this.albums.add(album);
    }
}
