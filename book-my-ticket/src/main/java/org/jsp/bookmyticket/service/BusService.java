package org.jsp.bookmyticket.service;

import java.util.Optional;

import org.jsp.bookmyticket.Exception.AdminNotFoundException;
import org.jsp.bookmyticket.Exception.BusNotFoundException;
import org.jsp.bookmyticket.dao.AdminDao;
import org.jsp.bookmyticket.dao.BusDao;
import org.jsp.bookmyticket.dto.AdminResponse;
import org.jsp.bookmyticket.dto.BusRequest;
import org.jsp.bookmyticket.dto.BusResponse;
import org.jsp.bookmyticket.dto.ResponseStructure;
import org.jsp.bookmyticket.model.Admin;
import org.jsp.bookmyticket.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(BusRequest busRequest, int admin_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Bus bus = mapToBus(busRequest);
			bus.setAdmin(recAdmin.get());
			recAdmin.get().getBuses().add(bus);
			adminDao.saveAdmin(recAdmin.get());
			busDao.saveBus(bus);
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		
//		structure.setMessage("Bus saved");
//		Bus bus = busDao.saveBus(mapToBus(busRequest));
//		structure.setData(mapToBusResponse(bus));
//		structure.setStatusCode(HttpStatus.CREATED.value());
//		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		
		throw new AdminNotFoundException("Cannot Add Bus as Admin Id is Invalid");
	}
	
	public ResponseEntity<ResponseStructure<BusResponse>> update(BusRequest busRequest, int id) {
		Optional<Bus> recBus = busDao.findById(id);
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		if (recBus.isPresent()) {
			Bus dbBus = recBus.get();
			dbBus.setBus_name(busRequest.getBus_name());
			dbBus.setBus_number(busRequest.getBus_number());
			dbBus.setDateOfDept(busRequest.getDateOfDept());
			dbBus.setFrom_location(busRequest.getFrom_location());
			dbBus.setTo_location(busRequest.getTo_location());
			dbBus.setNoOfSeats(busRequest.getNoOfSeats());
//			dbBus.setId(id);
			structure.setData(mapToBusResponse(busDao.saveBus(dbBus)));
			structure.setMessage("Bus Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new BusNotFoundException("Cannot Update Bus as Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<BusResponse>> findById(int id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> dbBus = busDao.findById(id);
		if (dbBus.isPresent()) {
			structure.setData(mapToBusResponse(dbBus.get()));
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Invalid Bus Id");
	}
	
	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Bus> dbBus = busDao.findById(id);
		if (dbBus.isPresent()) {
			busDao.delete(id);
			structure.setData("Admin Found");
			structure.setMessage("Admin deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new AdminNotFoundException("Cannot delete Admin as Id is Invalid");
	}
	
	
	
	private Bus mapToBus(BusRequest busRequest) {
		return Bus.builder().bus_name(busRequest.getBus_name()).bus_number(busRequest.getBus_number()).dateOfDept(busRequest.getDateOfDept())
				.from_location(busRequest.getFrom_location()).to_location(busRequest.getTo_location()).noOfSeats(busRequest.getNoOfSeats()).build();
	}
	
	private BusResponse mapToBusResponse(Bus bus) {
		return BusResponse.builder().bus_name(bus.getBus_name()).bus_number(bus.getBus_number()).dateOfDept(bus.getDateOfDept()).from_location(bus.getFrom_location())
				.id(bus.getId()).to_location(bus.getTo_location()).noOfSeats(bus.getNoOfSeats()).build();
	}
	
	
}
