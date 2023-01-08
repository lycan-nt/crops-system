package com.owl.systems.crops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.EventRepository;
import com.owl.systems.crops.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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
        prepareTestfindAllEvents();
        this.mockMvc.perform(MockMvcRequestBuilders.get(this.baseURL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private void prepareTestfindAllEvents() {
        Event event1 = new Event();
        event1.setNmEvent("TDD");
        event1.setCdEvent(1);
        event1.setDtEvent(new Date());
        event1.setTpEvent(1);
        event1.setPlaceEvent("Test");
        Event event2 = new Event();
        event2.setNmEvent("TDD2");
        event2.setCdEvent(2);
        event2.setDtEvent(new Date());
        event2.setTpEvent(2);
        event2.setPlaceEvent("Test2");
        List<Event> eventList = Arrays.asList(event1, event2);
        Mockito.when(this.eventRepository.findAll())
                .thenReturn(eventList);
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

    @Test
    @WithMockUser(username = "tdd", roles = {"ADMIN"})
    public void insert() throws Exception {
        Event event = eventForTests();
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(this.baseURL)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .content(asJsonString(event))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private Event eventForTests() {
        Event event = new Event();
        event.setCdEvent(1);
        event.setTpEvent(1);
        event.setDtEvent(new Date());
        event.setNmEvent("TDD Insert");
        event.setPlaceEvent("TDD TDD");
        return event;
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "tdd", roles = {"ADMIN"})
    public void testUpdate() throws Exception {
        Event event = eventForTests();
        event.setNmEvent("Test For Update in TDD");
        this.mockMvc.perform(MockMvcRequestBuilders
                .put(this.baseURL + "/{id}", event.getCdEvent())
                    .content(asJsonString(event))
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nmEvent").value("Test For Update in TDD"));
    }

    @Test
    @WithMockUser(username = "tdd", roles = {"ADMIN"})
    public void testDelete() throws Exception {
        int idEvent = 1;
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete(this.baseURL + "/{id}", idEvent)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
            ).andExpect(MockMvcResultMatchers.status().isAccepted());
    }

}
