package com.payevery.project.service.impl;

import com.payevery.project.configs.PayEveryConfiguration;
import com.payevery.project.dtos.requests.ExpensesReqDTO;
import com.payevery.project.dtos.responses.ExpenseResponseDTO;
import com.payevery.project.dtos.responses.ExpenseWithVATResponseDTO;
import com.payevery.project.entitites.Expenses;
import com.payevery.project.repositories.ExpensesRepository;
import com.payevery.project.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    PayEveryConfiguration payEveryConfiguration;

    @Override
    public ResponseEntity<List<ExpenseResponseDTO>> getListOfAllExpenses() {
        ExpenseResponseDTO responseDTO = null;

        List<ExpenseResponseDTO> expenseResponseDTOList = new ArrayList<>();
        try {

            List<Expenses> expenses = new ArrayList<>(expensesRepository.findAll());

            if (expenses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                for (Expenses expense : expenses) {
                    responseDTO = ExpenseResponseDTO.builder()
                            .amountSpent(expense.getAmount())
                            .dateOfExpense(expense.getDateOfExpense())
                            .reasonForExpenses(expense.getReasonForExpenditure())
                            .build();

                    expenseResponseDTOList.add(responseDTO);
                }
                return new ResponseEntity<>(expenseResponseDTOList, HttpStatus.OK);

            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<ExpenseWithVATResponseDTO>> getListOfAllExpensesWithVAT() {

        ExpenseWithVATResponseDTO responseDTO = null;

        List<ExpenseWithVATResponseDTO> expenseResponseDTOList = new ArrayList<>();

        try {
            List<Expenses> expenses = new ArrayList<>(expensesRepository.findAll());


            if (expenses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                for (Expenses expense : expenses) {
                    responseDTO = ExpenseWithVATResponseDTO.builder()
                            .amountSpent(expense.getAmount())
                            .dateOfExpense(expense.getDateOfExpense())
                            .reasonForExpenses(expense.getReasonForExpenditure())
                            .vatOnExpense(expense.getVat())
                            .build();

                    expenseResponseDTOList.add(responseDTO);
                }
                return new ResponseEntity<>(expenseResponseDTOList, HttpStatus.OK);

            }

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Expenses> createNewExpenses(ExpensesReqDTO expensesReqDTO) {
        try {
            Expenses newExpenses = expensesRepository.save(Expenses.builder().amount(expensesReqDTO.getExpenseValue())
                    .vat(expensesReqDTO.getExpenseValue() * Double.parseDouble(payEveryConfiguration.getVat()))
                    .dateOfExpense(expensesReqDTO.getExpenseDate()).reasonForExpenditure(expensesReqDTO.getReasonForExpense())
                    .build());

            return new ResponseEntity<>(newExpenses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Expenses>> findByExpensesDateAfter(LocalDate date) {
        try {
            List<Expenses> expenses = new ArrayList<>(expensesRepository.findByExpensesDateAfter(date));

            return new ResponseEntity<>(expenses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
