package com.andersenlab.roomba.controller;

import com.andersenlab.roomba.exception.RestResponseEntityExceptionHandler;
import com.andersenlab.roomba.model.request.CleanRoomRequest;
import com.andersenlab.roomba.model.request.HooverCoordsDto;
import com.andersenlab.roomba.service.CleanerFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static com.andersenlab.roomba.controller.TestDataFactory.getRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CleanerControllerTest {

    @InjectMocks
    private CleanerController controller;
    @InjectMocks
    private CleanerFacade cleanerFacade;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String PATH = "/api/v1/cleaner";

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(RestResponseEntityExceptionHandler.class)
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCoords")
    void givenInvalidCoords_whenCleanRoom_thenReturnBadRequest(HooverCoordsDto coords) throws Exception {
        CleanRoomRequest request = getRequest();
        request.setCoords(coords);

        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidCoords_whenCleanRoom_thenReturnOk() throws Exception {
        CleanRoomRequest request = getRequest();

        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    void givenInvalidRoomSize_whenCleanRoom_thenReturnBadRequest() throws Exception {
        CleanRoomRequest request = getRequest();
        request.getRoomSize().setLength(-1);

        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenInvalidInstructions_whenCleanRoom_thenReturnBadRequest() throws Exception {
        CleanRoomRequest request = getRequest();
        request.setInstructions("ABC");

        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    private static Stream<Arguments> provideInvalidCoords() {
        return Stream.of(
                Arguments.of(new HooverCoordsDto(-1, -1)),
                Arguments.of(new HooverCoordsDto(6, 6)),
                Arguments.of(new HooverCoordsDto(-1, 6)),
                Arguments.of(new HooverCoordsDto(6, -1))
        );
    }
}