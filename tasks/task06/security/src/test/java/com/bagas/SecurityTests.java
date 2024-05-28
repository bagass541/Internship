package com.bagas;

import com.bagas.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.bagas.constants.CommonConstants.JWT_LOGIN_ENDPOINT;
import static com.bagas.constants.CommonConstants.SECURED_ENDPOINT;
import static com.bagas.constants.TestConstants.BEARER_BEGGINING;
import static com.bagas.constants.TestConstants.HELLO_SECURED_MESSAGE;
import static com.bagas.constants.TestConstants.JSON_PATH_TO_ACCESS_TOKEN;
import static com.bagas.constants.TestConstants.LOGIN_ENDPOINT;
import static com.bagas.constants.TestConstants.OTHER_REMOTE_ADDR;
import static com.bagas.constants.TestConstants.USER_LOGIN;
import static com.bagas.constants.TestConstants.USER_PASSWORD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    private static User user;

    private static ObjectWriter ow;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @BeforeAll
    static void setUp() {
        user = new User();
        user.setUsername(USER_LOGIN);
        user.setPassword(USER_PASSWORD);

        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    void testJwtLogin() throws Exception {
        mockMvc.perform(post(JWT_LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(status().isOk()).andReturn();

        Thread.sleep(800);
    }

    @Test
    void testAccessToSource() throws Exception {
        MvcResult mvcResultOfAuth = mockMvc.perform(post(JWT_LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(status().isOk()).andReturn();

        String token = JsonPath.read(mvcResultOfAuth.getResponse().getContentAsString(), JSON_PATH_TO_ACCESS_TOKEN);

        MvcResult mvcResultOfSecured = mockMvc.perform(get(SECURED_ENDPOINT)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_BEGGINING + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResultOfSecured.getResponse().getContentAsString();
        Assertions.assertEquals(content, HELLO_SECURED_MESSAGE);

        Thread.sleep(800);
    }

    @Test
    void testLogoutIfIpChanged() throws Exception {
        MvcResult mvcResultOfAuth = mockMvc.perform(post(JWT_LOGIN_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(status().isOk()).andReturn();

        String token = JsonPath.read(mvcResultOfAuth.getResponse().getContentAsString(), JSON_PATH_TO_ACCESS_TOKEN);

        mockMvc.perform(get(SECURED_ENDPOINT)
                        .header(HttpHeaders.AUTHORIZATION, BEARER_BEGGINING + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(status().isOk());

        mockMvc.perform(get(SECURED_ENDPOINT)
                        .with(request -> {
                            request.setRemoteAddr(OTHER_REMOTE_ADDR);
                            return request;
                        })
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(user)))
                .andExpect(redirectedUrl(LOGIN_ENDPOINT));

        Thread.sleep(800);
    }
}
