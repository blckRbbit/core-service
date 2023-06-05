package com.shary.coreapi.controller;

import com.shary.coreapi.config.init.SettingsIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(3)
@Disabled
public class ItemControllerTest extends SettingsIntegrationTest {

    @Test
    @SneakyThrows
    void create_item_when_data_is_valid() {
        mvc
                .perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
                                                "name": "test",
                                                "serialNumber": "1111",
                                                "photo": "/item/photos/11/1.png",
                                                "video": "/item/videos/11/1.avi",
                                                "category": {
                                                    "id": "1",
                                                    "name": "laptop"
                                                },
                                                "owner": {
                                                    "id": "1",
                                                    "firstName": "firstName",
                                                    "secondName": "secondName",
                                                    "givenName": "givenName",
                                                    "email": "email@email.test",
                                                    "birthday": "1111-01-01",
                                                    "passportNumber": "123456",
                                                    "passportSeries": "11 11",
                                                    "phoneNumber": "87776543210",
                                                    "inn": "1234567890",
                                                    "registrationAddress": "registrationAddress",
                                                    "residenceAddress": "residenceAddress"
                                                }
                                            }
                                        """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value("9"))
                .andExpect(jsonPath("$", hasKey("name")))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$", hasKey("serialNumber")))
                .andExpect(jsonPath("$.serialNumber").value("1111"))
                .andExpect(jsonPath("$", hasKey("category")))
                .andExpect(status().isOk()
                );
    }

    @Test
    @SneakyThrows
    void create_item_when_data_is_invalid() {
        mvc
                .perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
                                                "name": "",
                                                "serialNumber": "1",
                                                "photo": "",
                                                "video": "",
                                                "category": {}
                                            }
                                        """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("errorFieldsMessages")))
                .andExpect(jsonPath("$.errorFieldsMessages").isArray())
                .andExpect(jsonPath("$.errorFieldsMessages").value(containsInAnyOrder(
                        "Name should be between 2 and 50 characters",
                        "Serial number should be between 2 and 50 characters",
                        "Photo should not be empty",
                        "Video should not be empty",
                        "Renter cannot be null"
                )))
                .andExpect(status().is4xxClientError()
                );
    }

    @Test
    @SneakyThrows
    void createRentedItem() {
        mvc
                .perform(post("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("ownerId", "1")
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$", hasKey("name")))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$", hasKey("serialNumber")))
                .andExpect(jsonPath("$.serialNumber").value("1111"))
                .andExpect(jsonPath("$", hasKey("category")))
                .andExpect(status().isOk()
                );
    }

    @Test
    @SneakyThrows
    void update() {
        mvc
                .perform(put("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
                                                "id": "1",
                                                "name": "testUpdate",
                                                "serialNumber": "1112",
                                                "photo": "/item/photos/11/1.png",
                                                "video": "/item/videos/11/1.avi",
                                                "category": {
                                                    "id": "1",
                                                    "name": "laptop"
                                                },
                                                "owner": {
                                                    "id": "1",
                                                    "firstName": "firstName",
                                                    "secondName": "secondName",
                                                    "givenName": "givenName",
                                                    "email": "email@email.test",
                                                    "birthday": "1111-01-01",
                                                    "passportNumber": "123456",
                                                    "passportSeries": "11 11",
                                                    "phoneNumber": "87776543210",
                                                    "inn": "1234567890",
                                                    "registrationAddress": "registrationAddress",
                                                    "residenceAddress": "residenceAddress"
                                                }
                                            }
                                        """
                        )
                )
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", hasKey("id")))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$", hasKey("name")))
                .andExpect(jsonPath("$.name").value("testUpdate"))
                .andExpect(jsonPath("$", hasKey("serialNumber")))
                .andExpect(jsonPath("$.serialNumber").value("1112"))
                .andExpect(jsonPath("$", hasKey("category")))
                .andExpect(status().isOk()
                );
    }

    @Test
    @SneakyThrows
    void remove() {
        mvc
                .perform(delete("/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print()
                );
    }
}
