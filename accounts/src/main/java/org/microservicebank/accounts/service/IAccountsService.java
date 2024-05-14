package org.microservicebank.accounts.service;

import org.microservicebank.accounts.dto.CustomerDTO;
import org.springframework.data.repository.query.Param;

public interface IAccountsService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
