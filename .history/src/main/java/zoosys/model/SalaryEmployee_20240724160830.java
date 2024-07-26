package zoosys.model;

/**
 * HourlyEmployee extends the Employee interface.
 */
public class SalaryEmployee extends Employee {
    private String employeeID;
    private String name;
    private String role;
    private String contactInfo;
    private String hireDate;
    private String department;
    private double annualSalary;

    /**
     * Constructor for SalaryEmployee.
     * @param employeeID unique identifier for the employee
     * @param name employee name
     * @param role employee role
     * @param contactInfo employee contact information
     * @param hireDate date the employee was hired
     * @param department department the employee works in
     * @param annualSalary annual salary of the employee
     */
    public SalaryEmployee(String employeeID, String name, String role, String contactInfo, String hireDate, String department, double annualSalary) {
        this.employeeID = employeeID;
        this.name = name;
        this.role = role;
        this.contactInfo = contactInfo;
        this.hireDate = hireDate;
        this.department = department;
        this.annualSalary = annualSalary;
    }

    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public String getHireDate() {
        return hireDate;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public double calculatePay() {
        return annualSalary / 12; // Calculate monthly pay
    }
}
