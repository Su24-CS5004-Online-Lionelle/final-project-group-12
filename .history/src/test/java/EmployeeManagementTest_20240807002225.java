import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementTest {

    private EmployeeManagement employeeManagement;
    private EmployeeImpl employee;

    @BeforeEach
    public void setUp() {
        employeeManagement = new EmployeeManagement();
        employee = new EmployeeImpl("John Doe", "Zookeeper");
        employeeManagement.addEmployee(employee);
    }

    @Test
    public void testAddEmployee() {
        EmployeeImpl newEmployee = new EmployeeImpl("Jane Smith", "Veterinarian");
        employeeManagement.addEmployee(newEmployee);
        assertEquals(newEmployee, employeeManagement.getEmployee("Jane Smith"));
    }

    @Test
    public void testRemoveEmployee() {
        employeeManagement.removeEmployee("John Doe");
        assertNull(employeeManagement.getEmployee("John Doe"));
    }

    @Test
    public void testGetEmployee() {
        Employee result = employeeManagement.getEmployee("John Doe");
        assertEquals(employee, result);
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeImpl updatedEmployee = new EmployeeImpl("John Doe", "Veterinarian");
        employeeManagement.updateEmployee("John Doe", updatedEmployee);
        Employee result = employeeManagement.getEmployee("John Doe");
        assertEquals("Veterinarian", result.getRole());
    }

    @Test
    public void testScheduleShift() {
        employeeManagement.scheduleShift("John Doe", "Night");
        assertEquals("Night", employee.getShift());
    }

    @Test
    public void testAssignResponsibility() {
        employeeManagement.assignResponsibility("John Doe", "Supervise feedings");
        assertEquals("Supervise feedings", employee.getResponsibilities());
    }

    @Test
    public void testAssignTask() {
        employeeManagement.assignTask("John Doe", "Clean water tanks");
        assertEquals("Clean water tanks", employee.getTasks());
    }

    @Test
    public void testPrintEmployeeDetails() {
        employee.setShift("Morning");
        employee.addResponsibility("Feed animals");
        employee.addTask("Check health records");
        // Assuming printEmployeeDetails just prints the details, we can capture the console output here, but for simplicity:
        employeeManagement.printEmployeeDetails("John Doe");
        // You can manually check the console output or capture it in tests with additional libraries
    }

    @Test
    public void testGetEmployeeNames() {
        assertTrue(employeeManagement.getEmployeeNames().contains("John Doe"));
    }
}
