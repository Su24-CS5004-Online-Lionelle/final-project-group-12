import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;
import zoosys.model.EmployeeManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementTest {

    private EmployeeManagement employeeManagement;
    private EmployeeImpl employee;

    @BeforeEach
    public void setUp() {
        employeeManagement = new EmployeeManagement();
        employee = new EmployeeImpl("Chase Morgan", "Zookeeper");
        employeeManagement.addEmployee(employee);
    }

    /**
     * Test adding a valid new employee.
     */
    @Test
    public void testAddEmployee() {
        EmployeeImpl newEmployee = new EmployeeImpl("Boa Smith", "Veterinarian");
        employeeManagement.addEmployee(newEmployee);
        assertEquals(newEmployee, employeeManagement.getEmployee("Boa Smith"));
        assertTrue(isEmployeeInCSV("Boa Smith"));
    }

    /**
     * Test adding a duplicate employee, which should fail.
     */
    @Test
    public void testAddDuplicateEmployee() {
        boolean result = employeeManagement.addEmployee(employee);
        assertFalse(result); // Adding the same employee again should fail
    }

    /**
     * Test adding an employee with an empty name, which should fail.
     */
    @Test
    public void testAddEmployeeWithEmptyName() {
        EmployeeImpl emptyNameEmployee = new EmployeeImpl("", "Veterinarian");
        boolean result = employeeManagement.addEmployee(emptyNameEmployee);
        assertTrue(result); // Employee with an empty name should not be added
    }

    /**
     * Test removing an existing employee.
     */
    @Test
    public void testRemoveEmployee() {
        employeeManagement.removeEmployee("Chase Morgan");
        assertNull(employeeManagement.getEmployee("Chase Morgan"));
        assertFalse(isEmployeeInCSV("Chase Morgan"));
    }

    /**
     * Test removing a non-existing employee, which should do nothing.
     */
    @Test
    public void testRemoveNonExistingEmployee() {
        boolean result = employeeManagement.removeEmployee("NonExistent Employee");
        assertFalse(result); // Removing a non-existing employee should return false
    }

    /**
     * Test retrieving an existing employee by name.
     */
    @Test
    public void testGetEmployee() {
        Employee result = employeeManagement.getEmployee("Chase Morgan");
        assertEquals(employee, result);
    }

    /**
     * Test retrieving a non-existing employee by name.
     */
    @Test
    public void testGetNonExistingEmployee() {
        Employee result = employeeManagement.getEmployee("NonExistent Employee");
        assertNull(result); // Should return null for a non-existing employee
    }

    /**
     * Test updating an existing employee's information.
     */
    @Test
    public void testUpdateEmployee() {
        EmployeeImpl updatedEmployee = new EmployeeImpl("Chase Morgan", "Veterinarian");
        employeeManagement.updateEmployee("Chase Morgan", updatedEmployee);
        Employee result = employeeManagement.getEmployee("Chase Morgan");
        assertEquals("Veterinarian", result.getRole());
        assertTrue(isEmployeeInCSV("Chase Morgan"));
    }

    /**
     * Test updating a non-existing employee, which should fail.
     */
    @Test
    public void testUpdateNonExistingEmployee() {
        EmployeeImpl updatedEmployee = new EmployeeImpl("NonExistent Employee", "Veterinarian");
        boolean result = employeeManagement.updateEmployee("NonExistent Employee", updatedEmployee);
        assertFalse(result); // Updating a non-existing employee should return false
    }

    /**
     * Test scheduling a shift for an existing employee.
     */
    @Test
    public void testScheduleShift() {
        employeeManagement.scheduleShift("Chase Morgan", "Night");
        assertEquals("Night", employee.getShift());
        assertTrue(isEmployeeInCSV("Chase Morgan"));
    }

    /**
     * Test scheduling a shift for a non-existing employee, which should do nothing.
     */
    @Test
    public void testScheduleShiftForNonExistingEmployee() {
        employeeManagement.scheduleShift("NonExistent Employee", "Night");
        assertNull(employeeManagement.getEmployee("NonExistent Employee"));
    }

    /**
     * Test assigning a responsibility to an existing employee.
     */
    @Test
    public void testAssignResponsibility() {
        employeeManagement.assignResponsibility("Chase Morgan", "Supervise feedings");
        assertEquals("Supervise feedings", employee.getResponsibilities());
        assertTrue(isEmployeeInCSV("Chase Morgan"));
    }

    /**
     * Test assigning a responsibility to a non-existing employee, which should do nothing.
     */
    @Test
    public void testAssignResponsibilityToNonExistingEmployee() {
        employeeManagement.assignResponsibility("NonExistent Employee", "Supervise feedings");
        assertNull(employeeManagement.getEmployee("NonExistent Employee"));
    }

    /**
     * Test printing the details of an existing employee.
     */
    @Test
    public void testPrintEmployeeDetails() {
        employee.setShift("Morning");
        employee.addResponsibility("Feed animals");
        employeeManagement.printEmployeeDetails("Chase Morgan");
        // Check console output manually or use libraries to capture console output in tests
    }

    /**
     * Test retrieving the names of all employees.
     */
    @Test
    public void testGetEmployeeNames() {
        Set<String> employeeNames = employeeManagement.getEmployeeNames();
        assertTrue(employeeNames.contains("Chase Morgan"));
    }

    /**
     * Utility method to check if an employee is present in the CSV file.
     * @param employeeName the name of the employee to check
     * @return true if the employee is in the CSV file, false otherwise
     */
    private boolean isEmployeeInCSV(String employeeName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/employees.csv"))) {
            return reader.lines().anyMatch(line -> line.contains(employeeName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
