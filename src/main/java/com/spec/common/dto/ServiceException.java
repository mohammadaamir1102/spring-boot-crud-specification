package com.spec.common.dto;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceError serviceError;
	private HttpStatus status;
	
	
	public ServiceException(HttpStatus status, ServiceError serviceError) {
		super(serviceError.msg);
		this.status = status;
		this.serviceError = serviceError;
	}
	
	

}
