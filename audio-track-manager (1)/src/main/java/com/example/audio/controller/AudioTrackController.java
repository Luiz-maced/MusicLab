package com.example.audio.controller;

import com.example.audio.model.AudioTrack;
import com.example.audio.repository.AudioTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AudioTrackController {

    private final AudioTrackRepository repository;

    @GetMapping("/")
    public String index(@RequestParam(value = "title", required = false) String title, Model model) {
        List<AudioTrack> tracks = (title == null || title.isEmpty()) ?
                repository.findAll() : repository.findByTitleContainingIgnoreCase(title);
        model.addAttribute("tracks", tracks);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadAudio(@RequestParam("title") String title,
                               @RequestParam("file") MultipartFile file) throws IOException {
        String fileType = file.getContentType();
        if (!fileType.equals("audio/mp3") && !fileType.equals("audio/wav") && !fileType.equals("audio/ogg")) {
            throw new IllegalArgumentException("Tipo de arquivo inválido: " + fileType);
        }
        AudioTrack track = new AudioTrack();
        track.setTitle(title);
        track.setFileName(file.getOriginalFilename());
        track.setFileType(fileType);
        track.setData(file.getBytes());
        repository.save(track);
        return "redirect:/";
    }

    @GetMapping("/audio/{id}")
    @ResponseBody
    public byte[] getAudio(@PathVariable Long id) {
        return repository.findById(id).orElseThrow().getData();
    }
}
