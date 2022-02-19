package com.spec.servic;

import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceException;
import com.spec.request.CustomerDetailReqDTO;
import com.spec.request.CustomerDetailSearchOperation;
import com.spec.response.CustomerDetailResDTO;

import java.util.Map;

public interface CustomerDetailService {

    CustomerDetailResDTO saveCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO);
    Map getAllCustomerDetail(PaginationDTO paginationDTO) throws ServiceException;
    CustomerDetailResDTO getCustomerDetailById(Long id) throws ServiceException;
    CustomerDetailResDTO updateCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO) throws ServiceException;
    Boolean deleteCustomerDetail(Long id) throws ServiceException;
    Map getCustomerDetailBySearchCriteria(CustomerDetailSearchOperation customerDetailSearchOperation) throws ServiceException;
}
