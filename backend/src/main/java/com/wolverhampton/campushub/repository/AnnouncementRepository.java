package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByOrderByPinnedDescCreatedAtDesc();
    List<Announcement> findByCategory(String category);
}
