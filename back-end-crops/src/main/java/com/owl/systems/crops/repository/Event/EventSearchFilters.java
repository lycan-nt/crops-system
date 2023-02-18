package com.owl.systems.crops.repository.Event;

import com.owl.systems.crops.builder.EventSearchCriterios;
import com.owl.systems.crops.model.Event;
import com.owl.systems.crops.repository.PredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class EventSearchFilters {
    @Autowired
    private EventRepository eventRepository;

    public Page<Event> findAllByFilter(EventSearchCriterios eventSearchCriterios) {
        Page<Event> eventListPaged = this.eventRepository.findAll((Specification<Event>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = filtrar(root, criteriaBuilder, eventSearchCriterios);
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dtEvent")));
            return predicate;
        }, eventSearchCriterios.getPageable());
        return eventListPaged;
    }

    private Predicate filtrar(Root root, CriteriaBuilder criteriaBuilder, EventSearchCriterios eventSearchCriterios) {
        Predicate predicateFilter = new PredicateBuilder(criteriaBuilder)
                .equalIntegerRoot(root,"tpEvent", eventSearchCriterios.getTypeEvent(), eventSearchCriterios.getTypeEvent() != null)
                .build();
        return predicateFilter;
    }

}
