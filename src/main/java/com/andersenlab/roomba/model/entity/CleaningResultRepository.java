package com.andersenlab.roomba.model.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleaningResultRepository extends JpaRepository<CleaningResult, Integer> {
}