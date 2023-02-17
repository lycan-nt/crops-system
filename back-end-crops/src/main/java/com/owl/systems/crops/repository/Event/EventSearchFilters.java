package com.owl.systems.crops.repository.Event;

import com.owl.systems.crops.builder.EventSearchBuilder;
import com.owl.systems.crops.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class EventSearchFilters {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    private List<Predicate> predicateList;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Event> query;
    private Root<Event> eventRoot;
    private Pageable pageable;

    @PostConstruct
    public void init() {
        this.session = this.sessionFactory.openSession();
        this.predicateList = new ArrayList<>();
        this.criteriaBuilder = this.session.getCriteriaBuilder();
        this.query = this.criteriaBuilder.createQuery(Event.class);
        this.eventRoot = this.query.from(Event.class);
    }

    public List<Event> find(EventSearchBuilder eventSearchBuilder) {
        
        this.query.select(this.eventRoot)
                .where(this.predicateList.toArray(new Predicate[]{}));

        List<Object> eventList = Collections.singletonList(this.session.createQuery(this.query)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList());

        System.out.println("RESULT: " + eventList.toString());



        return null;
    }
}
