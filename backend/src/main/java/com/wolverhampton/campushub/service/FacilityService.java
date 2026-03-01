package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.FacilityDTO;
import com.wolverhampton.campushub.entity.Facility;
import com.wolverhampton.campushub.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<FacilityDTO> getAllFacilities() {
        return facilityRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FacilityDTO getFacility(Long id) {
        return toDTO(facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found")));
    }

    public FacilityDTO createFacility(FacilityDTO dto) {
        Facility f = new Facility();
        mapToEntity(dto, f);
        return toDTO(facilityRepository.save(f));
    }

    public FacilityDTO updateFacility(Long id, FacilityDTO dto) {
        Facility f = facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        mapToEntity(dto, f);
        return toDTO(facilityRepository.save(f));
    }

    public void deleteFacility(Long id) {
        facilityRepository.deleteById(id);
    }

    public FacilityDTO updateStatus(Long id, Facility.FacilityStatus status) {
        Facility f = facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        f.setStatus(status);
        return toDTO(facilityRepository.save(f));
    }

    private void mapToEntity(FacilityDTO dto, Facility f) {
        f.setName(dto.getName());
        f.setDescription(dto.getDescription());
        f.setLocation(dto.getLocation());
        f.setCampus(dto.getCampus());
        f.setOpeningTimes(dto.getOpeningTimes());
        f.setImageUrl(dto.getImageUrl());
        if (dto.getStatus() != null) f.setStatus(dto.getStatus());
    }

    private FacilityDTO toDTO(Facility f) {
        FacilityDTO dto = new FacilityDTO();
        dto.setId(f.getId());
        dto.setName(f.getName());
        dto.setDescription(f.getDescription());
        dto.setLocation(f.getLocation());
        dto.setCampus(f.getCampus());
        dto.setOpeningTimes(f.getOpeningTimes());
        dto.setImageUrl(f.getImageUrl());
        dto.setStatus(f.getStatus());
        dto.setCreatedAt(f.getCreatedAt());
        return dto;
    }
}
