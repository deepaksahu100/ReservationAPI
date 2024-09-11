package org.jsp.bookmyticket.controller;

import org.jsp.bookmyticket.dto.BusRequest;
import org.jsp.bookmyticket.dto.BusResponse;
import org.jsp.bookmyticket.dto.ResponseStructure;
import org.jsp.bookmyticket.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/buses")
public class BusController {
	@Autowired
	private BusService busService;
	
	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(@Valid @RequestBody BusRequest busRequest, @PathVariable(name="admin_id") int admin_id) {
		return busService.saveBus(busRequest, admin_id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(@RequestBody BusRequest busRequest, @PathVariable int id) {
		return busService.update(busRequest, id);
	}

	@GetMapping("{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(@PathVariable int id) {
		return busService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id) {
		return busService.delete(id);
	}

}
