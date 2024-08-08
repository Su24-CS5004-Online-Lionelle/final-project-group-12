package zoosys.model;

/**
 * The Employee interface defines the basic structure and functionality 
 * for employees in the zoo management system.
 */
public interface Employee {
    /**
     * Gets the employee's name.
     * 
     * @return the name of the employee
     */
    String getName();

    /**
     * Gets the employee's role.
     * 
     * @return the role of the employee
     */
    String getRole();

    /**
     * Sets the shift for the employee.
     * 
     * @param shift the shift to be set
     */
    void setShift(String shift);

    /**
     * Gets the employee's shift.
     * 
     * @return the shift of the employee
     */
    String getShift();

    /**
     * Adds a responsibility to the employee's list of responsibilities.
     * 
     * @param responsibility the responsibility to add
     */
    void addResponsibility(String responsibility);


    /**
     * Gets the employee's list of responsibilities.
     * 
     * @return a string of responsibilities
     */
    String getResponsibilities();


    /**
     * Generate CSV representation of employee data.
     * 
     * @return the csv
     */
    String toCSV();
}
