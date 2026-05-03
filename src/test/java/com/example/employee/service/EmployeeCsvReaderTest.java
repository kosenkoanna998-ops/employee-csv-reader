package com.example.employee.service;

import com.example.employee.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeCsvReaderTest {

    @Test
    void testReadEmployeesSuccess() {
        EmployeeCsvReader reader = new EmployeeCsvReader();
        List<Employee> employees = reader.readEmployees("employees.csv", ';');

        assertNotNull(employees);
        assertEquals(10, employees.size());
        assertEquals("Иванов Иван", employees.get(0).getName());
        assertEquals("IT", employees.get(0).getDepartment().getName());
        assertEquals(85000.0, employees.get(0).getSalary(), 0.01);
    }
}