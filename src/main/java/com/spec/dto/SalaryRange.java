package com.spec.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class SalaryRange {

	private String firstName;
	private Double startRange;
	private Double endRange;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
}
