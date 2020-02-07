package com.example.flo.songs.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album {

    @Id @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "albums")
    private Set<Locale> locales = new HashSet<>();

    public Album(String title) {
        this.title = title;
    }
}
