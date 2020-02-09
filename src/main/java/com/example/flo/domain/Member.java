package com.example.flo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private int userId;

    @OneToMany(mappedBy = "member")
    private List<Playlist> playlists = new ArrayList<>();

    public Member() {

    }
}
