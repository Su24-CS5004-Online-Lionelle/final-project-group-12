package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee "implements" the Employee interface.
 */
public class Employee implements IEmployee {
    private String name;
    private String role;
    private String shift;
    private List<String> responsibilities;
    private List<String> tasks;

    /**
     * Constructs a new Employee with the specified name and role.
     * @param name the name of the employee
     * @param role the role of the employee
     */
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
        this.responsibilities = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the name of the employee.
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the role of the employee.
     * @return the role of the employee
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the shift for the employee.
     * @param shift the shift to be set
     */
    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Gets the shift of the employee.
     * @return the shift of the employee
     */
    public String getShift() {
        return shift;
    }

    /**
     * Adds a responsibility to the employee's list of responsibilities.
     * @param responsibility the responsibility to add
     */
    public void addResponsibility(String responsibility) {
        responsibilities.add(responsibility);
    }

    /**
     * Adds a task to the employee's list of tasks.
     * @param task the task to add
     */
    public void addTask(String task) {
        tasks.add(task);
    }

    /**
     * Gets the employee's list of responsibilities.
     * @return a string of responsibilities
     */
    public String getResponsibilities() {
        return String.join(", ", responsibilities);
    }

    /**
     * Gets the employee's list of tasks.
     * @return a string of tasks
     */
    public String getTasks() {
        return String.join(", ", tasks);
    }
}