package com.spec.servic.impl;

import com.spec.common.dto.*;
import com.spec.common.dto.service.PaginationService;
import com.spec.entity.CustomerDetail;
import com.spec.repo.CustomerDetailRepository;
import com.spec.request.CustomerDetailReqDTO;
import com.spec.request.CustomerDetailReqDynamicQueryDTO;
import com.spec.request.CustomerDetailSearchOperation;
import com.spec.response.CustomerDetailResDTO;
import com.spec.servic.CustomerDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Cacheable;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    Mapper dozerMapper;

    @Autowired
    PaginationService paginationService;

    @Autowired
    CustomerDetailRepository customerDetailRepository;

    @Override
    public CustomerDetailResDTO saveCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO) {
        log.info("inside CustomerDetailServiceImpl, saveCustomerDetail method called!!");
        CustomerDetail customerDetail = dozerMapper.map(customerDetailReqDTO, CustomerDetail.class);
        CustomerDetail detail = customerDetailRepository.save(customerDetail);
        return dozerMapper.map(detail, CustomerDetailResDTO.class);
    }

    @Override
    public Map getAllCustomerDetail(PaginationDTO paginationDTO) throws ServiceException {
        log.info("inside CustomerDetailServiceImpl, getAllCustomerDetail method called!!");
        Page<CustomerDetail> customerDetails = customerDetailRepository.findAll(paginationService.getPagination(paginationDTO));
        List<CustomerDetail> filterCustomerDetails = customerDetails.stream().filter(f -> f.getStatus() != false).collect(Collectors.toList());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", filterCustomerDetails);
        dataMap.put("totalPage", customerDetails.getTotalPages());
        dataMap.put("currentPage", customerDetails.getNumber());
        dataMap.put("totalRecords", filterCustomerDetails.size());
        return dataMap;
    }

    @Override
    public CustomerDetailResDTO getCustomerDetailById(Long id) throws ServiceException {
        log.info("inside CustomerDetailServiceImpl, getCustomerDetailById method called!!");
        Optional<CustomerDetail> customerDetailOptional = customerDetailRepository.findById(id);
        if (!customerDetailOptional.isPresent()) {
            ServiceError serviceError = new ServiceError(ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND, ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND_EXCEPTION);
            throw new ServiceException(HttpStatus.BAD_REQUEST, serviceError);
        }
        return customerDetailOptional.map(value -> {
            CustomerDetailResDTO map = dozerMapper.map(value, CustomerDetailResDTO.class);
            return map;
        }).orElse(null);
    }

    @Override
    public CustomerDetailResDTO updateCustomerDetail(CustomerDetailReqDTO customerDetailReqDTO) throws ServiceException {
        log.info("inside CustomerDetailServiceImpl, getCustomerDetailById method called!!");
        Optional<CustomerDetail> customerDetailOptional = customerDetailRepository.findById(customerDetailReqDTO.getId());
        if (!customerDetailOptional.isPresent()) {
            ServiceError serviceError = new ServiceError(ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND, ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND_EXCEPTION);
            throw new ServiceException(HttpStatus.BAD_REQUEST, serviceError);
        }
        CustomerDetail customerDetail = customerDetailOptional.get();
        dozerMapper.map(customerDetailReqDTO, customerDetail);
        customerDetailRepository.save(customerDetail);
        return dozerMapper.map(customerDetail, CustomerDetailResDTO.class);
    }

    @Override
    public Boolean deleteCustomerDetail(Long id) throws ServiceException {
        log.info("inside CustomerDetailServiceImpl, deleteCustomerDetail method called!!");
        Optional<CustomerDetail> customerDetailOptional = customerDetailRepository.findById(id);
        if (!customerDetailOptional.isPresent()) {
            ServiceError serviceError = new ServiceError(ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND, ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND_EXCEPTION);
            throw new ServiceException(HttpStatus.BAD_REQUEST, serviceError);
        }
        CustomerDetail customerDetail = customerDetailOptional.get();
        customerDetail.setStatus(Boolean.FALSE);
        customerDetailRepository.save(customerDetail);
        return Boolean.FALSE;
    }

    @Override
    public Map getCustomerDetailBySearchCriteria(CustomerDetailSearchOperation customerDetailSearchOperation) throws ServiceException {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setOffset(customerDetailSearchOperation.getOffset().intValue());
        paginationDTO.setPageNumber(customerDetailSearchOperation.getPageNumber().intValue());
        Page<CustomerDetail> customerDetails = customerDetailRepository.findAll(getQuerySpecification(customerDetailSearchOperation),
                paginationService.getPagination(paginationDTO));

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("data", customerDetails);
        mapData.put("totalPage", customerDetails.getTotalPages());
        mapData.put("currentPage", customerDetails.getNumber());
        mapData.put("totalRecords", customerDetails.getTotalElements());
        return mapData;
    }

    @Override
    public Long countByFirstName(String firstName) {
        log.info("inside CustomerDetailServiceImpl, countByFirstName method called!!");
        return customerDetailRepository.countByFirstName(firstName);
    }

    @Override
    public List<CustomerDetail> findByFirstNameAndLastName(String firstName, String lastName) {
        log.info("inside CustomerDetailServiceImpl, findByFirstNameAndLastName method called!!");
        return customerDetailRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<CustomerDetail> findByIdIn(List<Long> id) {
        log.info("inside CustomerDetailServiceImpl, findByFirstNameAndLastName method called!!");
        return customerDetailRepository.findByIdIn(id);
    }

    @Override
    public Map getDataWithDynamicQuery(CustomerDetailReqDynamicQueryDTO customerDetailReqDynamicQueryDTO) throws ServiceException {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setOffset(customerDetailReqDynamicQueryDTO.getOffset().intValue());
        paginationDTO.setPageNumber(customerDetailReqDynamicQueryDTO.getPageNumber().intValue());
        Page<CustomerDetail> CustomerDetailDynamicValue = customerDetailRepository.findAll(getDynamicQuerySpecification(customerDetailReqDynamicQueryDTO), paginationService.getPagination(paginationDTO));
        return null;
    }

    @Override
    public Optional<CustomerDetail> findById(Long id) throws ServiceException {
        Optional<CustomerDetail> customerDetailById = customerDetailRepository.findById(id);
        if (!customerDetailById.isPresent()) {
            ServiceError serviceError = new ServiceError(ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND, ErrorConstant.CUSTOMER_DETAIL_NOT_FOUND_EXCEPTION);
            throw new ServiceException(HttpStatus.BAD_REQUEST, serviceError);
        }
        return Optional.of(customerDetailById.get());
    }

    @Override
//    @Cacheable(value = CommonCacheEnum.Constants.CustomerDetailById,)
    public CustomerDetailResDTO getCustomerDetailByIdCache(Long customerDetailId) {
        return null;
    }

//    @Override
//    public Integer updateWithJpql(Long id, String address, String firstName) {
//        return customerDetailRepository.updateTables(id,address,firstName);
//    }

    private Specification<CustomerDetail> getDynamicQuerySpecification(CustomerDetailReqDynamicQueryDTO customerDetailReqDynamicQueryDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
//            if (!customerDetailReqDynamicQueryDTO.getId().isEmpty() && customerDetailReqDynamicQueryDTO.getId() !=null){
//                predicate.add(criteriaBuilder.in(root.get(("id"),customerDetailReqDynamicQueryDTO.getId())));
//            }
            if(customerDetailReqDynamicQueryDTO.getFromDate() !=null){
                predicate.add(criteriaBuilder.lessThan(root.get("fromDate"),customerDetailReqDynamicQueryDTO.getFromDate()));
            }
            if(customerDetailReqDynamicQueryDTO.getToDate() !=null){
                predicate.add(criteriaBuilder.lessThan(root.get("toDate"),customerDetailReqDynamicQueryDTO.getToDate()));
            }

            return null;
        };

    }

    private Specification<CustomerDetail> getQuerySpecification(CustomerDetailSearchOperation customerDetailSearchOperation) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicate = new ArrayList<>();


            if (customerDetailSearchOperation.getFirstName() != null && !customerDetailSearchOperation.getFirstName().isEmpty()) {
                predicate.add(criteriaBuilder.equal(root.get("firstName"), customerDetailSearchOperation.getFirstName()));
            }
            if (customerDetailSearchOperation.getLastName() != null && !customerDetailSearchOperation.getLastName().isEmpty()) {
                predicate.add(criteriaBuilder.equal(root.get("lastName"), customerDetailSearchOperation.getLastName()));
            }
            return criteriaBuilder.or(predicate.toArray(new javax.persistence.criteria.Predicate[predicate.size()]));
        };
    }


}