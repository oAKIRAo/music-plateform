package com.musicplatform.playlistservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_song")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistSong {
    @EmbeddedId
    private PlaylistSongId id;
}
