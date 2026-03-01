package com.wolverhampton.campushub.service;

import com.wolverhampton.campushub.dto.AppDTO.*;
import com.wolverhampton.campushub.entity.Counselor;
import com.wolverhampton.campushub.entity.CounselingSession;
import com.wolverhampton.campushub.entity.User;
import com.wolverhampton.campushub.repository.CounselingSessionRepository;
import com.wolverhampton.campushub.repository.CounselorRepository;
import com.wolverhampton.campushub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounselingService {

    @Autowired private CounselorRepository counselorRepository;
    @Autowired private CounselingSessionRepository sessionRepository;
    @Autowired private UserRepository userRepository;

    // Counselor management
    public List<CounselorDTO> getActiveCounselors() {
        return counselorRepository.findByActive(true).stream().map(this::toCounselorDTO).collect(Collectors.toList());
    }

    public List<CounselorDTO> getAllCounselors() {
        return counselorRepository.findAll().stream().map(this::toCounselorDTO).collect(Collectors.toList());
    }

    public CounselorDTO createCounselor(CounselorDTO dto) {
        Counselor c = new Counselor();
        mapToCounselorEntity(dto, c);
        return toCounselorDTO(counselorRepository.save(c));
    }

    public CounselorDTO updateCounselor(Long id, CounselorDTO dto) {
        Counselor c = counselorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Counselor not found"));
        mapToCounselorEntity(dto, c);
        return toCounselorDTO(counselorRepository.save(c));
    }

    public void deleteCounselor(Long id) {
        counselorRepository.deleteById(id);
    }

    // Session management
    public CounselingSessionDTO bookSession(SessionBookingRequest request, String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Counselor counselor = counselorRepository.findById(request.getCounselorId())
                .orElseThrow(() -> new RuntimeException("Counselor not found"));

        CounselingSession session = new CounselingSession();
        session.setStudent(student);
        session.setCounselor(counselor);
        session.setSessionDate(request.getSessionDate());
        session.setIssueDescription(request.getIssueDescription());
        session.setStatus(CounselingSession.SessionStatus.PENDING);

        return toSessionDTO(sessionRepository.save(session));
    }

    public List<CounselingSessionDTO> getMySessionsForStudent(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return sessionRepository.findByStudentIdOrderBySessionDateDesc(student.getId())
                .stream().map(this::toSessionDTO).collect(Collectors.toList());
    }

    public List<CounselingSessionDTO> getAllSessions() {
        return sessionRepository.findAllByOrderBySessionDateDesc()
                .stream().map(this::toSessionDTO).collect(Collectors.toList());
    }

    public CounselingSessionDTO cancelSession(Long sessionId, String username) {
        CounselingSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // Verify ownership or admin
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        boolean isAdmin = user.getRoles().stream()
                .anyMatch(r -> r.getName().name().equals("ROLE_ADMIN"));

        if (!isAdmin && !session.getStudent().getId().equals(user.getId())) {
            throw new RuntimeException("Not authorized to cancel this session");
        }

        session.setStatus(CounselingSession.SessionStatus.CANCELLED);
        return toSessionDTO(sessionRepository.save(session));
    }

    public CounselingSessionDTO updateSessionStatus(Long sessionId, String status) {
        CounselingSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        session.setStatus(CounselingSession.SessionStatus.valueOf(status.toUpperCase()));
        return toSessionDTO(sessionRepository.save(session));
    }

    private void mapToCounselorEntity(CounselorDTO dto, Counselor c) {
        c.setName(dto.getName());
        c.setTitle(dto.getTitle());
        c.setSpecializations(dto.getSpecializations());
        c.setBio(dto.getBio());
        c.setProfilePicture(dto.getProfilePicture());
        c.setContactEmail(dto.getContactEmail());
        c.setAvailabilityNotes(dto.getAvailabilityNotes());
        c.setActive(dto.isActive());
    }

    private CounselorDTO toCounselorDTO(Counselor c) {
        CounselorDTO dto = new CounselorDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setTitle(c.getTitle());
        dto.setSpecializations(c.getSpecializations());
        dto.setBio(c.getBio());
        dto.setProfilePicture(c.getProfilePicture());
        dto.setContactEmail(c.getContactEmail());
        dto.setAvailabilityNotes(c.getAvailabilityNotes());
        dto.setActive(c.isActive());
        return dto;
    }

    private CounselingSessionDTO toSessionDTO(CounselingSession s) {
        CounselingSessionDTO dto = new CounselingSessionDTO();
        dto.setId(s.getId());
        dto.setCounselorId(s.getCounselor().getId());
        dto.setCounselorName(s.getCounselor().getName());
        dto.setCounselorTitle(s.getCounselor().getTitle());
        dto.setStudentId(s.getStudent().getId());
        dto.setStudentUsername(s.getStudent().getUsername());
        dto.setSessionDate(s.getSessionDate());
        dto.setIssueDescription(s.getIssueDescription());
        dto.setStatus(s.getStatus().name());
        dto.setCreatedAt(s.getCreatedAt());
        return dto;
    }
}
