package org.example.lab2.Service.Sort.Implementation;

import org.example.lab2.Model.ExpenseEntity;
import org.example.lab2.Model.IncomeEntity;
import org.example.lab2.Service.Sort.SortItemsByCategory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortIncomesByCategoryImplementation implements SortItemsByCategory<IncomeEntity> {

    @Override
    public List<IncomeEntity> sortByCategory(List<IncomeEntity> incomes) {
        return incomes.stream()
                .sorted(Comparator.comparing(IncomeEntity::getCategory))
                .collect(Collectors.toList());
    }
}
