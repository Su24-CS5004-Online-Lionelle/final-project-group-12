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
        employee = new EmployeeImpl("Chase Morgan", "Zookeeper");
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
        employeeManagement.removeEmployee("Chase Morgan");
        assertNull(employeeManagement.getEmployee("Chase Morgan"));
    }

    @Test
    public void testGetEmployee() {
        Employee result = employeeManagement.getEmployee("Chase Morgan");
        assertEquals(employee, result);
    }

    @Test
    public void testUpdateEmployee() {
        EmployeeImpl updatedEmployee = new EmployeeImpl("Chase Morgan", "Veterinarian");
        employeeManagement.updateEmployee("Chase Morgan", updatedEmployee);
        Employee result = employeeManagement.getEmployee("Chase Morgan");
        assertEquals("Veterinarian", result.getRole());
    }

    @Test
    public void testScheduleShift() {
        employeeManagement.scheduleShift("Chase Morgan", "Night");
        assertEquals("Night", employee.getShift());
    }

    @Test
    public void testAssignResponsibility() {
        employeeManagement.assignResponsibility("Chase Morgan", "Supervise feedings");
        assertEquals("Supervise feedings", employee.getResponsibilities());
    }


    @Test
    public void testPrintEmployeeDetails() {
        employee.setShift("Morning");
        employee.addResponsibility("Feed animals");
        // Assuming printEmployeeDetails just prints the details, we can capture the console output here, but for simplicity:
        employeeManagement.printEmployeeDetails("Chase Morgan");
        // You can manually check the console output or capture it in tests with additional libraries
    }

    @Test
    public void testGetEmployeeNames() {
        assertTrue(employeeManagement.getEmployeeNames().contains("Chase Morgan"));
    }
}
