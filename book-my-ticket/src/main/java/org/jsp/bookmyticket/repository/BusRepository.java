package org.jsp.bookmyticket.repository;

import java.util.Optional;

import org.jsp.bookmyticket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Integer> {
//	Optional<Bus> findByBusNumber(String bus_number);
	
//	Optional<Bus> findByBusName(String bus_name);
}
