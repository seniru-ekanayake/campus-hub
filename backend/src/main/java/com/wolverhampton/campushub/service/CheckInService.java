package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.*;
import com.wolverhampton.campushub.entity.CheckIn;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.CheckInRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CheckInService {

    @Autowired private CheckInRepository checkInRepository;
    @Autowired private UserRepository userRepository;

    // Default points per location type
    private final Map<String, Integer> pointValues = Map.of(
        "LIBRARY", 10,
        "CLUB_MEETING", 15,
        "EVENT_VENUE", 20,
        "STUDY_SPACE", 5
    );

    @Transactional
    public CheckInDTO checkIn(CheckInRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CheckIn checkIn = new CheckIn();
        checkIn.setUser(user);
        checkIn.setLocationName(request.getLocationName());

        CheckIn.LocationType locType;
        try {
            locType = CheckIn.LocationType.valueOf(request.getLocationType().toUpperCase());
        } catch (IllegalArgumentException e) {
            locType = CheckIn.LocationType.STUDY_SPACE;
        }
        checkIn.setLocationType(locType);

        int points = pointValues.getOrDefault(request.getLocationType().toUpperCase(), 5);
        checkIn.setPointsAwarded(points);

        // Update user's total points
        user.setEngagementPoints(user.getEngagementPoints() + points);
        userRepository.save(user);

        CheckIn saved = checkInRepository.save(checkIn);
        return toDTO(saved);
    }

    public List<CheckInDTO> getMyCheckIns(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return checkInRepository.findByUserIdOrderByCheckInTimeDesc(user.getId())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CheckInDTO> getAllCheckIns() {
        return checkInRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    private CheckInDTO toDTO(CheckIn c) {
        CheckInDTO dto = new CheckInDTO();
        dto.setId(c.getId());
        dto.setLocationName(c.getLocationName());
        dto.setLocationType(c.getLocationType() != null ? c.getLocationType().name() : "");
        dto.setPointsAwarded(c.getPointsAwarded());
        dto.setCheckInTime(c.getCheckInTime());
        dto.setUsername(c.getUser().getUsername());
        return dto;
    }
}
