package com.payevery.project.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesReqDTO {

    private LocalDate expenseDate;
    private Double expenseValue;
    private Double vatOnExpense;
    private String  reasonForExpense;
}
