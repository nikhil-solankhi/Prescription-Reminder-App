package com.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PrescriptionDto {

	private String name;
	private String dosage;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime reminderTime;
	private boolean isActive;
	private Long userId;

}
