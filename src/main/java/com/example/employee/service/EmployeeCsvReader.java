package com.example.employee.service;

import com.example.employee.model.Department;
import com.example.employee.model.Employee;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для чтения сотрудников из CSV файла.
 */
public class EmployeeCsvReader {

    private final Map<String, Department> departmentCache = new HashMap<>();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Читает CSV файл и возвращает список сотрудников.
     */
    public List<Employee> readEmployees(String csvFilePath, char separator) {
        List<Employee> employees = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath)) {

            if (in == null) {
                throw new IllegalArgumentException("Файл не найден: " + csvFilePath);
            }

            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(separator)
                            .build())
                    .build();

            // Пропускаем заголовок
            reader.readNext();

            String[] nextLine;
            // Парсинг файла foreign_names.csv
            // Формат: id;name;gender;BirtDate;Division;Salary
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 6) continue;

                // Пропускаем заголовок
                if (nextLine[0].trim().equalsIgnoreCase("id")) continue;

                try {
                    // Парсим поля из CSV
                    int id = Integer.parseInt(nextLine[0].trim());
                    String name = nextLine[1].trim();
                    String gender = nextLine[2].trim();
                    LocalDate birthDate = LocalDate.parse(nextLine[3].trim(), dateFormatter);
                    String divisionCode = nextLine[4].trim();  // например "A", "B", "I"
                    double salary = Double.parseDouble(nextLine[5].trim().replace(',', '.'));

                    // Создаём/получаем подразделение (код из CSV = название)
                    // ВАЖНО: передаём только название, ID сгенерируется автоматически!
                    Department dept = departmentCache.computeIfAbsent(divisionCode,
                            code -> new Department(code));

                    // Создаём сотрудника
                    employees.add(new Employee(id, name, gender, dept, salary, birthDate));
                } catch (Exception e) {
                    System.err.println(" Ошибка парсинга: " + String.join(";", nextLine));
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}