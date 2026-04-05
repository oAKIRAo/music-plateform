package com.musicplatform.playlistservice.controller;

import com.musicplatform.playlistservice.dto.PlaylistRequest;
import com.musicplatform.playlistservice.dto.PlaylistResponse;
import com.musicplatform.playlistservice.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping("/add")
    public ResponseEntity<PlaylistResponse> addPlaylist(@RequestBody PlaylistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.createPlaylist(request));
    }

    @GetMapping
    public ResponseEntity<List<PlaylistResponse>> getPlaylists() {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getAllPlaylists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistResponse> getPlaylistByid(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.getPlaylistById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PlaylistResponse> updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.updatePlaylist(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylistById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistId}/song/{songId}")
    public ResponseEntity<Void> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{playlistId}/removeSong/{songId}")
    public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.removeSongFromplaylist(playlistId, songId);
        return ResponseEntity.noContent().build();
    }
}
