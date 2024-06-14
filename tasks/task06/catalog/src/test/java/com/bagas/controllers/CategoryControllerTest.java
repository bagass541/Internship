package com.bagas.controllers;

import com.bagas.dto.CategoryDTO;
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

import static com.bagas.constants.CommonTestConstants.TEST_CREATE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_DELETE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_UPDATE_CATEGORY_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

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
    void testCreateCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("TEST")
                .parent(1L)
                .build();

        String json = objectMapper.writeValueAsString(categoryDTO);

        mockMvc.perform(post(TEST_CREATE_CATEGORY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.name").value("TEST"))
                .andExpect(jsonPath("$.parent").value(1));
    }

    @Test
    @WithMockUser
    void testUpdateCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("TEST")
                .parent(1L)
                .build();

        String jsonSource = objectMapper.writeValueAsString(categoryDTO);

        MvcResult mvcResult = mockMvc.perform(post(TEST_CREATE_CATEGORY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSource))
                .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());

        String id = node.get("id").toString();

        CategoryDTO categoryTarget = CategoryDTO.builder()
                .name("TestUpdate")
                .parent(1L)
                .build();

        String json = objectMapper.writeValueAsString(categoryTarget);

        mockMvc.perform(put(String.format(TEST_UPDATE_CATEGORY_ENDPOINT, id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("TestUpdate"));
    }

    @Test
    @WithMockUser
    void testDeleteCategory() throws Exception {
        mockMvc.perform(delete(String.format(TEST_DELETE_CATEGORY_ENDPOINT, 1)))
                .andExpect(status().isOk());
    }

}
