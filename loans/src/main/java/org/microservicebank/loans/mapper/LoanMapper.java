package org.microservicebank.loans.mapper;

import org.microservicebank.loans.dto.LoanDto;
import org.microservicebank.loans.entity.Loan;

public class LoanMapper {
    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loan;
    }
}
