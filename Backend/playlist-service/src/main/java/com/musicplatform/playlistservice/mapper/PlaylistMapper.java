package com.musicplatform.playlistservice.mapper;

import com.musicplatform.playlistservice.dto.PlaylistRequest;
import com.musicplatform.playlistservice.dto.PlaylistResponse;
import com.musicplatform.playlistservice.entity.Playlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMapper extends BaseMapper<PlaylistRequest, PlaylistResponse, Playlist> {

}
