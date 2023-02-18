package com.owl.systems.crops.repository;

import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@Service
public class PredicateBuilder {
    private Predicate predicateFilter;
    private CriteriaBuilder criteriaBuilder;

    public PredicateBuilder() {}

    public PredicateBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
        this.predicateFilter = this.criteriaBuilder.conjunction();
    }

    public PredicateBuilder equalIntegerRoot(Root root, String colum, Integer value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.equal(root.get(colum), value));
        return this;
    }

    public PredicateBuilder equalIntegerJoin(Join join, String colum, Integer value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.equal(join.get(colum), value));
        return this;
    }

    public PredicateBuilder greaterDateRoot(Root root, String colum, Date value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.greaterThanOrEqualTo(root.get(colum), value));
        return this;
    }

    public PredicateBuilder greaterDateJoin(Join join, String colum, Date value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.greaterThanOrEqualTo(join.get(colum), value));
        return this;
    }

    public PredicateBuilder lessDateRoot(Root root, String colum, Date value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.lessThanOrEqualTo(root.get(colum), value));
        return this;
    }

    public PredicateBuilder lessDateJoin(Join join, String colum, Date value, boolean condition) {
        if(condition)
            this.predicateFilter = this.criteriaBuilder.and(this.predicateFilter, this.criteriaBuilder.lessThanOrEqualTo(join.get(colum), value));
        return this;
    }

    public Predicate build() {
        return this.predicateFilter;
    }
}
