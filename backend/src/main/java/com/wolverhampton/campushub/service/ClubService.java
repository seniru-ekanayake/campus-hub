package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.ClubDTO;
import com.wolverhampton.campushub.entity.Club;
import com.wolverhampton.campushub.entity.ClubMembership;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.ClubMembershipRepository;
import com.wolverhampton.campushub.repository.ClubRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {

    @Autowired private ClubRepository clubRepository;
    @Autowired private ClubMembershipRepository membershipRepository;
    @Autowired private UserRepository userRepository;

    public List<ClubDTO> getActiveClubs(String username) {
        return clubRepository.findByActive(true)
                .stream().map(c -> toDTO(c, username)).collect(Collectors.toList());
    }

    public List<ClubDTO> getAllClubs() {
        return clubRepository.findAll()
                .stream().map(c -> toDTO(c, null)).collect(Collectors.toList());
    }

    public ClubDTO getClub(Long id, String username) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));
        return toDTO(club, username);
    }

    public ClubDTO createClub(ClubDTO dto) {
        Club club = new Club();
        mapToEntity(dto, club);
        return toDTO(clubRepository.save(club), null);
    }

    public ClubDTO updateClub(Long id, ClubDTO dto) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found"));
        mapToEntity(dto, club);
        return toDTO(clubRepository.save(club), null);
    }

    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    public ClubDTO joinClub(Long clubId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        if (membershipRepository.existsByUserIdAndClubId(user.getId(), clubId)) {
            throw new RuntimeException("Already a member of this club");
        }

        ClubMembership membership = new ClubMembership();
        membership.setUser(user);
        membership.setClub(club);
        membershipRepository.save(membership);
        return toDTO(club, username);
    }

    public ClubDTO leaveClub(Long clubId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ClubMembership membership = membershipRepository.findByUserIdAndClubId(user.getId(), clubId)
                .orElseThrow(() -> new RuntimeException("Not a member of this club"));
        membershipRepository.delete(membership);
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));
        return toDTO(club, username);
    }

    public List<ClubDTO> getMyClubs(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return membershipRepository.findByUserId(user.getId())
                .stream().map(m -> toDTO(m.getClub(), username)).collect(Collectors.toList());
    }

    private void mapToEntity(ClubDTO dto, Club club) {
        club.setName(dto.getName());
        club.setDescription(dto.getDescription());
        club.setCategory(dto.getCategory());
        club.setMeetingSchedule(dto.getMeetingSchedule());
        club.setLocation(dto.getLocation());
        club.setImageUrl(dto.getImageUrl());
        club.setContactEmail(dto.getContactEmail());
        club.setActive(dto.isActive());
    }

    private ClubDTO toDTO(Club club, String username) {
        ClubDTO dto = new ClubDTO();
        dto.setId(club.getId());
        dto.setName(club.getName());
        dto.setDescription(club.getDescription());
        dto.setCategory(club.getCategory());
        dto.setMeetingSchedule(club.getMeetingSchedule());
        dto.setLocation(club.getLocation());
        dto.setImageUrl(club.getImageUrl());
        dto.setContactEmail(club.getContactEmail());
        dto.setActive(club.isActive());
        dto.setMemberCount(membershipRepository.countByClubId(club.getId()));
        dto.setCreatedAt(club.getCreatedAt());

        if (username != null) {
            userRepository.findByUsername(username).ifPresent(user ->
                dto.setMember(membershipRepository.existsByUserIdAndClubId(user.getId(), club.getId()))
            );
        }
        return dto;
    }
}
