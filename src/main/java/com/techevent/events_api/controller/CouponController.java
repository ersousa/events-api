package com.techevent.events_api.controller;

import com.techevent.events_api.domain.coupon.Coupon;
import com.techevent.events_api.domain.event.CouponRequestDTO;
import com.techevent.events_api.service.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/events/{eventId}")
    public ResponseEntity<Coupon> addCouponToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO requestDTO){
     Coupon coupon = this.couponService.addCouponToEvent(eventId, requestDTO);
     return ResponseEntity.ok(coupon);
    }

}
