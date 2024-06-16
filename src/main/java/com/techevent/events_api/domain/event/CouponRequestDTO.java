package com.techevent.events_api.domain.event;

public record CouponRequestDTO(String code, Integer discount, Long valid) {
}
