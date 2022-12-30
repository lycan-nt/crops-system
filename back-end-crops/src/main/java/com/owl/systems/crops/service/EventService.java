package com.owl.systems.crops.service;

import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    public Optional<Event> find(Integer idEvent) {
        return this.eventRepository.findById(idEvent);
    }
}
