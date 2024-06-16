package com.techevent.events_api.service;

import com.techevent.events_api.domain.coupon.Coupon;
import com.techevent.events_api.domain.event.CouponRequestDTO;
import com.techevent.events_api.domain.event.Event;
import com.techevent.events_api.repositories.CouponRepository;
import com.techevent.events_api.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    public CouponService(CouponRepository couponRepository, EventRepository eventRepository) {
        this.couponRepository = couponRepository;
        this.eventRepository = eventRepository;
    }

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponRequestDTO) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponRequestDTO.code());
        coupon.setDiscount(couponRequestDTO.discount());
        coupon.setValid(new Date(couponRequestDTO.valid()));
        coupon.setCode(couponRequestDTO.code());
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}
