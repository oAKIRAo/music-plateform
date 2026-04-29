package com.musicplatform.music_service.service;

import com.musicplatform.music_service.dto.SongDTO;
import com.musicplatform.music_service.entity.Song;
import com.musicplatform.music_service.exception.FailedToUploadFileException;
import com.musicplatform.music_service.exception.SongFileNotFoundException;
import com.musicplatform.music_service.exception.SongNotFoundException;
import com.musicplatform.music_service.mapper.SongMapper;
import com.musicplatform.music_service.repository.SongRepository;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${file.upload.dir}")
    private String uploadDir;

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll()
                .stream()
                .map(songMapper::toSongDTO)
                .toList();
    }

    public SongDTO getSongById(Long id) {
       return songRepository.findById(id)
               .map(songMapper::toSongDTO)
               .orElseThrow(() -> new SongNotFoundException(id));

    }

    public SongDTO uploadSong(MultipartFile file) {
        try {
            // 1. Generate unique filename
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            // 2. Build full path
            Path uploadPath = Paths.get(uploadDir);
            // 3. Create folder if not exists
            Files.createDirectories(uploadPath);
            // 4. Save file to disk
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            // 5. Extract duration automatically
            AudioFile audioFile = AudioFileIO.read(filePath.toFile());
            int duration = audioFile.getAudioHeader().getTrackLength();

            Tag tag = audioFile.getTag();
            String title = (tag != null && !tag.getFirst(FieldKey.TITLE).isEmpty())
                    ? tag.getFirst(FieldKey.TITLE)
                    : file.getOriginalFilename();
            String album = (tag != null) ? tag.getFirst(FieldKey.ALBUM) : null;
            String artist = (tag != null) ? tag.getFirst(FieldKey.ARTIST) : null;
            // 6. Set filePath + duration + title + album + artist in entity
            Song song = new Song();
            song.setFilePath(filePath.toString());
            song.setDuration(duration);
            song.setTitle(title);
            song.setAlbum(album);
            song.setArtist(artist);
            // 7. Save metadata to DB
            Song saved = songRepository.save(song);
            // 8. Return DTO
            return songMapper.toSongDTO(saved);

        } catch (Exception e) {
            throw new FailedToUploadFileException(e.getMessage());
        }
    }

    public Resource streamSong(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        try {
            Path path = Paths.get(song.getFilePath());
            Resource resource = new UrlResource(path.toUri());
            if(!resource.exists()) {
                throw new SongFileNotFoundException(song.getFilePath());
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new SongFileNotFoundException(song.getFilePath());
        }
    }

    public void deleteSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        songRepository.delete(song);
        kafkaTemplate.send("song-deleted", id.toString());
    }
}
