package org.example.lab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
