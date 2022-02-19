package com.spec.common.dto;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {

	private ServiceError serviceError;
	private HttpStatus status;
	
	
	public ServiceException(HttpStatus status, ServiceError serviceError) {
		super(serviceError.msg);
		this.status = status;
		this.serviceError = serviceError;
	}
	
	

}
