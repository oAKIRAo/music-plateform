package com.musicplatform.playlistservice.kafka;

import com.musicplatform.playlistservice.repository.PlaylistSongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SongEventConsumer {
    private final PlaylistSongRepository playlistSongRepository;
    @KafkaListener(topics = "song-deleted", groupId = "playlist-service-group")
    public void handleSongDeleted(String message) {
        Long songId = Long.parseLong(message);
        playlistSongRepository.deleteByIdSongId(songId);
    }
}
