package com.example.audio.repository;

import com.example.audio.model.AudioTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AudioTrackRepository extends JpaRepository<AudioTrack, Long> {
    List<AudioTrack> findByTitleContainingIgnoreCase(String title);
}
