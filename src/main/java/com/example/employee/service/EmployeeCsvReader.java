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
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 6) continue;

                try {
                    int id = Integer.parseInt(nextLine[0].trim());
                    String name = nextLine[1].trim();
                    String gender = nextLine[2].trim();
                    String deptName = nextLine[3].trim();
                    double salary = Double.parseDouble(nextLine[4].trim().replace(',', '.'));
                    LocalDate birthDate = LocalDate.parse(nextLine[5].trim(), dateFormatter);

                    Department dept = departmentCache.computeIfAbsent(deptName, Department::new);

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