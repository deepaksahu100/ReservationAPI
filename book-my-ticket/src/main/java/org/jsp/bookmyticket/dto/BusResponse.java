package org.jsp.bookmyticket.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusResponse {
	private int id;
	private String bus_name;
	private LocalDate dateOfDept;
	private String bus_number;
	private String from_location;
	private String to_location;
	private int noOfSeats;
}
