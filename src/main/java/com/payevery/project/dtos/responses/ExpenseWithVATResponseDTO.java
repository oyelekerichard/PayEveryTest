package com.payevery.project.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
public class ExpenseWithVATResponseDTO {

    private LocalDate dateOfExpense;
    private Double amountSpent;
    private Double vatOnExpense;
    private String reasonForExpenses;
}
