package com.example.flo.albums.repository;

import com.example.flo.albums.domain.Album;
import com.example.flo.albums.domain.Locale;
import com.example.flo.albums.dto.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.flo.albums.domain.QAlbum.album;
import static org.springframework.util.StringUtils.hasText;

public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AlbumRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Album> search(SearchCondition condition) {
        return queryFactory
                .selectFrom(album)
                .where(
                        localeContain(condition.getLocale()),
                        titleContain(condition.getTitle())
                ).fetch();
    }

    private BooleanExpression localeContain(String locale) {
        return hasText(locale) ? album.locales.contains(Locale.getEnum(locale)) : null;
    }

    private BooleanExpression titleContain(String title) {
        return hasText(title) ? album.title.contains(title) : null;
    }
}
