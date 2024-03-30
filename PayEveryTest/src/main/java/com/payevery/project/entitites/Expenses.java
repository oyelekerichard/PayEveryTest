package com.payevery.project.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses_table")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "date_of_transaction")
    private LocalDate dateOfExpense;

    @Column(name = "amount_spent")
    private Double amount;

    @Column(name = "vat")
    private Double vat;

    @Column(name = "reason")
    private String reasonForExpenditure;
}