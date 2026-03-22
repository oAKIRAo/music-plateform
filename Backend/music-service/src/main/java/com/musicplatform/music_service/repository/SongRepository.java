package com.musicplatform.music_service.repository;

import com.musicplatform.music_service.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
     List<Song> findSongsByTitle(String title);
}
