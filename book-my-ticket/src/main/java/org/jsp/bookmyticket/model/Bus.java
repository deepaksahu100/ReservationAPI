package org.jsp.bookmyticket.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bus { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String bus_name;
	@Column(nullable=false)
	private LocalDate dateOfDept;
	@Column(nullable=false, unique=true)
	private String bus_number;
	@Column(nullable= false)
	private String from_location;
	@Column(nullable=false)
	private String to_location;
	@Column(nullable=false)
	private int noOfSeats;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

}
