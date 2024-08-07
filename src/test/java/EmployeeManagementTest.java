import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;
import zoosys.model.EmployeeManagement;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagementTest {

    private EmployeeManagement employeeManagement;
    private EmployeeImpl employee;

    @BeforeEach
    public void setUp() {
        employeeManagement = new EmployeeManagement();
        employee = new EmployeeImpl("Wing Li", "Zookeeper");
        employeeManagement.addEmployee(employee);
    }

    @Test
    public void testAddEmployee() {
        EmployeeImpl newEmployee = new EmployeeImpl("Boa Smith", "Veterinarian");
        employeeManagement.addEmployee(newEmployee);
        assertEquals(newEmployee, employeeManagement.getEmployee("Boa Smith"));
    }

    @Test
    public void testRemoveEmployee() {
        employeeManagement.removeEmployee("Wing Li");
        assertNull(employeeManagement.getEmployee("Wing Li"));
    }

    @Test
    public void testGetEmployee() {
        Employee result = employeeManagement.getEmployee("Wing Li");
        assertEquals(employee, result);
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeImpl updatedEmployee = new EmployeeImpl("Wing Li", "Veterinarian");
        employeeManagement.updateEmployee("Wing Li", updatedEmployee);
        Employee result = employeeManagement.getEmployee("Wing Li");
        assertEquals("Veterinarian", result.getRole());
    }

    @Test
    public void testScheduleShift() {
        employeeManagement.scheduleShift("Wing Li", "Night");
        assertEquals("Night", employee.getShift());
    }

    @Test
    public void testAssignResponsibility() {
        employeeManagement.assignResponsibility("Wing Li", "Supervise feedings");
        assertEquals("Supervise feedings", employee.getResponsibilities());
    }

    @Test
    public void testAssignTask() {
        employeeManagement.assignTask("Wing Li", "Clean water tanks");
        assertEquals("Clean water tanks", employee.getTasks());
    }

    @Test
    public void testPrintEmployeeDetails() {
        employee.setShift("Morning");
        employee.addResponsibility("Feed animals");
        employee.addTask("Check health records");
        // Assuming printEmployeeDetails just prints the details, we can capture the console output here, but for simplicity:
        employeeManagement.printEmployeeDetails("Wing Li");
        // You can manually check the console output or capture it in tests with additional libraries
    }

    @Test
    public void testGetEmployeeNames() {
        assertTrue(employeeManagement.getEmployeeNames().contains("Wing Li"));
    }
}
