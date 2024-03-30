package com.payevery.project.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payevery.project.dtos.requests.ExpensesReqDTO;
import com.payevery.project.service.ExpensesService;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PayEveryController.class})
@ExtendWith(SpringExtension.class)
class PayEveryControllerTest {
    @MockBean
    private ExpensesService expensesService;

    @Autowired
    private PayEveryController payEveryController;

    /**
     * Method under test:
     * {@link PayEveryController#createNewExpenses(ExpensesReqDTO)}
     */
    @Test
    void testCreateNewExpenses() throws Exception {
        // Arrange
        when(expensesService.createNewExpenses(Mockito.<ExpensesReqDTO>any())).thenReturn(null);

        ExpensesReqDTO expensesReqDTO = new ExpensesReqDTO();
        expensesReqDTO.setExpenseDate(null);
        expensesReqDTO.setExpenseValue(10.0d);
        expensesReqDTO.setReasonForExpense("Just cause");
        expensesReqDTO.setVatOnExpense(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(expensesReqDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/expenses/createExpense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(payEveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test:
     * {@link PayEveryController#findByPublishedDateAfter(LocalDate)}
     */
    @Test
    void testFindByPublishedDateAfter() throws Exception {
        // Arrange
        when(expensesService.findByExpensesDateAfter(Mockito.<LocalDate>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/expenses/getExpenses/date-after/{date}", LocalDate.of(1970, 1, 1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(payEveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PayEveryController#getAllExpenses()}
     */
    @Test
    void testGetAllExpenses() throws Exception {
        // Arrange
        when(expensesService.getListOfAllExpenses()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/expenses/listOfExpenses");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(payEveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PayEveryController#getAllExpensesWithVAT()}
     */
    @Test
    void testGetAllExpensesWithVAT() throws Exception {
        // Arrange
        when(expensesService.getListOfAllExpensesWithVAT()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/expenses/listOfExpensesWithVAT");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(payEveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
