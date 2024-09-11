package org.jsp.bookmyticket.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BusRequest {
	@NotBlank(message = "Bus Name is Mandatory")
	private String bus_name;
	private LocalDate dateOfDept;
    @NotBlank
	private String bus_number;
    @NotBlank(message = "From location is Mandatory")
	private String from_location;
    @NotBlank(message = "To location is Mandatory")
	private String to_location;
	private int noOfSeats;
}
