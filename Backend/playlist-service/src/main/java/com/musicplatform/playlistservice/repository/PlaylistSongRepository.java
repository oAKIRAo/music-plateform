package com.musicplatform.playlistservice.repository;

import com.musicplatform.playlistservice.entity.PlaylistSong;
import com.musicplatform.playlistservice.entity.PlaylistSongId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, PlaylistSongId> {
}
