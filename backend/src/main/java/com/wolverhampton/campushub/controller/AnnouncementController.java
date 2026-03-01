package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.AppDTO.AnnouncementDTO;
import com.wolverhampton.campushub.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    public ResponseEntity<List<AnnouncementDTO>> getAll() {
        return ResponseEntity.ok(announcementService.getAll());
    }

    @GetMapping("/announcements/category/{category}")
    public ResponseEntity<List<AnnouncementDTO>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(announcementService.getByCategory(category));
    }

    @PostMapping("/admin/announcements")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody AnnouncementDTO dto,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(announcementService.create(dto, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/announcements/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnnouncementDTO dto) {
        try {
            return ResponseEntity.ok(announcementService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/announcements/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        announcementService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Announcement deleted"));
    }
}
