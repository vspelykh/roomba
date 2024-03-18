package com.andersenlab.roomba.repository;

import com.andersenlab.roomba.model.entity.CleaningResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CleaningResultRepository extends JpaRepository<CleaningResult, Integer> {
}