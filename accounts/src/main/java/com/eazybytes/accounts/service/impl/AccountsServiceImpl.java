package com.eazybytes.accounts.service.impl;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto customerDto){

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDateTime.now());
        System.out.println("@@ " +customer.getCreatedAt());
        System.out.println("@@ " +customer.getCreatedBy());
        System.out.println("@@ " +customer);

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw  new CustomerAlreadyExistsException("Customer already register with given mobile number" +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        System.out.println("@@ " +savedCustomer);
        accountsRepository.save(createNesAccount(savedCustomer));


    }

    private Accounts createNesAccount(Customer customer){

        Accounts newAccount = new Accounts();
        long randomAccNumber = 10000000000L +new Random().nextInt(90000000);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setCreatedBy("Anonymous");
        newAccount.setCreatedAt(LocalDateTime.now());
        System.out.println("@@ " +newAccount);
        System.out.println("@@ " +newAccount.getCreatedAt());
        System.out.println("@@ " +newAccount.getCreatedBy());
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String  mobileNumber){
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "Customer Id Number", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(accounts,new AccountsDto()));
        return customerDto;



    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
       boolean isUpdated = false;
       AccountsDto accountsDto = customerDto.getAccountsDto();
       if(accountsDto!=null){

           Accounts account = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                   ()-> new ResourceNotFoundException("Account","Account Number", accountsDto.getAccountNumber().toString())
           );

           account = AccountsMapper.mapToAccounts(accountsDto,account);
           accountsRepository.save(account);

           Long customerId = account.getCustomerId();
           Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                   ()-> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
           );

           // convert this actountDto to account
           CustomerMapper.mapToCustomer(customerDto,customer);
           customerRepository.save(customer);
           isUpdated = true;
       }
       return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
       boolean isDeleted =false;

        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "Customer Id Number", customer.getCustomerId().toString())
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        isDeleted = true;
        return isDeleted;

    }


}
