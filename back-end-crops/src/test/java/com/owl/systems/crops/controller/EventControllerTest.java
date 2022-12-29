package com.owl.systems.crops.controller;

import com.owl.systems.crops.config.WebSecurityConfig;
import com.owl.systems.crops.dto.LoginRequest;
import com.owl.systems.crops.security.JwtProvider;
import com.owl.systems.crops.service.AuthService;
import com.owl.systems.crops.service.AuthenticationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="tdd",roles={"ADMIN"})
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
