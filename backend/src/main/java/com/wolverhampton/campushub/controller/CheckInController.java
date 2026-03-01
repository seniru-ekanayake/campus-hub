package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.AppDTO.*;
import com.wolverhampton.campushub.service.CheckInService;
import com.wolverhampton.campushub.service.RewardService;
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
public class CheckInController {

    @Autowired private CheckInService checkInService;
    @Autowired private RewardService rewardService;

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@RequestBody CheckInRequest request,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(checkInService.checkIn(request, userDetails.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/checkin/my")
    public ResponseEntity<List<CheckInDTO>> getMyCheckIns(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(checkInService.getMyCheckIns(userDetails.getUsername()));
    }

    @GetMapping("/rewards")
    public ResponseEntity<List<RewardDTO>> getRewards() {
        return ResponseEntity.ok(rewardService.getActiveRewards());
    }

    @GetMapping("/admin/checkins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CheckInDTO>> getAllCheckIns() {
        return ResponseEntity.ok(checkInService.getAllCheckIns());
    }

    @GetMapping("/admin/rewards")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RewardDTO>> getAllRewards() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    @PostMapping("/admin/rewards")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createReward(@RequestBody RewardDTO dto) {
        try {
            return ResponseEntity.ok(rewardService.create(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/rewards/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateReward(@PathVariable Long id, @RequestBody RewardDTO dto) {
        try {
            return ResponseEntity.ok(rewardService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/rewards/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteReward(@PathVariable Long id) {
        rewardService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Reward deleted"));
    }
}
