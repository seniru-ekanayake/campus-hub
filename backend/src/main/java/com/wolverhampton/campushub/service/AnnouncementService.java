package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.AnnouncementDTO;
import com.wolverhampton.campushub.entity.Announcement;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.AnnouncementRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired private AnnouncementRepository announcementRepository;
    @Autowired private UserRepository userRepository;

    public List<AnnouncementDTO> getAll() {
        return announcementRepository.findAllByOrderByPinnedDescCreatedAtDesc()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<AnnouncementDTO> getByCategory(String category) {
        return announcementRepository.findByCategory(category)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AnnouncementDTO create(AnnouncementDTO dto, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Announcement a = new Announcement();
        a.setTitle(dto.getTitle());
        a.setContent(dto.getContent());
        a.setCategory(dto.getCategory());
        a.setPinned(dto.isPinned());
        a.setAuthor(author);
        return toDTO(announcementRepository.save(a));
    }

    public AnnouncementDTO update(Long id, AnnouncementDTO dto) {
        Announcement a = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
        a.setTitle(dto.getTitle());
        a.setContent(dto.getContent());
        a.setCategory(dto.getCategory());
        a.setPinned(dto.isPinned());
        return toDTO(announcementRepository.save(a));
    }

    public void delete(Long id) {
        announcementRepository.deleteById(id);
    }

    private AnnouncementDTO toDTO(Announcement a) {
        AnnouncementDTO dto = new AnnouncementDTO();
        dto.setId(a.getId());
        dto.setTitle(a.getTitle());
        dto.setContent(a.getContent());
        dto.setCategory(a.getCategory());
        dto.setPinned(a.isPinned());
        dto.setCreatedAt(a.getCreatedAt());
        if (a.getAuthor() != null) dto.setAuthorUsername(a.getAuthor().getUsername());
        return dto;
    }
}
