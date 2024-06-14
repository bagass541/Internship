package com.bagas.controllers;

import com.bagas.dto.SubcategoryDTO;
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

import static com.bagas.constants.CommonTestConstants.TEST_CREATE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_DELETE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_UPDATE_SUBCATEGORY_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubcategoryControllerTest {

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
    void testCreateSubcategory() throws Exception {
        SubcategoryDTO subcategoryDTO = SubcategoryDTO.builder()
                .name("TEST")
                .parent(1L)
                .build();

        String json = objectMapper.writeValueAsString(subcategoryDTO);

        mockMvc.perform(post(TEST_CREATE_SUBCATEGORY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(jsonPath("$.name").value("TEST"))
                .andExpect(jsonPath("$.parent").value(1));
    }

    @Test
    @WithMockUser
    void testUpdateSubcategory() throws Exception {
        SubcategoryDTO subcategoryDTO = SubcategoryDTO.builder()
                .name("TEST")
                .parent(1L)
                .build();

        String jsonSource = objectMapper.writeValueAsString(subcategoryDTO);

        MvcResult mvcResult = mockMvc.perform(post(TEST_CREATE_SUBCATEGORY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSource))
                .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());

        String id = node.get("id").toString();

        SubcategoryDTO subcategoryTarget = SubcategoryDTO.builder()
                .name("TestUpdated")
                .parent(2L)
                .build();

        String jsonTarget = objectMapper.writeValueAsString(subcategoryTarget);

        mockMvc.perform(put(String.format(TEST_UPDATE_SUBCATEGORY_ENDPOINT, id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTarget))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("TestUpdated"))
                .andExpect(jsonPath("$.parent").value("2"));
    }

    @Test
    @WithMockUser
    void testDeleteSubcategory() throws Exception {
        mockMvc.perform(delete(String.format(TEST_DELETE_SUBCATEGORY_ENDPOINT, 1)))
                .andExpect(status().isOk());
    }
}
