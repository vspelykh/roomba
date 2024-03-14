package com.andersenlab.roomba.controller;

import com.andersenlab.roomba.model.request.CleanRoomRequest;
import com.andersenlab.roomba.model.response.CleanRoomResponse;
import com.andersenlab.roomba.service.CleanerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CleanerController {

    private final CleanerService cleanerService;

    @PostMapping("/cleaning")
    public ResponseEntity<CleanRoomResponse> cleanRoom(@Valid @RequestBody CleanRoomRequest cleanRoomRequest) {
        CleanRoomResponse cleanRoomResponse = cleanerService.cleanRoom(cleanRoomRequest);
        return ResponseEntity.ok(cleanRoomResponse);
    }
}
