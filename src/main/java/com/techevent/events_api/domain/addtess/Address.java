package com.techevent.events_api.domain.addtess;

import com.techevent.events_api.domain.event.Event;
import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String city;

    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
