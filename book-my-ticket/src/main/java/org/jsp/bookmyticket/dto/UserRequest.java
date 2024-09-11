package org.jsp.bookmyticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
	@NotBlank(message = "Name is Mandatory")
	private String name;
	@NotBlank(message = "Password is mandatory")
	@Size(min = 5, max = 20)
	private String password;
	@NotBlank(message = "Email is Mandatory")
	@Email
	private String email;
	private long phone;
	@NotBlank(message = "Gender is mandatory")
	private String gender;
	private int age;
}
