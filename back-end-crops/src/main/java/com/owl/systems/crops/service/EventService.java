package com.owl.systems.crops.service;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.model.User;
import com.owl.systems.crops.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() throws Exception {
        List<Event> eventList = this.eventRepository.findAll();
        if (eventList.isEmpty())
            throw new Exception("No records found.");
        return eventList;
    }

    public Optional<Event> find(Integer idEvent) {
        return this.eventRepository.findById(idEvent);
    }

    public Event insert(Event event) {
        return this.eventRepository.save(event);
    }
}
