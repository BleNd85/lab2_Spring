package org.example.lab2.Service;

import org.example.lab2.Model.IncomeEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IncomeService {
    Iterable<IncomeEntity> findAll();

    Optional<IncomeEntity> findById(Integer id);

    IncomeEntity save(IncomeEntity income);

    String delete(Integer id);

    Iterable<IncomeEntity> findByUserId(Integer userId);

    List<IncomeEntity> findByUserIdAndDateRange(Integer userId, Date startDate, Date endDate);
}
