package com.payevery.project.controllers;

import com.payevery.project.dtos.requests.ExpensesReqDTO;
import com.payevery.project.dtos.responses.ExpenseResponseDTO;
import com.payevery.project.dtos.responses.ExpenseWithVATResponseDTO;
import com.payevery.project.entitites.Expenses;
import com.payevery.project.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class PayEveryController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/listOfExpenses")
    public ResponseEntity<List<ExpenseResponseDTO>> getAllExpenses() {
        return expensesService.getListOfAllExpenses();
    }

    @GetMapping("/listOfExpensesWithVAT")
    public ResponseEntity<List<ExpenseWithVATResponseDTO>> getAllExpensesWithVAT() {
        return expensesService.getListOfAllExpensesWithVAT();
    }

    @PostMapping("/createExpense")
    public ResponseEntity<Expenses> createNewExpenses(@RequestBody ExpensesReqDTO expensesReqDTO) {

        return expensesService.createNewExpenses(expensesReqDTO);
    }

    @GetMapping("/getExpenses/date-after/{date}")
    public ResponseEntity<List<Expenses>> findByPublishedDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return expensesService.findByExpensesDateAfter(date);
    }
}
