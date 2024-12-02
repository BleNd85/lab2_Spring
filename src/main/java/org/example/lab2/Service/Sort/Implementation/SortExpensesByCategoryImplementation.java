package org.example.lab2.Service.Sort.Implementation;

import org.example.lab2.Model.ExpenseEntity;
import org.example.lab2.Service.Sort.SortItemsByCategory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortExpensesByCategoryImplementation implements SortItemsByCategory<ExpenseEntity> {

    @Override
    public List<ExpenseEntity> sortByCategory(List<ExpenseEntity> expenses) {
        return expenses.stream()
                .sorted(Comparator.comparing(ExpenseEntity::getCategory))
                .collect(Collectors.toList());
    }
}
