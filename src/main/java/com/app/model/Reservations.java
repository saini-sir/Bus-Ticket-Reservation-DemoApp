package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservations {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reservationId;
	
	
	private String reservationStatus;
	
	@NotNull(message = "This Field can not be null..")
	private String reservationType;
	
	private LocalDate reservationDate;
	
	private LocalDate journeyDate;
	
	private LocalTime reservationTime;
	
	@NotNull(message = "This Field can not be null..")
	private String source;
	
	@NotNull(message = "This Field can not be null..")
	private String destination;
	
	private Integer noOfSeatsBooked;
	
	private Integer fare;
	
	

	@ManyToOne
	@JoinColumn( name = "bus_id", referencedColumnName = "busId")
	private Bus bus;
	
	@ManyToOne
	@JoinColumn( name = "user_id", referencedColumnName = "userLoginId")
	private User user;

	
		

}
