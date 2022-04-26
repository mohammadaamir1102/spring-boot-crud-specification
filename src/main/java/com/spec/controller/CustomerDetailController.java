package com.spec.controller;

import com.spec.common.dto.ErrorConstant;
import com.spec.common.dto.PaginationDTO;
import com.spec.common.dto.ServiceError;
import com.spec.common.dto.ServiceException;
import com.spec.dto.CustomerDetailDTO;
import com.spec.dto.CutomerDetailId;
import com.spec.entity.CustomerDetail;
import com.spec.helper.ExcelHelper;
import com.spec.repo.CustomerDetailRepository;
import com.spec.request.CustomerDetailReqDTO;
import com.spec.request.CustomerDetailSearchOperation;
import com.spec.response.CustomerDetailResDTO;
import com.spec.servic.CustomerDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/customer/")
public class CustomerDetailController {


    @Autowired
    CustomerDetailService customerDetailService;

    @Autowired
    CustomerDetailRepository customerDetailRepository;

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

    @GetMapping(value = "countByFirstName/{firstName}")
    public Long countByFirstName(@PathVariable String firstName) {
        log.info("inside CustomerDetailController, countByFirstName method called !!");
        return customerDetailService.countByFirstName(firstName);
    }

    @GetMapping(value = "findByFirstNameAndLastName/{page}/{offset}")
    public List<CustomerDetail> findByFirstNameAndLastName(@PathVariable Long page, @PathVariable Long offset, @RequestParam String firstName, @RequestParam String lastName) {
        log.info("inside CustomerDetailController, countByFirstName method called !!");
        return customerDetailService.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping(value = "findByIdIn")
    public List<CustomerDetail> findByIdIn(@RequestBody CustomerDetailDTO customerDetailDTO) {
        log.info("inside CustomerDetailController, countByFirstName method called !!");
        return customerDetailService.findByIdIn(customerDetailDTO.getId());
    }

    @GetMapping(value = "getCustomerNameById/{id}")
    public List<String> findById(@PathVariable Long id) throws ServiceException {
        List<String> getNames = new ArrayList<>();
        Optional<CustomerDetail> firstNameAndLastName = customerDetailService.findById(id);
        getNames.add(firstNameAndLastName.get().getFirstName());
        getNames.add(firstNameAndLastName.get().getLastName());
        return getNames;


    }

    /*Below api for cache practice*/
    @GetMapping(value = "getCustomerDetailByIdCache/{id}")
    public CustomerDetailResDTO getCustomerDetailByIdCache(@PathVariable Long customerDetailId) throws ServiceException {
        log.info("inside CustomerDetailController, getCustomerDetailById method called !!");
        return customerDetailService.getCustomerDetailByIdCache(customerDetailId);
    }


    @PutMapping(value = "updateRecordByNativeQuery/{id}/{address}/{firstName}")
    public String findBYID(@PathVariable Long id, @PathVariable String address, @PathVariable String firstName) {
        customerDetailRepository.updateTables(id, address, firstName);
        return "updated";
    }

    //excel read controller
    @PostMapping("excel-upload")
    public String saveExcelFile(@RequestParam("file") MultipartFile file) throws IOException, ServiceException {
        if(ExcelHelper.checkExcelFormat(file)){
            return customerDetailService.saveExcelFile(file);
        }else {
            ServiceError serviceError = new ServiceError(ErrorConstant.EXCEL_FORMAT, ErrorConstant.EXCEL_FORMAT_EXCEPTION);
            throw new ServiceException(HttpStatus.BAD_REQUEST, serviceError);
        }

    }


}
