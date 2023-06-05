package com.shary.coreapi.controller;

import com.shary.coreapi.config.init.SettingsIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(2)
@Disabled
public class CategoryControllerTest extends SettingsIntegrationTest {

    @Test
    @SneakyThrows
    @Order(1)
    void create_when_item_name_is_valid() {
        mvc
                .perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"test name\"}")
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$", hasKey("name")))
                .andExpect(jsonPath("$.name").value("test name"))
                .andExpect(status().isOk()
                );
    }

    @Test
    @SneakyThrows
    void create_when_id_less_than_one() {
        mvc
                .perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                        "id": "0",
                                        "name": "test name"
                                    }
                                """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("errorFieldsMessages")))
                .andExpect(jsonPath("$.errorFieldsMessages[0]").value("Minimum allowable id value: 1"))
                .andExpect(status().is4xxClientError()
                );
    }

    @Test
    @SneakyThrows
    @Order(2)
    void create_when_name_is_empty() {
        mvc
                .perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                        "name": ""
                                    }
                                """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("errorFieldsMessages")))
                .andExpect(jsonPath("$.errorFieldsMessages").isArray())
                .andExpect(jsonPath("$.errorFieldsMessages").value(containsInAnyOrder(
                        "Name should not be empty",
                        "Name should be between 2 and 50 characters"
                )))
                .andExpect(status().is4xxClientError()
                );
    }

    @Test
    @SneakyThrows
    @Order(3)
    void update() {
        mvc
                .perform(put("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                    {
                                        "id": "5",
                                        "name": "laptop"
                                    }
                                """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value("5"))
                .andExpect(jsonPath("$", hasKey("name")))
                .andExpect(jsonPath("$.name").value("laptop"))
                .andExpect(status().isOk()
                );
    }

}
