package org.microservicebank.loans.service.impl;

import lombok.AllArgsConstructor;
import org.microservicebank.loans.constants.LoanConstants;
import org.microservicebank.loans.dto.LoanDto;
import org.microservicebank.loans.entity.Loan;
import org.microservicebank.loans.exception.LoanAlreadyExistException;
import org.microservicebank.loans.exception.ResourceNotFoundException;
import org.microservicebank.loans.mapper.LoanMapper;
import org.microservicebank.loans.repository.LoanRepository;
import org.microservicebank.loans.service.ILoanService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()) {
            throw new LoanAlreadyExistException("Loan is already registered with MobileNumber: " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan loan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        // Initializing the Loan value
        loan.setLoanNumber(String.valueOf(randomLoanNumber));
        loan.setMobileNumber(mobileNumber);
        loan.setLoanType(LoanConstants.HOME_LOAN);
        loan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        loan.setAmountPaid(0L);
        loan.setOutstandingAmount(0L);
        return loan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan optionalloan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        LoanDto loanDto = LoanMapper.mapToLoanDto(optionalloan, new LoanDto());
        return loanDto;
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        boolean isUpdated = false;
        if(loanDto != null){
            Loan loan = loanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Loan", "mobileNumber", loanDto.getMobileNumber())
            );
            LoanMapper.mapToLoan(loanDto, loan);
            isUpdated = true;
            loanRepository.save(loan);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.deleteByMobileNumber(mobileNumber);
        return true;
    }
}
