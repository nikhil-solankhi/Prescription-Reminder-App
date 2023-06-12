package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User extends BaseEntity {
	@Column(length = 50)
	private String email;
	@Column(length = 100)
	@JsonIgnore
	private String password;
	@Column(length = 20, name = "first_name")
	private String firstName;
	@Column(length = 20, name = "last_name")
	private String lastName;

	private String contactNo;
	

	private boolean isEnabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<Prescription> prescription = new HashSet<>();

}
