package com.musicplatform.music_service.mapper;

import com.musicplatform.music_service.dto.SongDTO;
import com.musicplatform.music_service.entity.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {
    public SongDTO toSongDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        songDTO.setArtist(song.getArtist());
        songDTO.setAlbum(song.getAlbum());
        songDTO.setDuration(song.getDuration());
        songDTO.setCreatedAt(song.getCreatedAt());
        return songDTO;
    }
    public Song toSong(SongDTO songDTO) {
        Song song = new Song();
        song.setId(songDTO.getId());
        song.setTitle(songDTO.getTitle());
        song.setArtist(songDTO.getArtist());
        song.setAlbum(songDTO.getAlbum());
        song.setDuration(songDTO.getDuration());
        song.setCreatedAt(songDTO.getCreatedAt());
        return song;
    }
}
