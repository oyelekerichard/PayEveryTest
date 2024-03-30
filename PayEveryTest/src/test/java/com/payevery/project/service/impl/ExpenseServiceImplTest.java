package com.payevery.project.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.payevery.project.dtos.requests.ExpensesReqDTO;
import com.payevery.project.entitites.Expenses;
import com.payevery.project.repositories.ExpensesRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExpenseServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ExpenseServiceImplTest {
    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @MockBean
    private ExpensesRepository expensesRepository;

    /**
     * Method under test:
     * {@link ExpenseServiceImpl#createNewExpenses(ExpensesReqDTO)}
     */
    @Test
    void testCreateNewExpenses() {
        // Arrange and Act
        ResponseEntity<Expenses> actualCreateNewExpensesResult = expenseServiceImpl.createNewExpenses(new ExpensesReqDTO());

        // Assert
        assertNull(actualCreateNewExpensesResult.getBody());
        assertEquals(500, actualCreateNewExpensesResult.getStatusCodeValue());
        assertTrue(actualCreateNewExpensesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExpenseServiceImpl#createNewExpenses(ExpensesReqDTO)}
     */
    @Test
    void testCreateNewExpenses2() {
        // Arrange
        ExpensesReqDTO.ExpensesReqDTOBuilder builderResult = ExpensesReqDTO.builder();
        ExpensesReqDTO expensesReqDTO = builderResult.expenseDate(LocalDate.of(1970, 1, 1))
                .expenseValue(10.0d)
                .reasonForExpense("Just cause")
                .vatOnExpense(10.0d)
                .build();

        // Act
        ResponseEntity<Expenses> actualCreateNewExpensesResult = expenseServiceImpl.createNewExpenses(expensesReqDTO);

        // Assert
        assertNull(actualCreateNewExpensesResult.getBody());
        assertEquals(500, actualCreateNewExpensesResult.getStatusCodeValue());
        assertTrue(actualCreateNewExpensesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExpenseServiceImpl#createNewExpenses(ExpensesReqDTO)}
     */
    @Test
    void testCreateNewExpenses3() {
        // Arrange and Act
        ResponseEntity<Expenses> actualCreateNewExpensesResult = expenseServiceImpl.createNewExpenses(null);

        // Assert
        assertNull(actualCreateNewExpensesResult.getBody());
        assertEquals(500, actualCreateNewExpensesResult.getStatusCodeValue());
        assertTrue(actualCreateNewExpensesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExpenseServiceImpl#createNewExpenses(ExpensesReqDTO)}
     */
    @Test
    void testCreateNewExpenses4() {
        // Arrange
        ExpensesReqDTO expensesReqDTO = mock(ExpensesReqDTO.class);
        when(expensesReqDTO.getExpenseValue()).thenReturn(10.0d);

        // Act
        ResponseEntity<Expenses> actualCreateNewExpensesResult = expenseServiceImpl.createNewExpenses(expensesReqDTO);

        // Assert
        verify(expensesReqDTO, atLeast(1)).getExpenseValue();
        assertNull(actualCreateNewExpensesResult.getBody());
        assertEquals(500, actualCreateNewExpensesResult.getStatusCodeValue());
        assertTrue(actualCreateNewExpensesResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExpenseServiceImpl#findByExpensesDateAfter(LocalDate)}
     */
    @Test
    void testFindByExpensesDateAfter() {
        // Arrange
        ArrayList<Expenses> expensesList = new ArrayList<>();
        when(expensesRepository.findByExpensesDateAfter(Mockito.<LocalDate>any())).thenReturn(expensesList);

        // Act
        ResponseEntity<List<Expenses>> actualFindByExpensesDateAfterResult = expenseServiceImpl
                .findByExpensesDateAfter(LocalDate.of(1970, 1, 1));

        // Assert
        verify(expensesRepository).findByExpensesDateAfter(Mockito.<LocalDate>any());
        assertEquals(200, actualFindByExpensesDateAfterResult.getStatusCodeValue());
        assertTrue(actualFindByExpensesDateAfterResult.getHeaders().isEmpty());
        assertEquals(expensesList, actualFindByExpensesDateAfterResult.getBody());
    }
}
