package com.eazybytes.accounts.repository;


import com.eazybytes.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository  //  behind the scene spring data jpa will create a bean implementation based upon configuration we provide
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
