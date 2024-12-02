package org.example.lab2.Service.Implementation;

import org.example.lab2.Model.IncomeEntity;
import org.example.lab2.Repository.IncomeRepository;
import org.example.lab2.Service.IncomeService;
import org.example.lab2.Service.Sort.SortItemsByCategory;
import org.example.lab2.Service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImplementation implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserService userService;
    private final SortItemsByCategory<IncomeEntity> categorySorter;

    public IncomeServiceImplementation(IncomeRepository incomeRepository, UserService userService, @Qualifier("sortIncomesByCategory")
    SortItemsByCategory<IncomeEntity> categorySorter) {
        this.incomeRepository = incomeRepository;
        this.userService = userService;
        this.categorySorter = categorySorter;
    }

    @Override
    public Iterable<IncomeEntity> findAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<IncomeEntity> findById(Integer id) {
        return incomeRepository.findById(id);
    }

    @Override
    public IncomeEntity save(IncomeEntity income) {
        userService.findById(income.getUserId()).map(user -> {
            user.setBudget(user.getBudget() + income.getAmount());
            userService.save(user);
            return null;
        });
        income.setCategory(Character.toUpperCase(income.getCategory().charAt(0))
                + income.getCategory().substring(1).toLowerCase());
        return incomeRepository.save(income);
    }

    @Override
    public void update(IncomeEntity income, Double oldAmount) {
        userService.findById(income.getUserId()).map(user -> {
            user.setBudget(user.getBudget() - oldAmount + income.getAmount());
            userService.save(user);
            return null;
        });
    }

    @Override
    public String delete(Integer id) {
        incomeRepository.deleteById(id);
        if (!incomeRepository.existsById(id)) {
            return "Income was deleted";
        } else {
            return "Income wasn't deleted";
        }
    }

    @Override
    public Iterable<IncomeEntity> findByUserId(Integer userId) {
        return incomeRepository.findByUserId(userId);
    }

    @Override
    public Double totalSum(List<IncomeEntity> incomes) {
        return incomes.stream().mapToDouble(IncomeEntity::getAmount).sum();
    }


    @Override
    public List<IncomeEntity> findByUserIdAndDateRange(Integer userId, Date startDate, Date endDate) {
        return incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
                .stream()
                .sorted((income1, income2) -> income2.getDate().compareTo(income1.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<IncomeEntity> sortByCategory(List<IncomeEntity> incomes) {
        return categorySorter.sortByCategory(incomes);
    }
}
