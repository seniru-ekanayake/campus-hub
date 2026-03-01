package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.AppDTO.TransportDTO;
import com.wolverhampton.campushub.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransportController {

    @Autowired
    private TransportService transportService;

    @GetMapping("/transport")
    public ResponseEntity<List<TransportDTO>> getActive() {
        return ResponseEntity.ok(transportService.getAll());
    }

    @GetMapping("/transport/route")
    public ResponseEntity<List<TransportDTO>> getByRoute(@RequestParam String from,
                                                          @RequestParam String to) {
        return ResponseEntity.ok(transportService.getByRoute(from, to));
    }

    @GetMapping("/admin/transport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransportDTO>> getAllAdmin() {
        return ResponseEntity.ok(transportService.getAllAdmin());
    }

    @PostMapping("/admin/transport")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody TransportDTO dto) {
        try {
            return ResponseEntity.ok(transportService.create(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/transport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TransportDTO dto) {
        try {
            return ResponseEntity.ok(transportService.update(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/transport/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        transportService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Schedule deleted"));
    }
}
