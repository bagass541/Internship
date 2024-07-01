package com.bagas.controllers;

import com.bagas.dto.GroupDTO;
import com.bagas.entities.Group;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static com.bagas.constants.CommonTestConstants.TEST_CREATE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_DELETE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_UPDATE_GROUP_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(df);
    }

    @Test
    @WithMockUser
    void testCreateGroup() throws Exception {
        GroupDTO groupDTO = GroupDTO.builder()
                .name("Test")
                .build();

        String json = objectMapper.writeValueAsString(groupDTO);

        mockMvc.perform(post(TEST_CREATE_GROUP_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    @WithMockUser
    void testUpdateGroup() throws Exception {
        GroupDTO groupDTO = GroupDTO.builder()
                .name("Test")
                .build();

        String jsonSource = objectMapper.writeValueAsString(groupDTO);

        MvcResult mvcResult = mockMvc.perform(post(TEST_CREATE_GROUP_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSource))
                .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        String id = node.get("id").toString();

        GroupDTO groupTarget = GroupDTO.builder()
                .name("TestUpdated")
                .build();

        String jsonTarget = objectMapper.writeValueAsString(groupTarget);

        mockMvc.perform(put(String.format(TEST_UPDATE_GROUP_ENDPOINT, id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTarget))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("TestUpdated"));
    }

    @Test
    @WithMockUser
    void testDeleteGroup() throws Exception {
        mockMvc.perform(delete(String.format(TEST_DELETE_GROUP_ENDPOINT, 1)))
                .andExpect(status().isOk());
    }
}
