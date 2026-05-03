package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeCsvReader;

import java.util.List;

/**
 * Главный класс приложения.
 * Загружает сотрудников из CSV и выводит статистику.
 */
public class App {
    public static void main(String[] args) {
        System.out.println(" Запуск приложения чтения сотрудников...");

        EmployeeCsvReader reader = new EmployeeCsvReader();

        // Указываем путь к файлу (будет лежать в resources) и разделитель ';'
        List<Employee> employees = reader.readEmployees("employees.csv", ';');

        if (employees == null || employees.isEmpty()) {
            System.out.println(" Список сотрудников пуст. Проверьте путь к файлу.");
            return;
        }

        System.out.println(" Успешно загружено сотрудников: " + employees.size());
        System.out.println("\n--- Первые 5 записей ---");
        for (int i = 0; i < Math.min(5, employees.size()); i++) {
            System.out.println(employees.get(i));
        }
        System.out.println("------------------------------");
    }
}