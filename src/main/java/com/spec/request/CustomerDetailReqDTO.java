package com.spec.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetailReqDTO {
	private Long id;
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String mobileNumber;
	private String gender;
	private Boolean status=Boolean.TRUE;
	private String address;

}
