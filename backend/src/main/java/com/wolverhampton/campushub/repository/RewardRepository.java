package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByActive(boolean active);
}
