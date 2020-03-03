package com.example.demo.album.repository;

import com.example.demo.album.domain.Album;
import com.example.demo.album.domain.Locale;
import com.example.demo.album.domain.Song;
import com.example.demo.album.dto.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.album.domain.QAlbum.album;
import static com.example.demo.album.domain.QSong.song;
import static org.springframework.util.StringUtils.hasText;

public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AlbumRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Album> searchAlbum(SearchCondition condition) {
        return queryFactory
                .selectFrom(album)
                .where(
                        localeContain(condition.getLocale()),
                        albumTitleContain(condition.getTitle())
                ).fetch();
    }

    @Override
    public List<Song> searchSong(SearchCondition condition) {
        return queryFactory
                .selectFrom(song)
                .join(song.album, album)
                .where(
                        localeContain(condition.getLocale()),
                        songTitleContain(condition.getTitle())
                ).fetch();
    }

    @Override
    public Page<Album> listAlbum(String locale, Pageable pageable) {

        // content 와 count 쿼리를 분리함
        List<Album> content = getAlbums(locale, pageable);

        JPAQuery<Album> countQuery = queryFactory
                .selectFrom(album)
                .where(
                        localeContain(locale)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private List<Album> getAlbums(String locale, Pageable pageable) {
        return queryFactory
                    .selectFrom(album)
                    .where(
                            localeContain(locale)
                    )
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
    }

    private BooleanExpression localeContain(String locale) {
        return hasText(locale) ? album.locales.contains(Locale.getEnum(locale)) : null;
    }

    private BooleanExpression albumTitleContain(String title) {
        return hasText(title) ? album.title.contains(title) : null;
    }

    private BooleanExpression songTitleContain(String title) {
        return hasText(title) ? song.title.contains(title) : null;
    }
}
