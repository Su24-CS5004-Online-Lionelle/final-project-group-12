import org.junit.jupiter.api.Test;
import zoosys.model.Employee;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testEmployeeCreation() {
        Employee employee = new Employee("John Doe", "Zookeeper");
        assertEquals("John Doe", employee.getName());
        assertEquals("Zookeeper", employee.getRole());
    }

    @Test
    public void testGetEmployeeName() {
        Employee employee = new Employee("John Doe", "Zookeeper");
        employee.getName("Jane Doe");
        assertEquals("Jane Doe", employee.getName());
    }

    @Test
    public void testGetEmployeeRole() {
        Employee employee = new Employee("John Doe", "Zookeeper");
        employee.getRole("Veterinarian");
        assertEquals("Veterinarian", employee.getRole());
    }
}
