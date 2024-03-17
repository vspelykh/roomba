package com.andersenlab.roomba.controller;

import com.andersenlab.roomba.model.entity.CleaningResult;
import com.andersenlab.roomba.model.entity.CleaningResultRepository;
import com.andersenlab.roomba.model.request.CleanRoomRequest;
import com.andersenlab.roomba.model.request.HooverCoordsDto;
import com.andersenlab.roomba.service.CleanerFacade;
import com.andersenlab.roomba.service.CleaningResultService;
import com.andersenlab.roomba.service.HooverService;
import com.andersenlab.roomba.service.RoomService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Stream;

import static com.andersenlab.roomba.controller.TestDataFactory.getExpectedResult;
import static com.andersenlab.roomba.controller.TestDataFactory.getRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class IntegrationCleanerControllerTest extends AbstractIntegrationControllerTest {

    protected IntegrationCleanerControllerTest() {
        if (!postgreSQLContainer.isRunning()) {
            postgreSQLContainer.withDatabaseName("integration-tests-db")
                    .withUsername("username").withPassword("password");
            postgreSQLContainer.start();
        }
    }

    @AfterAll
    void destroy() {
        postgreSQLContainer.close();
    }

    @Autowired
    protected CleanerController controller;

    @Autowired
    protected CleanerFacade cleanerFacade;

    @Autowired
    protected HooverService service;

    @Autowired
    protected RoomService roomService;

    @Autowired
    protected CleaningResultService cleaningResultService;

    @Autowired
    protected CleaningResultRepository cleaningResultRepository;
    private static final String PATH = "/api/v1/cleaning";

    @Test
    void givenValidRequest_whenCleanRoom_saveResultToDatabaseAndReturnOk() throws Exception {
        CleanRoomRequest request = getRequest();

        String body = objectMapper.writeValueAsString(request);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(jsonPath("$.coords.x").value("3"))
                .andExpect(jsonPath("$.coords.y").value("0"))
                .andExpect(jsonPath("$.patches").value("1"))
                .andExpect(status().isOk());

        List<CleaningResult> resultList = cleaningResultRepository.findAll();
        assertEquals(1, resultList.size());
        assertEquals(getExpectedResult(), resultList.get(0));
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