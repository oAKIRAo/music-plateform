package com.musicplatform.playlistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistResponse {
    private Long id;
    private String name;
    private String imagePath;
    private Long userId;
    private LocalDateTime createdAt;
    private List<SongDetailsDTO> songs;
}
