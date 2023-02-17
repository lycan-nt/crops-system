package com.owl.systems.crops.repository.Event;

import com.owl.systems.crops.builder.EventSearchBuilder;
import com.owl.systems.crops.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventSearchFilters {
    public List<Event> findAllByFilter(EventSearchBuilder eventSearchBuilder) {
        return null;
    }
}
