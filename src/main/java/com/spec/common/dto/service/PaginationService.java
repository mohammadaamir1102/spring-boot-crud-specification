package com.spec.common.dto.service;

import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceException;
import org.springframework.data.domain.Pageable;

public interface PaginationService {
    Pageable getPagination(PaginationDTO paginationDTO) throws ServiceException;

}
