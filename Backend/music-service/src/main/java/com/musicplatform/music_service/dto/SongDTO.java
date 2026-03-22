package com.musicplatform.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {

     private Long id;
     private String title;
     private String artist;
     private String album;
     private Integer duration;
     private LocalDateTime createdAt;

}
