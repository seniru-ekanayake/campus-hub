package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByUserIdOrderByCheckInTimeDesc(Long userId);

    @Query("SELECT SUM(c.pointsAwarded) FROM CheckIn c WHERE c.user.id = :userId")
    Integer sumPointsByUserId(Long userId);
}
