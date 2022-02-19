package com.spec.common.dto.service.impl;

import com.spec.common.dto.ErrorConstant;
import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceError;
import com.spec.common.dto.ServiceException;
import com.spec.common.dto.service.PaginationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Override
    public Pageable getPagination(PaginationDTO paginationDTO) throws ServiceException {
        if(Objects.isNull(paginationDTO.getOffset()) && Objects.isNull(paginationDTO.getPageNumber())){
            ServiceError serviceError = new ServiceError(ErrorConstant.OFFSET_AND_PAGE_NUMBER_REQUIRED, ErrorConstant.SOMETHING_WENT_WRONG);
            throw new ServiceException(HttpStatus.BAD_REQUEST,serviceError);
        }
        return PageRequest.of(paginationDTO.getPageNumber(), paginationDTO.getOffset(), Sort.by("id").descending());
    }
}
