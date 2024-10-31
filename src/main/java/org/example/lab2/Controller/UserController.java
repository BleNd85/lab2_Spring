package org.example.lab2.Controller;

import org.example.lab2.Model.ExpenseEntity;
import org.example.lab2.Model.IncomeEntity;
import org.example.lab2.Model.UserEntity;
import org.example.lab2.Service.ExpenseService;
import org.example.lab2.Service.IncomeService;
import org.example.lab2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser() {
        UserEntity user = new UserEntity()
                .builder()
                .name("Ivan")
                .budget(0.0)
                .build();
        UserEntity savedUser = userService.save(user);
        if (savedUser != null) {
            return ResponseEntity.ok(savedUser.getName());
        } else {
            return ResponseEntity.ok("error");
        }
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Integer id, Model model,
                              @RequestParam(required = false) Date startDate,
                              @RequestParam(required = false) Date endDate) {
        userService.findById(id).ifPresent(user -> {
            model.addAttribute("user", user);
        });
        if (startDate == null || endDate == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            startDate = new Date(calendar.getTimeInMillis());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            endDate = new Date(calendar.getTimeInMillis());
        }
        model.addAttribute("incomes", incomeService.findByUserIdAndDateRange(id, startDate, endDate));
        model.addAttribute("expenses", expenseService.findByUserIdAndDateRange(id, startDate, endDate));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "user-details";
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user, @PathVariable Integer id) {
        userService.findById(id)
                .map(oldUser -> {
                    oldUser.setName(user.getName());
                    oldUser.setBudget(user.getBudget());
                    userService.save(oldUser);
                    return null;
                });
        return ResponseEntity.ok("user is saved");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @GetMapping("/{id}/income/all")
    public ResponseEntity<?> getIncome(@PathVariable Integer id) {
        return ResponseEntity.ok(incomeService.findByUserId(id));
    }

    @GetMapping("/{id}/expense/new")
    public String newExpense(@PathVariable Integer id, Model model, @ModelAttribute ExpenseEntity expense) {
        model.addAttribute("expense", expense);
        userService.findById(id).ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "create-expense";
    }

    @PostMapping("/{id}/expense/new/create")
    public String createNewExpense(@PathVariable Integer id, @ModelAttribute ExpenseEntity expense) {
        ExpenseEntity savedExpense = new ExpenseEntity()
                .builder()
                .userId(id)
                .date(expense.getDate())
                .amount(expense.getAmount())
                .description(expense.getDescription())
                .category(expense.getCategory())
                .build();
        expenseService.save(savedExpense);
        return "redirect:/user/" + id;
    }

    @GetMapping("/{id}/income/new")
    public String newIncome(@PathVariable Integer id, Model model, @ModelAttribute IncomeEntity income) {
        model.addAttribute("income", income);
        userService.findById(id).ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "create-income";
    }

    @PostMapping("/{id}/income/new/create")
    public String createNewIncome(@PathVariable Integer id, @ModelAttribute IncomeEntity income) {
        IncomeEntity savedIncome = new IncomeEntity()
                .builder()
                .userId(id)
                .date(income.getDate())
                .amount(income.getAmount())
                .description(income.getDescription())
                .category(income.getCategory())
                .build();
        incomeService.save(savedIncome);
        return "redirect:/user/" + id;
    }

}
