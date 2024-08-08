package zoosys.model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The EmployeeManagement class manages the employees, including
 * adding, removing, scheduling shifts, and assigning responsibilities.
 */
public class EmployeeManagement {
    private Map<String, Employee> employees;

    /**
     * Constructs a new EmployeeManagement instance.
     */
    public EmployeeManagement() {
        employees = new HashMap<>();
        readCSV(); // Read employees from CSV on initialization
    }


    /**
     * Reads the employees from a CSV file and populates the employee list.
     */
    public void readCSV() {
        List<String> lines;
        try {
            InputStream is = getClass().getResourceAsStream("resources/employees.csv");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        if (lines == null || lines.isEmpty()) {
            return;
        }

        // Process header and remove it from lines
        lines.remove(0);

        // Process each line and add employees
        for (String line : lines) {
            String[] record = line.split(",");
            String name = record[0];
            String role = record[1];
            String shift = record[2];
            String responsibilities = record[3];
            Employee employee = new EmployeeImpl(name, role);
            employee.setShift(shift);
            for (String responsibility : responsibilities.split(",")) {
                employee.addResponsibility(responsibility.trim());
            }
            employees.put(name, employee);
        }
    }
}

    /**
     * Adds an employee to the management system.
     * @param employee the employee to add
     */
    public void addEmployee(Employee employee) {
        employees.put(employee.getName(), employee);
        updateEmployeesToCSV();
    }

    /**
     * Removes an employee from the management system.
     * @param name the name of the employee to remove
     */
    public void removeEmployee(String name) {
        employees.remove(name);
        updateEmployeesToCSV();
    }

    /**
     * Retrieves an employee by name.
     * @param name the name of the employee to retrieve
     * @return the employee object, or null if not found
     */
    public Employee getEmployee(String name) {
        return employees.get(name);
    }

    /**
     * Updates the employee's info.
     * 
     * @param name name of the employee
     * @param updatedEmployee updated employee info.
     */
    public void updateEmployee(String name, Employee updatedEmployee) {
        employees.put(name, updatedEmployee);
        updateEmployeesToCSV();
    }

    /**
     * Schedules a shift for an employee.
     * @param name the name of the employee
     * @param shift the shift to schedule
     */
    public void scheduleShift(String name, String shift) {
        Employee employee = employees.get(name);
        if (employee != null) {
            employee.setShift(shift);
            updateEmployeesToCSV();
        } else {
            System.out.println("Employee not found");
        }
    }

    /**
     * Assigns a responsibility to an employee.
     * @param name the name of the employee
     * @param responsibility the responsibility to assign
     */
    public void assignResponsibility(String name, String responsibility) {
        Employee employee = employees.get(name);
        if (employee != null) {
            employee.addResponsibility(responsibility);
            updateEmployeesToCSV();
        } else {
            System.out.println("Employee not found");
        }
    }

    /**
     * Prints the details of an employee.
     * @param name the name of the employee
     */
    public void printEmployeeDetails(String name) {
        Employee employee = employees.get(name);
        if (employee != null) {
            System.out.println("Name: " + employee.getName());
            System.out.println("Role: " + employee.getRole());
            System.out.println("Shift: " + employee.getShift());
            System.out.println("Responsibilities: " + employee.getResponsibilities());
        } else {
            System.out.println("Employee not found");
        }
    }

    /**
     * Retrieves the names of all employees.
     * @return a set of employee names
     */
    public Set<String> getEmployeeNames() {
        return employees.keySet();
    }

    /**
     * Returns a list of all employees.
     * @return a list of all employees
     */
    public List<Employee> getAllEmployees() {
        return employees.values().stream().collect(Collectors.toList());
    }

    /**
     * Updates the CSV file with all employee details.
     */
    private void updateEmployeesToCSV() {
        try (FileWriter writer = new FileWriter("resources/employees.csv", false)) {
            writer.append("Name,Role,Shift,Responsibilities");
            writer.append("\n");
            for (String name : getEmployeeNames()) {
                Employee e = employees.get(name);
                writer.append(e.toCSV());
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}