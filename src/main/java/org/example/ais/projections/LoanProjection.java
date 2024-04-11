package org.example.ais.projections;

public interface LoanProjection {
    Long getId();

    String getName();

    String getDescription();

    Float getInterestRate();

    Long getAmount();

    Integer getDurationInMonths();
}
