package com.eazybytes.loans.loans.repository;

import com.eazybytes.loans.loans.entity.Loan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {


   Optional<Loan>  findByMobileNumber(String mobileNumber);
   @Modifying
   @Transactional
   void deleteByMobileNumber(String mobileNumber);


}
