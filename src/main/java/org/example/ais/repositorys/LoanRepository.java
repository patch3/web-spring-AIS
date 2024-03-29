package org.example.ais.repositorys;

import org.example.ais.models.Loan;
import org.example.ais.projections.LoanProjection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Override
    @NonNull
    List<Loan> findAll();

    List<LoanProjection>
    findProjectionByNameStartingWithAndDurationInMonthsStartingWithAndInterestRateStartingWithAndAmountStartingWith(
            String patternName, Integer durationInMonths, Float interestRate, Long amount, Sort sort
    );
}
