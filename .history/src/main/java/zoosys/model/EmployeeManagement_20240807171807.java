package zoosys.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    }

    /**
     * Adds an employee to the management system.
     * @param employee the employee to add
     */
    public void addEmployee(Employee employee) {
        employees.put(employee.getName(), employee);
        saveEmployeeToCSV(employee); // Save employee to CSV file
    }

    /**
     * Removes an employee from the management system.
     * @param name the name of the employee to remove
     */
    public void removeEmployee(String name) {
        employees.remove(name);
        removeEmployeeFromCSV(name); // Remove employee to CSV file
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
        updateEmployeeInCSV(updatedEmployee);
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
     * Saves the employee details to a CSV file.
     * @param employee the employee whose details are to be saved
     */
    private void saveEmployeeToCSV(Employee employee) {
        try (FileWriter writer = new FileWriter("resources/employees.csv", true)) {
            writer.append(employee.toCSV());
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes an employee from the CSV file by name.
     * @param name the name of the employee to remove
     */
    private void removeEmployeeFromCSV(String name) {
        List<String> updatedEmployees = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/employees.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(name + ",")) {
                    updatedEmployees.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (FileWriter writer = new FileWriter("resources/employees.csv", false)) {
            for (String employeeLine : updatedEmployees) {
                writer.write(employeeLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the employee details in the CSV file.
     * @param updatedEmployee the updated employee
     */
    private void updateEmployeeInCSV(Employee updatedEmployee) {
        removeEmployeeFromCSV(updatedEmployee.getName());
        saveEmployeeToCSV(updatedEmployee);
    }
}
