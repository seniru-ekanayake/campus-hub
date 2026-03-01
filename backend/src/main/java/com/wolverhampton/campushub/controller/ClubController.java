package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.AppDTO.ClubDTO;
import com.wolverhampton.campushub.service.ClubService;
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
public class ClubController {

    @Autowired
    private ClubService clubService;

    @GetMapping("/clubs")
    public ResponseEntity<List<ClubDTO>> getActive(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails != null ? userDetails.getUsername() : null;
        return ResponseEntity.ok(clubService.getActiveClubs(username));
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<ClubDTO> getOne(@PathVariable Long id,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails != null ? userDetails.getUsername() : null;
        return ResponseEntity.ok(clubService.getClub(id, username));
    }

    @GetMapping("/clubs/my")
    public ResponseEntity<List<ClubDTO>> getMyClubs(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(clubService.getMyClubs(userDetails.getUsername()));
    }

    @PostMapping("/clubs/{id}/join")
    public ResponseEntity<?> join(@PathVariable Long id,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(clubService.joinClub(id, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/clubs/{id}/leave")
    public ResponseEntity<?> leave(@PathVariable Long id,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(clubService.leaveClub(id, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/admin/clubs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClubDTO>> getAllAdmin() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PostMapping("/admin/clubs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ClubDTO dto) {
        try {
            return ResponseEntity.ok(clubService.createClub(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/clubs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClubDTO dto) {
        try {
            return ResponseEntity.ok(clubService.updateClub(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/clubs/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok(Map.of("message", "Club deleted"));
    }
}
