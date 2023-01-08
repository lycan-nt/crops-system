package com.owl.systems.crops.service;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.EventRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
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

    @Autowired
    private EventService eventService;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void testfindAllEvents() throws Exception {
        prepareTestfindAllEvents();
        Assertions.assertEquals(2, this.eventService.findAll().size());
    }

    private void prepareTestfindAllEvents() {
        List<Event> eventList = Arrays.asList(new Event(), new Event());
        Mockito.when(this.eventRepository.findAll())
                .thenReturn(eventList);
    }

    @Test
    public void testFindEvent() throws Exception {
        int idEvent = 1;
        prepareTestFindEvent(idEvent);
        Event event = this.eventService.find(idEvent);
        Assertions.assertEquals("TDD", event.getNmEvent());
    }

    private void prepareTestFindEvent(int idEvent) {
        Event event = new Event();
        event.setNmEvent("TDD");
        event.setCdEvent(1);
        event.setDtEvent(new Date());
        event.setTpEvent(1);
        event.setPlaceEvent("Test");
        Mockito.when(this.eventRepository.findById(idEvent))
                .thenReturn(Optional.of(event));
    }


    @Test
    public void findAllMustThrowNotContextException() throws Exception {
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            this.eventService.findAll();
        });
        Assert.assertEquals("No records found.", exception.getMessage());
    }

    @Test
    public void insertEvent() {
        Event event = creatEventForTests();
        prepareTestInsertOrUpdateEvent(event);
        this.eventService.insert(event);
        Assertions.assertNotNull(event);
    }

    private void prepareTestInsertOrUpdateEvent(Event event) {
        Mockito.when(this.eventRepository.save(event))
                .thenReturn(event);
    }

    private Event creatEventForTests() {
        Event event = new Event();
        event.setCdEvent(1);
        event.setTpEvent(1);
        event.setDtEvent(new Date());
        event.setNmEvent("TDD Insert");
        event.setPlaceEvent("TDD TDD");
        return event;
    }

    @Test
    public void updateEventTest() throws Exception {
        int idEvent = 1;
        prepareTestFindEvent(idEvent);
        Event event = creatEventForTests();
        event.setNmEvent("Test For Update in TDD");
        prepareTestInsertOrUpdateEvent(event);
        this.eventService.update(event);
        Assertions.assertEquals("Test For Update in TDD", event.getNmEvent());
    }

    @Test
    public void exceptionForUpdateEventTestNotFound() throws Exception {
        int idEvent = 1;
        prepareTestFindEvent(idEvent);
        Event event = creatEventForTests();
        event.setNmEvent("Test For Update in TDD");
        event.setCdEvent(100);
        prepareTestInsertOrUpdateEvent(event);
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            this.eventService.update(event);
        });
        Assert.assertEquals("Event not found", exception.getMessage());
    }

    @Test
    public void deleteEvent() throws Exception {
        int idEvent = 1;
        prepareTestDelete(idEvent);
        this.eventService.delete(idEvent);
        Mockito.verify(this.eventRepository, Mockito.times(1)).deleteById(idEvent);
    }

    private void prepareTestDelete(int idEvent) {
        prepareTestFindEvent(idEvent);
        Mockito.doNothing().when(this.eventRepository).deleteById(idEvent);
    }

}
