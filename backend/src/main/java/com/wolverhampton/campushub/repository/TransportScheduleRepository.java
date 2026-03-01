package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.TransportSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransportScheduleRepository extends JpaRepository<TransportSchedule, Long> {
    List<TransportSchedule> findByFromCampusAndToCampus(String from, String to);
    List<TransportSchedule> findByActive(boolean active);
}
