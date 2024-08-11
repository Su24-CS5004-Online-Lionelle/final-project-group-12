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
import java.io.FileInputStream;

/**
 * The EmployeeManagement class manages the employees, including
 * adding, removing, scheduling shifts, and assigning responsibilities.
 */
public class EmployeeManagement {
    private Map<String, Employee> employees;

    /**
     * Constructs a new EmployeeManagement instance.
     * Initializes the employees map and reads the employee data from a CSV file.
     */
    public EmployeeManagement() {
        employees = new HashMap<>();
        readCSV(); // Read employees from CSV on initialization
    }

    /**
     * Reads the employees from a CSV file and populates the employee list.
     * The CSV file is expected to be in the resources directory.
     */
    public void readCSV() {
        List<String> lines;
        try {
            InputStream is = new FileInputStream("resources/employees.csv");
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
            
            // Handle cases where the CSV line doesn't have all required fields
            if (record.length < 4) {
                System.err.println("Skipping malformed line: " + line);
                continue; // Skip this line and move to the next one
            }
    
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
    
    /**
     * Adds an employee to the management system.
     * @param employee the employee to add
     * @return true if the employee was successfully added, false if the employee already exists
     */
    public boolean addEmployee(Employee employee) {
        if (employees.containsKey(employee.getName())) {
            return false; // Employee already exists, not added
        }
        employees.put(employee.getName(), employee);
        updateEmployeesToCSV();
        return true; // Employee successfully added
    }

    /**
     * Removes an employee from the management system.
     * @param name the name of the employee to remove
     * @return true if the employee was found and removed, false otherwise
     */
    public boolean removeEmployee(String name) {
        if (employees.containsKey(name)) {
            employees.remove(name);
            updateEmployeesToCSV();
            return true;
        } else {
            return false;
        }
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
     * Updates the employee's information.
     * @param name the name of the employee to update
     * @param updatedEmployee the updated employee information
     * @return true if the employee was found and updated, false otherwise
     */
    public boolean updateEmployee(String name, Employee updatedEmployee) {
        if (employees.containsKey(name)) {
            employees.put(name, updatedEmployee);
            updateEmployeesToCSV();
            return true;
        } else {
            System.out.println("Employee not found");
            return false;
        }
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
     * The CSV file is overwritten with the current list of employees.
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
