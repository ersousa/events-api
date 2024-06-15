package com.techevent.events_api.repositories;

import com.techevent.events_api.domain.addtess.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
