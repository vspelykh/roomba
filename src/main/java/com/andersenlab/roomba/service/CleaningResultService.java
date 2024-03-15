package com.andersenlab.roomba.service;

import com.andersenlab.roomba.model.entity.CleaningResult;
import com.andersenlab.roomba.model.entity.CleaningResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CleaningResultService {

    private final CleaningResultRepository cleaningResultRepository;

    public CleaningResult save(CleaningResult cleaningResult) {
        return cleaningResultRepository.save(cleaningResult);
    }
}
