/**
 * Employee "implements" the Employee interface.
 */
public abstract class Employee implements IEmployee {
    private String employeeID;
    private String name;
    private String role;
    private String contactInfo;
    private String hireDate;
    private String department;

    /**
     * Constructor for Employee.
     * @param employeeID unique identifier for the employee
     * @param name employee name
     * @param role employee role
     * @param contactInfo employee contact information
     * @param hireDate date the employee was hired
     * @param department department the employee works in
     */
    public Employee(String employeeID, String name, String role, String contactInfo, String hireDate, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
        this.hireDate = hireDate;
        this.department = department;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getHireDate() {
        return hireDate;
    }

    public String getDepartment() {
        return department;
    }

    public abstract double calculatePay(); // This method will be overridden by subclasses
}
