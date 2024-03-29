package com.owl.systems.crops.service;

import com.owl.systems.crops.builder.EventSearchCriterios;
import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.Event.EventRepository;
import com.owl.systems.crops.repository.Event.EventSearchFilters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventSearchFilters eventSearchFilters;

    public List<Event> findAll() throws Exception {
        List<Event> eventList = this.eventRepository.findAll();
        if (eventList.isEmpty())
            throw new Exception("No records found.");
        return eventList;
    }

    public Event find(Integer idEvent) throws Exception {
        Optional<Event> obj =  this.eventRepository.findById(idEvent);
        return obj.orElseThrow(() -> new Exception("Event not found"));
    }

    public Event insert(Event event) {
        return this.eventRepository.save(event);
    }

    public Event update(Event event) throws Exception {
        find(event.getCdEvent());
        return this.eventRepository.save(event);
    }

    public void delete(int idEvent) throws Exception {
        find(idEvent);
        this.eventRepository.deleteById(idEvent);
    }

    public Page<Event> findAllByFilter(EventSearchCriterios eventSearchCriterios) {
        Page<Event> eventListPaged = this.eventSearchFilters.findAllByFilter(eventSearchCriterios);
        return eventListPaged;
    }
}
