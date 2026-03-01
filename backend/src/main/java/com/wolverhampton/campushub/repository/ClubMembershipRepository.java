package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {
    List<ClubMembership> findByUserId(Long userId);
    List<ClubMembership> findByClubId(Long clubId);
    Optional<ClubMembership> findByUserIdAndClubId(Long userId, Long clubId);
    boolean existsByUserIdAndClubId(Long userId, Long clubId);
    long countByClubId(Long clubId);
}
