package com.owl.systems.crops.service;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.model.User;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Event> eventList = Arrays.asList(new Event(), new Event());
        Mockito.when(this.eventRepository.findAll())
                .thenReturn(eventList);
    }

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void testfindAllEvents() {
        List<User> eventList = new ArrayList<User>();
        Assertions.assertEquals(2, this.eventService.findAll().size());
    }

}
