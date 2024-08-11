package zoosys.controller;

import zoosys.model.*;

import java.util.List;
import java.util.Set;

/**
 * The EmployeeController class manages operations related to employees,
 * using the EmployeeManagement model. 
 */
public class EmployeeController {

    private EmployeeManagement employeeManagement;

    /**
     * Constructs an EmployeeController instance with the specified EmployeeManagement.
     * 
     * @param employeeManagement the EmployeeManagement instance to manage employee operations
     */
    public EmployeeController(EmployeeManagement employeeManagement) {
        this.employeeManagement = employeeManagement;
    }

    /**
     * Adds a new employee to the system.
     * 
     * @param name the name of the employee
     * @param role the role of the employee
     */
    public void addEmployee(String name, String role) {
        Employee employee = new EmployeeImpl(name, role);
        employeeManagement.addEmployee(employee);
    }

    /**
     * Removes an employee from the system by their name.
     * 
     * @param name the name of the employee to be removed
     */
    public void removeEmployee(String name) {
        employeeManagement.removeEmployee(name);
    }

    /**
     * Retrieves an employee by their name.
     * 
     * @param name the name of the employee to retrieve
     * @return the Employee object representing the employee, or null if not found
     */
    public Employee getEmployee(String name) {
        return employeeManagement.getEmployee(name);
    }

    /**
     * Updates an existing employee's details.
     * 
     * @param name the name of the employee to be updated
     * @param role the new role of the employee
     * @param shift the new shift of the employee
     * @param responsibilities the new responsibilities of the employee
     */
    public void updateEmployee(String name, String role, String shift, List<String> responsibilities) {
        Employee updatedEmployee = new EmployeeImpl(name, role);
        updatedEmployee.setShift(shift);
        for (String responsibility : responsibilities) {
            updatedEmployee.addResponsibility(responsibility);
        }
        employeeManagement.updateEmployee(name, updatedEmployee);
    }

    /**
     * Schedules a shift for an employee.
     * 
     * @param name the name of the employee
     * @param shift the shift to schedule for the employee
     */
    public void scheduleShift(String name, String shift) {
        employeeManagement.scheduleShift(name, shift);
    }

    /**
     * Assigns a responsibility to an employee.
     * 
     * @param name the name of the employee
     * @param responsibility the responsibility to assign to the employee
     */
    public void assignResponsibility(String name, String responsibility) {
        employeeManagement.assignResponsibility(name, responsibility);
    }

    /**
     * Retrieves a list of all employees.
     * 
     * @return a List of Employee objects representing all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeManagement.getAllEmployees();
    }

    /**
     * Retrieves a set of all employee names.
     * 
     * @return a Set of strings representing the names of all employees
     */
    public Set<String> getAllEmployeeNames() {
        return employeeManagement.getEmployeeNames();
    }

    /**
     * Prints the details of an employee.
     * 
     * @param name the name of the employee whose details are to be printed
     */
    public void printEmployeeDetails(String name) {
        employeeManagement.printEmployeeDetails(name);
    }
}
