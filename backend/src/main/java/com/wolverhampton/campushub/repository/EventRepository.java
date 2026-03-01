package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByEventDateAfterOrderByEventDateAsc(LocalDateTime date);
    List<Event> findByCampus(String campus);
}
