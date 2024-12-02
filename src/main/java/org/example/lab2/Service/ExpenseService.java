package org.example.lab2.Service;

import org.example.lab2.Model.ExpenseEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    Iterable<ExpenseEntity> findAll();

    Optional<ExpenseEntity> findById(Integer id);

    ExpenseEntity save(ExpenseEntity expense);

    void update(ExpenseEntity expense, Double oldAmount);

    String delete(Integer id);

    Iterable<ExpenseEntity> findByUserId(Integer userId);

    Double totalSum(List<ExpenseEntity> expenses);

    List<ExpenseEntity> findByUserIdAndDateRange(Integer userId, Date startDate, Date endDate);

    List<ExpenseEntity> sortByCategory(List<ExpenseEntity> expenses);

}
