package com.eazybytes.accounts.repository;


import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  //  behind the scene spring data jpa will create a bean implementation based upon configuration we provide
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
