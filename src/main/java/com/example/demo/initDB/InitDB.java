package com.example.demo.initDB;

import com.example.demo.domain.Album;
import com.example.demo.domain.Song;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Profile("dev")
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;
    private final EntityManager em;

    @PostConstruct
    public void init() {
        initService.insertData();
    }

    @Component
    @RequiredArgsConstructor
    private static class InitService {

        private final EntityManager em;

        @Transactional
        public void insertData() {

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<AlbumDto>> typeReference = new TypeReference<List<AlbumDto>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/albums.json");

            try {
                List<AlbumDto> albumDtos = mapper.readValue(inputStream, typeReference);

                for (AlbumDto albumDto : albumDtos) {
                    Album album = Album.createAlbum(
                            albumDto.album_title
                            , albumDto.locales
                            , albumDto.songs.stream()
                                    .map(s -> new Song(s.title, s.track, s.length))
                                    .collect(Collectors.toList()));

                    em.persist(album);
                }
            } catch (IOException e) {
                // TODO 로그 처리하기
                // System.out.println("Unable to save models: " + e.getMessage());
            }
        }

        @Data
        @AllArgsConstructor
        private static class AlbumDto {
            @JsonProperty("album_title")
            private String album_title;
            @JsonProperty("locales")
            private List<String> locales = new ArrayList<>();
            @JsonProperty("songs")
            private List<SongDto> songs = new ArrayList<>();

            public AlbumDto () {
                super();
            }
        }

        @Data
        @AllArgsConstructor
        private static class SongDto {
            @JsonProperty("title")
            private String title;
            @JsonProperty("length")
            private int length;
            @JsonProperty("track")
            private int track;

            public SongDto () {
                super();
            }
        }
    }
}