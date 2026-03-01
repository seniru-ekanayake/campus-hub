package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.UserDTO;
import com.wolverhampton.campushub.repository.*;
import com.wolverhampton.campushub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired private UserRepository userRepository;
    @Autowired private ClubRepository clubRepository;
    @Autowired private EventRepository eventRepository;
    @Autowired private CheckInRepository checkInRepository;
    @Autowired private CounselingSessionRepository sessionRepository;
    @Autowired private AuthService authService;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(Map.of(
            "totalStudents", userRepository.count(),
            "totalClubs", clubRepository.count(),
            "totalEvents", eventRepository.count(),
            "totalCheckIns", checkInRepository.count(),
            "totalSessions", sessionRepository.count()
        ));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream()
                .map(authService::toDTO).collect(Collectors.toList()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted"));
    }
}
