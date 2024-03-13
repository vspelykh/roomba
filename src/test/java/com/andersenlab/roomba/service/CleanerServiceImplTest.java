package com.andersenlab.roomba.service;

import com.andersenlab.roomba.controller.response.CleanRoomResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CleanerServiceImplTest {

    CleanerServiceImpl cleanerService = new CleanerServiceImpl();

    @Test
    void test() {
        CleanRoomResponse cleanRoomResponse = cleanerService.cleanRoom(TestDataFactory.getRequest());
    }
}