package zoosys.model;

/**
 * An interface for the concept of the employee.
 */

public interface IEmployee {
    /**
     * Gets the employee ID.
     * @return employee ID
     */
    String getEmployeeID();
    
    /**
     * Gets the employee name.
     * @return employee name
     */
    String getName();
    
    /**
     * Gets the employee role.
     * @return employee role
     */
    String getRole();
    
    /**
     * Gets the employee contact information.
     * @return employee contact information
     */
    String getContactInfo();
    
    /**
     * Gets the hire date of the employee.
     * @return hire date
     */
    String getHireDate();
    
    /**
     * Gets the department of the employee.
     * @return department
     */
    String getDepartment();
    
    /**
     * Calculates the pay for the employee.
     * @return calculated pay
     */
    double calculatePay();
}
