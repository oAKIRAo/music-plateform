package com.musicplatform.playlistservice.repository;

import com.musicplatform.playlistservice.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Playlist getPlaylistById(Long id);

    List<Playlist> id(Long id);
}
