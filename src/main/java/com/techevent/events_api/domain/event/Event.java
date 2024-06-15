package com.techevent.events_api.domain.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "event")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private LocalDate date;
}
