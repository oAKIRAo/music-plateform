package com.musicplatform.playlistservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDetailsDTO {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private Integer duration;
}
