package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {


    /*
    *
    * @param customerDto - CustomerDto Object
    * */
    void createAccount(CustomerDto customerDto);
}
