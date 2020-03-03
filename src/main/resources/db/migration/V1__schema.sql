drop table if exists album;
drop table if exists album_locales;
drop table if exists hibernate_sequence;
drop table if exists playlist;
drop table if exists playlist_song;
drop table if exists song;

create table album (
    album_id bigint not null,
    title varchar(255),
    primary key (album_id)
);

create table album_locales (
    album_album_id bigint not null,
    locales varchar(255)
);

create table hibernate_sequence (
    next_val bigint
);

insert into hibernate_sequence values ( 1 );

create table playlist (
    playlist_id bigint not null,
    name varchar(255),
    user_id integer not null,
    primary key (playlist_id)
);

create table playlist_song (
    playlist_id bigint not null,
    song_id bigint not null
);

create table song (
    song_id bigint not null,
    length integer not null,
    title varchar(255),
    track integer not null,
    album_id bigint,
    primary key (song_id)
);

alter table album_locales
    add constraint AlbumLocaleID
        foreign key (album_album_id)
            references album (album_id);

alter table playlist_song
    add constraint SongID
        foreign key (song_id)
            references song (song_id);

alter table playlist_song
    add constraint PlaylistID
        foreign key (playlist_id)
            references playlist (playlist_id);

alter table song
    add constraint AlbumID
        foreign key (album_id)
            references album (album_id);