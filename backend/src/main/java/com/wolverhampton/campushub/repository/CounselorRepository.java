package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CounselorRepository extends JpaRepository<Counselor, Long> {
    List<Counselor> findByActive(boolean active);
}
