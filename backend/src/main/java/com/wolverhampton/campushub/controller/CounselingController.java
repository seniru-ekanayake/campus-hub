package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.AppDTO.*;
import com.wolverhampton.campushub.service.CounselingService;
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
public class CounselingController {

    @Autowired
    private CounselingService counselingService;

    // Public-facing (authenticated): list counselors
    @GetMapping("/counseling/counselors")
    public ResponseEntity<List<CounselorDTO>> getCounselors() {
        return ResponseEntity.ok(counselingService.getActiveCounselors());
    }

    // Book session
    @PostMapping("/counseling/sessions")
    public ResponseEntity<?> bookSession(@RequestBody SessionBookingRequest request,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(counselingService.bookSession(request, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // View own sessions
    @GetMapping("/counseling/sessions/my")
    public ResponseEntity<List<CounselingSessionDTO>> getMySessions(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(counselingService.getMySessionsForStudent(userDetails.getUsername()));
    }

    // Cancel own session
    @PatchMapping("/counseling/sessions/{id}/cancel")
    public ResponseEntity<?> cancelSession(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(counselingService.cancelSession(id, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ============ Admin endpoints ============

    @GetMapping("/admin/counseling/counselors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CounselorDTO>> getAllCounselors() {
        return ResponseEntity.ok(counselingService.getAllCounselors());
    }

    @PostMapping("/admin/counseling/counselors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCounselor(@RequestBody CounselorDTO dto) {
        try {
            return ResponseEntity.ok(counselingService.createCounselor(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/counseling/counselors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCounselor(@PathVariable Long id, @RequestBody CounselorDTO dto) {
        try {
            return ResponseEntity.ok(counselingService.updateCounselor(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/counseling/counselors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCounselor(@PathVariable Long id) {
        counselingService.deleteCounselor(id);
        return ResponseEntity.ok(Map.of("message", "Counselor removed"));
    }

    @GetMapping("/admin/counseling/sessions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CounselingSessionDTO>> getAllSessions() {
        return ResponseEntity.ok(counselingService.getAllSessions());
    }

    @PatchMapping("/admin/counseling/sessions/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(counselingService.updateSessionStatus(id, body.get("status")));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
