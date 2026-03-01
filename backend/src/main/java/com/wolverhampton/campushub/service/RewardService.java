package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.RewardDTO;
import com.wolverhampton.campushub.entity.Reward;
import com.wolverhampton.campushub.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public List<RewardDTO> getActiveRewards() {
        return rewardRepository.findByActive(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<RewardDTO> getAllRewards() {
        return rewardRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RewardDTO create(RewardDTO dto) {
        Reward r = new Reward();
        mapToEntity(dto, r);
        return toDTO(rewardRepository.save(r));
    }

    public RewardDTO update(Long id, RewardDTO dto) {
        Reward r = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));
        mapToEntity(dto, r);
        return toDTO(rewardRepository.save(r));
    }

    public void delete(Long id) {
        rewardRepository.deleteById(id);
    }

    private void mapToEntity(RewardDTO dto, Reward r) {
        r.setName(dto.getName());
        r.setDescription(dto.getDescription());
        r.setPointsRequired(dto.getPointsRequired());
        r.setAvailableQuantity(dto.getAvailableQuantity());
        r.setActive(dto.isActive());
        r.setLocationTypeName(dto.getLocationTypeName());
        r.setPointValue(dto.getPointValue());
    }

    private RewardDTO toDTO(Reward r) {
        RewardDTO dto = new RewardDTO();
        dto.setId(r.getId());
        dto.setName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setPointsRequired(r.getPointsRequired());
        dto.setAvailableQuantity(r.getAvailableQuantity());
        dto.setActive(r.isActive());
        dto.setLocationTypeName(r.getLocationTypeName());
        dto.setPointValue(r.getPointValue());
        return dto;
    }
}
