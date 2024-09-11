package org.jsp.bookmyticket.dao;

import java.util.Optional;

import org.jsp.bookmyticket.model.Bus;
import org.jsp.bookmyticket.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository busRepository;
	
	public Bus saveBus(Bus bus) {
		return busRepository.save(bus);
	}

	public Optional<Bus> findById(int id) {
		return busRepository.findById(id);
	}
	
//	public Optional<Bus> findByBusName(String bus_name) {
//		return busRepository.findByBusName(bus_name);
//	}
	
//	public Optional<Bus> findByBusNumber(String bus_number) {
//		return busRepository.findByBusNumber(bus_number);
//	}
//	
	public void delete(int id) {
		busRepository.deleteById(id);
	}
	
	
}
