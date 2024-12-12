package com.devops.backend.repository;

import com.devops.backend.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {
    // add custom queries here
}