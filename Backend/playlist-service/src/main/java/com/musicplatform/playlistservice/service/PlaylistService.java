package com.musicplatform.playlistservice.service;

import com.musicplatform.playlistservice.dto.PlaylistRequest;
import com.musicplatform.playlistservice.dto.PlaylistResponse;
import com.musicplatform.playlistservice.dto.SongDetailsDTO;
import com.musicplatform.playlistservice.entity.Playlist;
import com.musicplatform.playlistservice.entity.PlaylistSong;
import com.musicplatform.playlistservice.entity.PlaylistSongId;
import com.musicplatform.playlistservice.exception.PlaylistNotFoundException;
import com.musicplatform.playlistservice.exception.SongNotFoundInPlaylistException;
import com.musicplatform.playlistservice.mapper.PlaylistMapper;
import com.musicplatform.playlistservice.repository.PlaylistRepository;
import com.musicplatform.playlistservice.repository.PlaylistSongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistMapper playlistMapper;
    @Value("${music-service.url}")
    private String musicServiceUrl;

    private final RestTemplate restTemplate;

   public PlaylistResponse createPlaylist(PlaylistRequest request) {
       Playlist playlist = playlistMapper.toEntity(request);
       Playlist savedPlaylist = playlistRepository.save(playlist);
       return playlistMapper.toDto(savedPlaylist);
   }

    public PlaylistResponse getPlaylistById(Long id) {

        // 1. Find the playlist in playlist_db
        // If not found → throw PlaylistNotFoundException
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        // 2. Find all rows in playlist_song table where playlist_id = id
        // Result: list of (playlistId, songId) pairs
        List<PlaylistSong> playlistSongs = playlistSongRepository.findByIdPlaylistId(id);
        // 3. For each (playlistId, songId) pair:
        //    → call music-service REST API to get song details
        //    → map the response to SongDetailsDTO
        // Result: list of song details from music-service
        List<SongDetailsDTO> songs = playlistSongs.stream()
                .map(ps -> restTemplate.getForObject(
                        musicServiceUrl + "/songs/" + ps.getId().getSongId(),
                        SongDetailsDTO.class))
                .toList();
        // 4. Convert playlist entity to DTO (name, userId, createdAt...)
        PlaylistResponse playlistResponse = playlistMapper.toDto(playlist);
        // 5. Attach the song details to the response
        playlistResponse.setSongs(songs);
        return playlistResponse;
    }

   public List<PlaylistResponse> getAllPlaylists() {
       return playlistRepository.findAll()
               .stream()
               .map(playlistMapper::toDto)
               .toList();
   }

   public PlaylistResponse updatePlaylist(Long id, PlaylistRequest request) {
       Playlist playlist = playlistRepository.findById(id)
               .orElseThrow(() -> new PlaylistNotFoundException(id));
       if(request.getName() != null) {
           playlist.setName(request.getName());
       }
       if(request.getImagePath() != null) {
           playlist.setImagePath(request.getImagePath());
       }
       Playlist updated = playlistRepository.save(playlist);
       return playlistMapper.toDto(updated);
   }

    public void deletePlaylistById(Long id) {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException(id));
        playlistRepository.delete(playlist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId){
        if(!playlistRepository.existsById(playlistId)) {
            throw new PlaylistNotFoundException(playlistId);}
        PlaylistSongId playlistSongId = PlaylistSongId.builder()
                .playlistId(playlistId)
                .songId(songId)
                .build();
        PlaylistSong playlistSong = PlaylistSong.builder()
                .id(playlistSongId)
                .build();
        playlistSongRepository.save(playlistSong);
    }

    public void removeSongFromplaylist(Long playlistId, Long songId) {
        PlaylistSongId playlistSongId = PlaylistSongId.builder()
                .playlistId(playlistId)
                .songId(songId)
                .build();
        if(!playlistSongRepository.existsById(playlistSongId)) {
            throw new SongNotFoundInPlaylistException(playlistId, songId);
        }
        playlistSongRepository.deleteById(playlistSongId);
    }

}
