package org.jsp.bookmyticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
	private int id;
	private String name;
	private String email;
	private long phone;
	private int age;
	private String gender;
	private String password;
}
