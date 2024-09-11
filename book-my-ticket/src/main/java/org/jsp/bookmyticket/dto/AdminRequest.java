package org.jsp.bookmyticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminRequest {
	private long phone;
	@Email(message = "Invalid Format")
	private String email;
	@NotBlank(message = "Gst Number is Mandatory")
	@Size(min = 15, max = 15)
	private String gst_number;
	@NotBlank(message = "Name is Mandatory fields")
	private String name;
	@NotBlank(message = "Travels Name is Mandatory")
	private String travels_name;
	@NotBlank(message = "Password is Mandatory")
	@Size(min = 8, max = 20)
	private String password;
	
}
