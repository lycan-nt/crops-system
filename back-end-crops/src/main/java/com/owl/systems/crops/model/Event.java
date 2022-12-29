package com.owl.systems.crops.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdEvent;

    @Column
    private String nmEvent;

    @Column
    private Date dtEvent;

    @Column
    private int tpEvent;

    @Column
    private String placeEvent;

    public int getCdEvent() {
        return cdEvent;
    }

    public void setCdEvent(int cdEvent) {
        this.cdEvent = cdEvent;
    }

    public String getNmEvent() {
        return nmEvent;
    }

    public void setNmEvent(String nmEvent) {
        this.nmEvent = nmEvent;
    }

    public Date getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(Date dtEvent) {
        this.dtEvent = dtEvent;
    }

    public int getTpEvent() {
        return tpEvent;
    }

    public void setTpEvent(int tpEvent) {
        this.tpEvent = tpEvent;
    }

    public String getPlaceEvent() {
        return placeEvent;
    }

    public void setPlaceEvent(String placeEvent) {
        this.placeEvent = placeEvent;
    }

}
