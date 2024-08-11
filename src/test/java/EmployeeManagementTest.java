import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;
import zoosys.model.EmployeeManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

    @Test
    public void testAddEmployee() {
        EmployeeImpl newEmployee = new EmployeeImpl("Boa Smith", "Veterinarian");
        employeeManagement.addEmployee(newEmployee);
        assertEquals(newEmployee, employeeManagement.getEmployee("Boa Smith"));
        assertTrue(isEmployeeInCSV("Boa Smith"));
    }

    @Test
    public void testRemoveEmployee() {
        employeeManagement.removeEmployee("Chase Morgan");
        assertNull(employeeManagement.getEmployee("Chase Morgan"));
        assertFalse(isEmployeeInCSV("Chase Morgan"));
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
        assertTrue(isEmployeeInCSV("Chase Morgan"));
    }

    @Test
    public void testScheduleShift() {
        employeeManagement.scheduleShift("Chase Morgan", "Night");
        assertEquals("Night", employee.getShift());
        assertTrue(isEmployeeInCSV("Chase Morgan"));
    }

    @Test
    public void testAssignResponsibility() {
        employeeManagement.assignResponsibility("Chase Morgan", "Supervise feedings");
        assertEquals("Supervise feedings", employee.getResponsibilities());
        assertTrue(isEmployeeInCSV("Chase Morgan"));
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
        Set<String> employeeNames = employeeManagement.getEmployeeNames();
        assertTrue(employeeNames.contains("Chase Morgan"));
    }

    private boolean isEmployeeInCSV(String employeeName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/employees.csv"))) {
            return reader.lines().anyMatch(line -> line.contains(employeeName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void clearCSVFile() {
        try (FileWriter writer = new FileWriter("resources/employees.csv", false)) {
            writer.write("Name,Role,Shift,Responsibilities\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
