package org.microservicebank.loans.service;

import org.microservicebank.loans.dto.LoanDto;
import org.microservicebank.loans.entity.Loan;


public interface ILoanService {
    void createLoan(String mobileNumber);
    LoanDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);

}
