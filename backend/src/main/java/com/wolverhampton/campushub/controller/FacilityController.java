package com.wolverhampton.campushub.controller;

import com.wolverhampton.campushub.dto.FacilityDTO;
import com.wolverhampton.campushub.entity.Facility;
import com.wolverhampton.campushub.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/facilities")
    public ResponseEntity<List<FacilityDTO>> getAll() {
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }

    @GetMapping("/facilities/{id}")
    public ResponseEntity<FacilityDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(facilityService.getFacility(id));
    }

    @PostMapping("/admin/facilities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody FacilityDTO dto) {
        try {
            return ResponseEntity.ok(facilityService.createFacility(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/admin/facilities/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FacilityDTO dto) {
        try {
            return ResponseEntity.ok(facilityService.updateFacility(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/admin/facilities/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok(Map.of("message", "Facility deleted"));
    }

    @PatchMapping("/admin/facilities/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestBody Map<String, String> body) {
        Facility.FacilityStatus status = Facility.FacilityStatus.valueOf(body.get("status").toUpperCase());
        return ResponseEntity.ok(facilityService.updateStatus(id, status));
    }
}
