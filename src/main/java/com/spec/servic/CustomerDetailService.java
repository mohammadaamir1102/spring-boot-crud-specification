package com.spec.servic;

import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceException;
import com.spec.entity.CustomerDetail;
import com.spec.request.CustomerDetailReqDTO;
import com.spec.request.CustomerDetailReqDynamicQueryDTO;
import com.spec.request.CustomerDetailSearchOperation;
import com.spec.response.CustomerDetailResDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerDetailService {

    CustomerDetailResDTO saveCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO);
    Map getAllCustomerDetail(PaginationDTO paginationDTO) throws ServiceException;
    CustomerDetailResDTO getCustomerDetailById(Long id) throws ServiceException;
    CustomerDetailResDTO updateCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO) throws ServiceException;
    Boolean deleteCustomerDetail(Long id) throws ServiceException;
    Map getCustomerDetailBySearchCriteria(CustomerDetailSearchOperation customerDetailSearchOperation) throws ServiceException;
    Long countByFirstName(String firstName);
    List<CustomerDetail> findByFirstNameAndLastName(String firstName, String lastName);
    List<CustomerDetail> findByIdIn(List<Long> id);
    Map getDataWithDynamicQuery(CustomerDetailReqDynamicQueryDTO customerDetailReqDynamicQueryDTO) throws ServiceException;
    Optional<CustomerDetail> findById(Long id) throws ServiceException;
    String saveExcelFile(MultipartFile file) throws IOException;
    CustomerDetailResDTO getCustomerDetailByIdCache(Long customerDetailId);
//    public Integer updateWithJpql(Long id, String address, String firstName);
}
