package com.techevent.events_api.domain.coupon;

import com.techevent.events_api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "coupon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String code;
    private Integer discount;
    private LocalDate valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
