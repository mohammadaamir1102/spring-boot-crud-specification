package com.spec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spec.entity.CustomerDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long>, JpaSpecificationExecutor<CustomerDetail> {

    Long countByFirstName(String firstName);
    List<CustomerDetail> findByFirstNameAndLastName(String firstName, String lastName);
    List<CustomerDetail> findByIdIn(List<Long> id);
    Optional<CustomerDetail> findById(Long id);
    //    @Query("update User u set u.firstname = ?1, u.lastname = ?2 where u.id = ?3")

    @Modifying
    @Transactional
    @Query(value = "Update CustomerDetail c SET c.address = :address, c.firstName= :firstName where c.id = :id")
    void updateTables(Long id, String address, String firstName);
}
