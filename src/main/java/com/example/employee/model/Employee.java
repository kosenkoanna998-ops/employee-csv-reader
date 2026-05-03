package com.example.employee.model;

import java.time.LocalDate;

/**
 * Класс сотрудника.
 * Содержит основные данные работника и ссылку на его подразделение.
 */
public class Employee {
    private int id;
    private String name;
    private String gender; // "Male" / "Female"
    private Department department; // Связь с другим объектом
    private double salary;
    private LocalDate birthDate;

    // Конструктор
    public Employee(int id, String name, String gender, Department department, double salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    // Геттеры (нужны для доступа к полям)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Сеттеры (могут пригодиться для тестов)
    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        // Форматируем дату красиво
        String deptName = (department != null) ? department.getName() : "Unknown";
        return "Employee{id=" + id + ", name='" + name + "', dept='" + deptName + "', salary=" + salary + "}";
    }
}