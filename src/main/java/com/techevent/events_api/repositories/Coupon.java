package com.techevent.events_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Coupon extends JpaRepository<Coupon, UUID> {
}
