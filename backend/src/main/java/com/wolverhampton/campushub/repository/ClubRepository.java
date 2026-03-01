package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findByActive(boolean active);
    List<Club> findByCategory(String category);
}
