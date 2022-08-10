package com.spec.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "customer_detail")
public class CustomerDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Long id;

	@NotBlank(message = "Please Enter the first name")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "dob")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "status")
	private Boolean status;

	@Column(name = "address")
	private String address;
	
	@Column(name = "salary")
	private Double salary;	
	

}
