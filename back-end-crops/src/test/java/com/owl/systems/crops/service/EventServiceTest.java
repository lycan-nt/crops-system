package com.owl.systems.crops.service;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EventServiceTest {

    @TestConfiguration
    static class EventServiceTestConfiguration {
        @Bean
        public EventService eventService() {
            return new EventService();
        }
    }

    @Before
    public void setup() {
        prepareTestfindAllEvents();
        prepareTestFindEvent();
    }

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void testfindAllEvents() {
        Assertions.assertEquals(2, this.eventService.findAll().size());
    }

    private void prepareTestfindAllEvents() {
        List<Event> eventList = Arrays.asList(new Event(), new Event());
        Mockito.when(this.eventRepository.findAll())
                .thenReturn(eventList);
    }

    @Test
    public void testFindEvent() {
        int idEvent = 1;
        Event event = this.eventService.find(idEvent).get();
        Assertions.assertEquals("TDD", event.getNmEvent());
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
