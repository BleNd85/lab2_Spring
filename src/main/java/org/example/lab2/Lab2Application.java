package org.example.lab2;

import org.example.lab2.Model.ExpenseEntity;
import org.example.lab2.Model.IncomeEntity;
import org.example.lab2.Service.Sort.Implementation.SortExpensesByCategoryImplementation;
import org.example.lab2.Service.Sort.Implementation.SortIncomesByCategoryImplementation;
import org.example.lab2.Service.Sort.SortItemsByCategory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*Тема: Веб-сайт ведення домашньої бухгалтерії
Сутності: витрати чи надходження грошей, покупка, категорія, дата
Актори: Користувач
Сценарії використання:
Користувач: Створення/редагування/видалення інформації про витрати чи
надходження, перегляд за вказаний період, та за категоріями
*/

@SpringBootApplication
public class Lab2Application {
    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    @Bean
    public SortItemsByCategory<ExpenseEntity> sortExpensesByCategory() {
        return new SortExpensesByCategoryImplementation();
    }

    @Bean
    public SortItemsByCategory<IncomeEntity> sortIncomesByCategory() {
        return new SortIncomesByCategoryImplementation();
    }
}
