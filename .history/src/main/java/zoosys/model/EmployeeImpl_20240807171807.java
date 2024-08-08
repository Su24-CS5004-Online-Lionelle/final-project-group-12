package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * EmployeeImpl implements the Employee interface.
 */
public class EmployeeImpl implements Employee {
    
    /**
     * The name of the employee.
     */
    private String name;

    /**
     * The role of the employee.
     */
    private String role;

    /**
     * The shift of the employee.
     */
    private String shift;

    /**
     * The list of responsibilities assigned to the employee.
     */
    private List<String> responsibilities;


    /**
     * Constructs a new EmployeeImpl with the specified name and role.
     * 
     * @param name the name of the employee
     * @param role the role of the employee
     */
    public EmployeeImpl(String name, String role) {
        this.name = name;
        this.role = role;
        this.responsibilities = new ArrayList<>();
    }

    /**
     * Gets the employee's name.
     * 
     * @return the name of the employee
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the employee's role.
     * 
     * @return the role of the employee
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Sets the shift for the employee.
     * 
     * @param shift the shift to be set
     */
    @Override
    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Gets the employee's shift.
     * 
     * @return the shift of the employee
     */
    @Override
    public String getShift() {
        return shift;
    }

    /**
     * Adds a responsibility to the employee's list of responsibilities.
     * 
     * @param responsibility the responsibility to add
     */
    @Override
    public void addResponsibility(String responsibility) {
        responsibilities.add(responsibility);
    }

    /**
     * Gets the employee's list of responsibilities.
     * 
     * @return a string of responsibilities
     */
    @Override
    public String getResponsibilities() {
        return String.join(", ", responsibilities);
    }


    /**
     * Generate CSV representation of employee data.
     * 
     * @return the csv
     */
    public String toCSV() {
        return name + "," + role + "," + shift + "," + getResponsibilities();
    }
}
