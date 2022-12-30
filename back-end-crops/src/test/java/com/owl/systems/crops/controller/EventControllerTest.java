package com.owl.systems.crops.controller;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.EventRepository;
import com.owl.systems.crops.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import java.util.Date;
import java.util.Optional;

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

    @Before
    public void setup() {
        prepareTestFindEvent();
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventService eventService;
    @MockBean
    private EventRepository eventRepository;
    private String baseURL = "/api/v1/events";

    @Test
    @WithMockUser(username="tdd",roles={"ADMIN"})
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(this.baseURL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "tdd", roles = {"ADMIN"})
    public void getEvent() throws Exception {
        Integer idEvent = 1;
        this.mockMvc.perform(MockMvcRequestBuilders.get(this.baseURL + "/" + idEvent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void prepareTestFindEvent() {
        Event event = new Event();
        event.setNmEvent("TDD");
        event.setCdEvent(1);
        event.setDtEvent(new Date());
        event.setTpEvent(1);
        event.setPlaceEvent("Test");
        Mockito.when(this.eventRepository.findById(1))
                .thenReturn(Optional.of(event));
    }

}
