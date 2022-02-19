package com.spec.controller;

import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceException;
import com.spec.request.CustomerDetailReqDTO;
import com.spec.request.CustomerDetailSearchOperation;
import com.spec.response.CustomerDetailResDTO;
import com.spec.servic.CustomerDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/customer/")
public class CustomerDetailController {


    @Autowired
    CustomerDetailService customerDetailService;

    @PostMapping(value = "/save")
    public CustomerDetailResDTO saveCustomerDetail(@RequestBody CustomerDetailReqDTO customerDetailReqDTO) {
        log.info("inside CustomerDetailController, saveCustomerDetail method called !!");
        return customerDetailService.saveCustomerDetail(customerDetailReqDTO);
    }

    @GetMapping(value = "/getAllCustomerDetail/{page}/{offset}")
    public Map getAllCustomerDetail(@PathVariable Long page, @PathVariable Long offset) throws ServiceException {
        log.info("inside CustomerDetailController, getAllCustomerDetail method called !!");
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setOffset(offset.intValue());
        paginationDTO.setPageNumber(page.intValue());
        return customerDetailService.getAllCustomerDetail(paginationDTO);
    }

    @GetMapping(value = "getCustomerDetailById/{id}")
    public CustomerDetailResDTO getCustomerDetailById(@PathVariable Long id) throws ServiceException {
        log.info("inside CustomerDetailController, getCustomerDetailById method called !!");
        return customerDetailService.getCustomerDetailById(id);
    }

    @PutMapping(value = "updateCustomerDetail")
    public CustomerDetailResDTO updateCustomerDetail(@RequestBody CustomerDetailReqDTO customerDetailReqDTO) throws ServiceException {
        log.info("inside CustomerDetailController, updateCustomerDetail method called !!");
        return customerDetailService.updateCustomerDetail(customerDetailReqDTO);
    }

    @DeleteMapping(value = "deleteCustomerDetail/{id}")
    public Boolean deleteCustomerDetail(@PathVariable Long id) throws ServiceException {
        log.info("inside CustomerDetailController, deleteCustomerDetail method called !!");
        return customerDetailService.deleteCustomerDetail(id);
    }

    @PostMapping(value = "/customerDetailSearchCriteria")
    public Map getCustomerDetailBySearchCriteria(@RequestBody CustomerDetailSearchOperation customerDetailSearchOperation) throws ServiceException {
        log.info("inside CustomerDetailController, getCustomerDetailBySearchCriteria method called !!");
        return customerDetailService.getCustomerDetailBySearchCriteria(customerDetailSearchOperation);
    }

}
