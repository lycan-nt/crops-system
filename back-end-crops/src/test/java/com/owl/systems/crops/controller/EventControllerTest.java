package com.owl.systems.crops.controller;

import com.owl.systems.crops.repository.EventRepository;
import com.owl.systems.crops.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class EventControllerTest {

    @TestConfiguration
    static class EventControllerTestConfiguration {
        @Bean
        public EventService eventService() {
            return new EventService();
        }
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventService eventService;
    @MockBean
    private EventRepository eventRepository;

    @Test
    @WithMockUser(username="tdd",roles={"ADMIN"})
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
