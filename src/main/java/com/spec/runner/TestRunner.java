package com.spec.runner;

import com.spec.common.dto.PaginationDTO;
import com.spec.entity.CustomerDetail;
import com.spec.servic.CustomerDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class TestRunner implements CommandLineRunner {

    @Autowired
    private CustomerDetailService customerDetailService;


    @Override
    public void run(String... args) throws Exception {
        log.info("Command line Runner ..................................");
     /*   Optional<CustomerDetail> byId = customerDetailService.findById(60l);
        CustomerDetail customerDetail = byId.get();
        System.out.println(customerDetail);*/


       /* PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPageNumber(0);
        paginationDTO.setOffset(10);
        Map allCustomerDetail = customerDetailService.getAllCustomerDetail(paginationDTO);
        allCustomerDetail.forEach((a,b) -> System.out.println((a+ "  "+b)));*/
        log.info("Command line Runner ..................................");
    }
}
