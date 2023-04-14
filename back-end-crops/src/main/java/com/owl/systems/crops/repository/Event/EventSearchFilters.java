package com.owl.systems.crops.repository.Event;

import com.owl.library.repositorys.PredicateSearchBuilder;
import com.owl.systems.crops.builder.EventSearchCriterios;
import com.owl.systems.crops.model.Event;
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
        return this.eventRepository.findAll((Specification<Event>) (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = filtrar(root, criteriaBuilder, eventSearchCriterios);
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dtEvent")));
            return predicate;
        }, eventSearchCriterios.getPageable());
    }

    private Predicate filtrar(Root root, CriteriaBuilder criteriaBuilder, EventSearchCriterios eventSearchCriterios) {
        return new PredicateSearchBuilder(criteriaBuilder)
                .equalIntegerRoot(root,"tpEvent", eventSearchCriterios.getTypeEvent(), eventSearchCriterios.getTypeEvent() != null)
                .greaterDateRoot(root,"dtEvent", eventSearchCriterios.getFromDate(), eventSearchCriterios.getFromDate() != null)
                .lessDateRoot(root, "dtEvent", eventSearchCriterios.getToDate(), eventSearchCriterios.getToDate() != null)
                .build();
    }

}
