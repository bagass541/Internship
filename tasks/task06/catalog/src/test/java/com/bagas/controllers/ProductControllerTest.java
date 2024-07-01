package com.bagas.controllers;

import com.bagas.dto.DescriptionDTO;
import com.bagas.dto.ProductDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import static com.bagas.constants.CommonTestConstants.TEST_CREATE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_DELETE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_PRODUCTS_BY_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonTestConstants.TEST_UPDATE_PRODUCT_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

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
    void testGetProductsBySubcategory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(String.format(TEST_PRODUCTS_BY_SUBCATEGORY_ENDPOINT, 4))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].parent").value("4"))
                .andReturn();

        String res = mvcResult.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(res);

        Assertions.assertEquals(jsonNode.size(), 1);
    }

    @Test
    @WithMockUser
    void testCreateProduct() throws Exception {
        DescriptionDTO descriptionDTO = DescriptionDTO.builder()
                .weight(100)
                .length(100)
                .width(100)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .name("Test")
                .price(BigDecimal.TEN)
                .deliveryTime(LocalDateTime.now())
                .descriptionDTO(descriptionDTO)
                .parent(1L)
                .imageUrl("test")
                .build();

        String json = objectMapper.writeValueAsString(productDTO);

        mockMvc.perform(post(TEST_CREATE_PRODUCT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("10"))
                .andExpect(jsonPath("$.descriptionDTO.length").value(100));
    }

    @Test
    @WithMockUser
    void testUpdateProduct() throws Exception {
        DescriptionDTO descriptionDTO = DescriptionDTO.builder()
                .weight(100)
                .length(100)
                .width(100)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .name("Test")
                .price(BigDecimal.TEN)
                .deliveryTime(LocalDateTime.now())
                .descriptionDTO(descriptionDTO)
                .parent(1L)
                .imageUrl("test")
                .build();

        String jsonSource = objectMapper.writeValueAsString(productDTO);

        MvcResult mvcResult = mockMvc.perform(post(TEST_CREATE_PRODUCT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSource))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());

        String id = node.get("id").toString();

        ProductDTO productTarget = ProductDTO.builder()
                .name("TestUpdate")
                .price(BigDecimal.TEN)
                .deliveryTime(LocalDateTime.now())
                .parent(1L)
                .imageUrl("TestUpdate")
                .build();

        String jsonTarget = objectMapper.writeValueAsString(productTarget);

        mockMvc.perform(put(String.format(TEST_UPDATE_PRODUCT_ENDPOINT, id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTarget))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("TestUpdate"))
                .andExpect(jsonPath("$.imageUrl").value("TestUpdate"));
    }

    @Test
    @WithMockUser
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete(String.format(TEST_DELETE_PRODUCT_ENDPOINT, 1)))
                .andExpect(status().isOk());
    }
}
