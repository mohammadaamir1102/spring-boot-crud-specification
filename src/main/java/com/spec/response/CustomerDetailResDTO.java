package com.spec.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@NoArgsConstructor
public class CustomerDetailResDTO {
	private Long id;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String mobileNumber;
	private String gender;
	private Boolean status;
	private String address;

}
