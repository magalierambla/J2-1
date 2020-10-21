/*
package com.api.crowdlending.controller;

import com.api.crowdlending.model.user;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersHttpTest {
    @LocalServerPort
    private Integer port;
    private static RestTemplate restTemplate = null;
    private String baseUrl = "http://localhost";

    @BeforeAll
    static void init() {
        restTemplate = new RestTemplate();
    }
    @BeforeEach
    void setUp(){
        //exemple tout bete http://localhost:7097/users
        baseUrl = baseUrl.concat(":").concat(port.toString()).concat("/users");
    }

    @Test
    void shouldReturnAListOfUsers(){
        Map users = restTemplate.getForObject(baseUrl, Map.class);
        Map usersMap = (Map) users.get("_embedded");
        List<user> users3 = (List) usersMap.get("users");
        Assertions.assertAll(
                () -> assertNotNull(users3),
                () -> assertTrue(users3.size() == 2)
        );
    }
}


















*/
