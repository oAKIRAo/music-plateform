package com.musicplatform.music_service.controller;

import com.musicplatform.music_service.dto.SongDTO;
import com.musicplatform.music_service.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    @GetMapping("")
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        return ResponseEntity.ok(songService.getAllSongs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long id) {
        return ResponseEntity.ok(songService.getSongById(id));
    }

    @PostMapping("/upload")
    public ResponseEntity<SongDTO> uploadSong(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("artist") String artist,
            @RequestParam("album") String album,
            @RequestParam(value = "duration", required = false) Integer duration) {

        SongDTO songDTO = SongDTO.builder()
                .title(title)
                .artist(artist)
                .album(album)
                .duration(duration)
                .build();

        return ResponseEntity.status(201).body(songService.uploadSong(file, songDTO));
    }

    @GetMapping("/stream/{id}")
    public ResponseEntity<Resource> streamSong(@PathVariable Long id) {
        Resource resource = songService.streamSong(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(resource);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return ResponseEntity.noContent().build();
    }

}
