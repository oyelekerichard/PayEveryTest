package com.payevery.project.service;

import com.payevery.project.dtos.requests.ExpensesReqDTO;
import com.payevery.project.dtos.responses.ExpenseResponseDTO;
import com.payevery.project.dtos.responses.ExpenseWithVATResponseDTO;
import com.payevery.project.entitites.Expenses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ExpensesService {

    ResponseEntity<List<ExpenseResponseDTO>> getListOfAllExpenses();

    ResponseEntity<List<ExpenseWithVATResponseDTO>> getListOfAllExpensesWithVAT();

    ResponseEntity<Expenses> createNewExpenses(ExpensesReqDTO expensesReqDTO);

    ResponseEntity<List<Expenses>> findByExpensesDateAfter(LocalDate date);

}
