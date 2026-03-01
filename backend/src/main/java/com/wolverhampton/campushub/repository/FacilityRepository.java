package com.wolverhampton.campushub.repository;

import com.wolverhampton.campushub.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByCampus(String campus);
    List<Facility> findByStatus(Facility.FacilityStatus status);
}
