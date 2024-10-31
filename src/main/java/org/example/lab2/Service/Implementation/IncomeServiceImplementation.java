package org.example.lab2.Service.Implementation;

import org.example.lab2.Model.IncomeEntity;
import org.example.lab2.Model.UserEntity;
import org.example.lab2.Repository.IncomeRepository;
import org.example.lab2.Repository.UserRepository;
import org.example.lab2.Service.IncomeService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImplementation implements IncomeService {
    private IncomeRepository incomeRepository;

    public IncomeServiceImplementation(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Iterable<IncomeEntity> findAll() {
        return null;
    }

    @Override
    public Optional<IncomeEntity> findById(Integer id) {
        return incomeRepository.findById(id);
    }

    @Override
    public IncomeEntity save(IncomeEntity income) {
        return incomeRepository.save(income);
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
    public List<IncomeEntity> findByUserIdAndDateRange(Integer userId, Date startDate, Date endDate) {
        return incomeRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
                .stream()
                .sorted((income1, income2) -> income2.getDate().compareTo(income1.getDate()))
                .collect(Collectors.toList());
    }
}
