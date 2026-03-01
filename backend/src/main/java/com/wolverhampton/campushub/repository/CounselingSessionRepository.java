package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.CounselingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CounselingSessionRepository extends JpaRepository<CounselingSession, Long> {
    List<CounselingSession> findByStudentIdOrderBySessionDateDesc(Long studentId);
    List<CounselingSession> findByCounselorIdOrderBySessionDateDesc(Long counselorId);
    List<CounselingSession> findByStatus(CounselingSession.SessionStatus status);
    List<CounselingSession> findAllByOrderBySessionDateDesc();
}
