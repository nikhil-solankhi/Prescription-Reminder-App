package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "prescription_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Prescription extends BaseEntity {

	@Column(length = 50)
	private String name;
	@Column(length = 50)
	private String dosage;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime reminderTime;
	private boolean isActive;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

}
