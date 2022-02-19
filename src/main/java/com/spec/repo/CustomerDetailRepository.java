package com.spec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spec.entity.CustomerDetail;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long>, JpaSpecificationExecutor<CustomerDetail> {

}
