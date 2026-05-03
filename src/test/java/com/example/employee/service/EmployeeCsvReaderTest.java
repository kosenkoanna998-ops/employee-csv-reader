package com.example.employee.service;

import com.example.employee.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmployeeCsvReaderTest {

    @Test
    void testReadEmployeesSuccess() {
        EmployeeCsvReader reader = new EmployeeCsvReader();
        List<Employee> employees = reader.readEmployees("employees.csv", ';');

        assertNotNull(employees);
        assertTrue(employees.size() > 0);  // В файле много записей

        // Проверка первого сотрудника из foreign_names.csv
        // 28281;Aahan;Male;15.05.1970;I;4800
        Employee first = employees.get(0);
        assertEquals(28281, first.getId());
        assertEquals("Aahan", first.getName());
        assertEquals("Male", first.getGender());
        assertEquals("I", first.getDepartment().getName());
        assertEquals(4800.0, first.getSalary(), 0.01);
    }
}