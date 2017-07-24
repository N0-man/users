package com.tradeaway.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeaway.users.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by noumanm on 7/15/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:application.properties"})

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") })

public class UserControllerTest {
    RestTemplate restTemplate = new RestTemplate();

    private JacksonTester<User> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
    }

    @Autowired
    private UserController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void Should_SearchUserDetails_When_ValidLastNameIsGiven () throws IOException {
        String expected = "{\"id\":\"9999\",\"fname\":\"TestMango\",\"lname\":\"TestLastMango\",\"type\":\"Buyer\",\"userId\":\"Lucky123\"}";

        String endpointURL = "http://localhost:8080/findUser/TestLastMango";

        ResponseEntity<User[]> responseEntity = restTemplate
                .getForEntity(endpointURL,
                        User[].class);
        final User user = Arrays.asList(responseEntity.getBody()).get(0);
        assertThat(user).isEqualTo(this.json.parse(expected).getObject());
    }

    @Test
    public void Should_ReturnTrueWhenUserNameIsAvailableToUse () throws IOException {
        String endpointURL = "http://localhost:8080/isUserIDAvailable/PAPA1234";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .getForEntity(endpointURL,
                        Boolean.class);
        final Boolean isUserIDAvailable = responseEntity.getBody();
        assertThat(isUserIDAvailable).isEqualTo(new Boolean(true));
    }

    @Test
    public void Should_ReturnFalseWhenUserNameIsUnAvailableToUse () throws IOException {
        String endpointURL = "http://localhost:8080/isUserIDAvailable/Lucky123";

        ResponseEntity<Boolean> responseEntity = restTemplate
                .getForEntity(endpointURL,
                        Boolean.class);
        final Boolean isUserIDAvailable = responseEntity.getBody();
        assertThat(isUserIDAvailable).isEqualTo(new Boolean(false));
    }

    @Test
    public void Should_CreateUserDetails_When_ValidUserIsGiven () throws IOException {
        // create payload
        User body = new User("TryFristName", "LuckyLastName", "Seller", "Lucky123");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(body, headers);

        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity("http://localhost:8080/createUser",
                        body,
                        User.class);
        final User response = responseEntity.getBody();
        assertThat(response).isNotNull();
    }
}
