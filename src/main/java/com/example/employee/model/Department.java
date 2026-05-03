package com.example.employee.model;

/**
 * Класс подразделения.
 * ID генерируется автоматически при создании объекта.
 */
public class Department {
    private static int idCounter = 1; // Счётчик для генерации ID
    private final int id;
    private String name;

    public Department(String name) {
        this.id = idCounter++;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}