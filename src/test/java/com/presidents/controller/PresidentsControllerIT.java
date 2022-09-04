package com.presidents.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.presidents.model.dto.PresidentDto;
import com.presidents.util.TestUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;

import static com.presidents.util.TestUtils.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PresidentsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostRequestForPresidentSave_thanCorrectResponse() throws Exception {
        //given
        var president = PresidentDto.builder().name("TestPresident")
                .surname("TestPresident")
                .termFrom(Timestamp.valueOf("1789-03-04 00:00:00"))
                .termTo((Timestamp.valueOf("1793-03-04 00:00:00")))
                .politicalParty("TestParty")
                .build();
        //when
        mockMvc.perform(post("/presidents/save").content(toJson(president))
                .contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("TestPresident")))
                .andExpect(jsonPath("$.surname", Matchers.equalTo("TestPresident")));

    }

    @Test
    void whenPostPresidentAndIncorrectName_thanIncorrectResponse() throws Exception {
        //given
        var president = PresidentDto.builder().name(null)
                .surname("TestSurname")
                .politicalParty("TestyParty")
                .build();
        //when
        mockMvc.perform(post("/presidents/save").content(toJson(president))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder("Name is required")));

    }
}