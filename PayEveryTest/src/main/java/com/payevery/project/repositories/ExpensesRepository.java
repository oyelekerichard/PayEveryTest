package com.payevery.project.repositories;

import com.payevery.project.entitites.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    // Custom query
    @Query("SELECT e FROM Expenses e WHERE e.dateOfExpense >:date")
    List<Expenses> findByExpensesDateAfter(@Param("date") LocalDate date);
}
