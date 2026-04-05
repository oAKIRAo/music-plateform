package com.musicplatform.playlistservice.mapper;

public interface BaseMapper<REQ, RES, E> {
    RES toDto(E entity);
    E toEntity(REQ request);
}
