package com.andersenlab.roomba.service;

import com.andersenlab.roomba.controller.request.CleanRoomRequest;
import com.andersenlab.roomba.controller.response.CleanRoomResponse;

public interface CleanerService {

    CleanRoomResponse cleanRoom(CleanRoomRequest cleanRoomRequest);

}
