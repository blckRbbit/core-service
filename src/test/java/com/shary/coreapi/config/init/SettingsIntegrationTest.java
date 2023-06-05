package com.shary.coreapi.config.init;

import com.shary.coreapi.config.IntegrationBaseTest;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@AutoConfigureMockMvc
public class SettingsIntegrationTest extends IntegrationBaseTest {
    //@Autowired
    protected MockMvc mvc;

    @PostConstruct
    private void fillDataBase() {
        addCategoryToDataBase();
        createRenter();
        addItemToDataBase();
    }

    @SneakyThrows
    protected void addUserToDataBase() {
        mvc
                .perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"email@email.test\"}")
                );
    }

    @SneakyThrows
    protected void addCategoryToDataBase() {
        mvc
                .perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"test\"}")
                );
    }

    @SneakyThrows
    protected void createRenter() {
        addUserToDataBase();
        mvc
                .perform(post("/users/owner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                            {
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
                                        """
                        )
                );
    }

    @SneakyThrows
    protected void addItemToDataBase() {
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
                                                "isVerified": "true",
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
                );
    }
}
