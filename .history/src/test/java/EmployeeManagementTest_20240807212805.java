import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zoosys.model.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementTest {
    private EmployeeManagement employeeManagement;
    private final String csvFilePath = "resources/employees.csv";

    @BeforeEach
    public void setUp() {
        employeeManagement = new EmployeeManagement();
        // Ensure the CSV file is cleared before each test
        clearCSVFile();
    }

    @AfterEach
    public void tearDown() {
        // Clean up after each test
        clearCSVFile();
    }

    @Test
    public void testAddEmployee() {
        Employee employee = new EmployeeImpl("John Doe", "Keeper");
        employee.setShift("Morning");
        employee.addResponsibility("Feeding");

        employeeManagement.addEmployee(employee);
        Employee retrieved = employeeManagement.getEmployee("John Doe");

        assertNotNull(retrieved);
        assertEquals("John Doe", retrieved.getName());
        assertEquals("Keeper", retrieved.getRole());
        assertEquals("Morning", retrieved.getShift());
        assertEquals("Feeding", retrieved.getResponsibilities());

        // Verify CSV update
        assertTrue(isEmployeeInCSV("John Doe"));
    }

    @Test
    public void testRemoveEmployee() {
        Employee employee = new EmployeeImpl("Jane Smith", "Guide");
        employee.setShift("Afternoon");
        employee.addResponsibility("Guiding Tours");

        employeeManagement.addEmployee(employee);
        employeeManagement.removeEmployee("Jane Smith");

        Employee retrieved = employeeManagement.getEmployee("Jane Smith");
        assertNull(retrieved);

        // Verify CSV update
        assertFalse(isEmployeeInCSV("Jane Smith"));
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new EmployeeImpl("Emily Brown", "Veterinarian");
        employee.setShift("Morning");
        employee.addResponsibility("Health Checks");

        employeeManagement.addEmployee(employee);

        Employee updatedEmployee = new EmployeeImpl("Emily Brown", "Senior Veterinarian");
        updatedEmployee.setShift("Afternoon");
        updatedEmployee.addResponsibility("Surgeries");

        employeeManagement.updateEmployee("Emily Brown", updatedEmployee);
        Employee retrieved = employeeManagement.getEmployee("Emily Brown");

        assertNotNull(retrieved);
        assertEquals("Emily Brown", retrieved.getName());
        assertEquals("Senior Veterinarian", retrieved.getRole());
        assertEquals("Afternoon", retrieved.getShift());
        assertEquals("Surgeries", retrieved.getResponsibilities());

        // Verify CSV update
        assertTrue(isEmployeeInCSV("Emily Brown"));
        assertFalse(isEmployeeInCSV("Emily Brown, Veterinarian"));
    }

    @Test
    public void testScheduleShift() {
        Employee employee = new EmployeeImpl("Mark Johnson", "Trainer");
        employeeManagement.addEmployee(employee);

        employeeManagement.scheduleShift("Mark Johnson", "Evening");
        Employee retrieved = employeeManagement.getEmployee("Mark Johnson");

        assertNotNull(retrieved);
        assertEquals("Evening", retrieved.getShift());

        // Verify CSV update
        assertTrue(isEmployeeInCSV("Mark Johnson"));
    }

    @Test
    public void testAssignResponsibility() {
        Employee employee = new EmployeeImpl("Sarah Davis", "Cleaner");
        employeeManagement.addEmployee(employee);

        employeeManagement.assignResponsibility("Sarah Davis", "Sanitizing");
        Employee retrieved = employeeManagement.getEmployee("Sarah Davis");

        assertNotNull(retrieved);
        assertEquals("Sanitizing", retrieved.getResponsibilities());

        // Verify CSV update
        assertTrue(isEmployeeInCSV("Sarah Davis"));
    }

    @Test
    public void testGetAllEmployees() {
        Employee employee1 = new EmployeeImpl("Tom White", "Manager");
        Employee employee2 = new EmployeeImpl("Alice Green", "Assistant");

        employeeManagement.addEmployee(employee1);
        employeeManagement.addEmployee(employee2);

        List<Employee> employees = employeeManagement.getAllEmployees();
        assertEquals(2, employees.size());
        assertTrue(employees.stream().anyMatch(e -> e.getName().equals("Tom White")));
        assertTrue(employees.stream().anyMatch(e -> e.getName().equals("Alice Green")));

        // Verify CSV update
        assertTrue(isEmployeeInCSV("Tom White"));
        assertTrue(isEmployeeInCSV("Alice Green"));
    }

    private void clearCSVFile() {
        try (FileWriter writer = new FileWriter(csvFilePath, false)) {
            writer.write("Name,Role,Shift,Responsibilities\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isEmployeeInCSV(String employeeName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            return reader.lines().anyMatch(line -> line.contains(employeeName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
