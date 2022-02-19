package com.spec.common.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationDTO {
	
	private int offset;
    private int pageNumber;

}
