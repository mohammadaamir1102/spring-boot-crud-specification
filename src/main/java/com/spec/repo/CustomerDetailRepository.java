package com.spec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spec.entity.CustomerDetail;

import java.util.List;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long>, JpaSpecificationExecutor<CustomerDetail> {

    Long countByFirstName(String firstName);
    List<CustomerDetail> findByFirstNameAndLastName(String firstName, String lastName);
    List<CustomerDetail> findByIdIn(List<Long> id);
}
