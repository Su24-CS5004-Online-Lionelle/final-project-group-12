package zoosys.controller;

import zoosys.model.*;
import java.util.List;
import java.util.Set;

/**
 *  The Controller class that manages interactions between model and the view.
 */
public class controller {
    private EmployeeManagement employeeManagement;
    private EnclosureManagement enclosureManagement;
    private Visitor visitorManagement;
    
    /**
     * Constructs a Controller class instance with the specified model managements.
     * @param employeeManagement employeemanagement the employee management
     * @param enclosureManagement enclosuremanagement the enclosure management
     * @param visitorManagement visitormanagement the visitor management
     */
    public controller(EmployeeManagement employeeManagement,
            EnclosureManagement enclosureManagement, Visitor visitorManagement) {
        this.employeeManagement = new EmployeeManagement();
        this.enclosureManagement = enclosureManagement;
        this.visitorManagement = visitorManagement;
    }

    //Employee
    /**
     * Adds a new employee into the system.
     * 
     * @param name the name of the employee
     * @param role the role of the employee
     * @param shift the shift assigned to the employee
     * @param responsibilities the responsibilities of the employee, provided as a comma-separated string
     * @return true if the employee was successfully added, false if the employee already exists or the input is invalid
     */
    public boolean addEmployee(String name, String role, String shift, String responsibilities) {
        if (name != null && !name.isEmpty() && role != null && !role.isEmpty()) {
            Employee employee = new EmployeeImpl(name, role);
            employee.setShift(shift);
            for (String responsibility : responsibilities.split(",")) {
                employee.addResponsibility(responsibility.trim());
            }
            return employeeManagement.addEmployee(employee);
        }
        return false;
    }

    /**
     * Retrieves the EmployeeManagement instance.
     * 
     * @return the EmployeeManagement instance that manages employees
     */
    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    /**
     * Removes an employee from the management system.
     * 
     * @param name the name of the employee to be removed
     * @return true if the employee was successfully removed, false if the employee was not found or the input is invalid
     */
    public boolean removeEmployee(String name) {
        if (name != null && !name.isEmpty()) {
            return employeeManagement.removeEmployee(name);
        }
        return false;
    }

    /**
    * Updates an employee in the management system.
    * 
    * @param name the name of the employee to be updated
    * @param updatedEmployee the updated employee data
    * @return true if the employee was successfully updated, false if the employee was not found or the input is invalid
    */
    public boolean updateEmployee(String name, Employee updatedEmployee) {
        if (name != null && !name.isEmpty() && updatedEmployee != null) {
            return employeeManagement.updateEmployee(name, updatedEmployee);
        }
        return false;
    }

    /**
     * Get the employee from the management system.
     * 
     * @param name the name of the employee to retrieve
     * @return the employee with the name
     */
    public Employee getEmployee(String name) {
        return employeeManagement.getEmployee(name);
    }

    /**
     * Schedule shifts for the employee.
     * 
     * @param name the name of the employee
     * @param shift the shift to assign
     */
     public void scheduleShift(String name, String shift) {
        employeeManagement.scheduleShift(name, shift);
    }

    /**
     * Assign the responsibility for the employee.
     * 
     * @param name the name of the employee
     * @param responsibility the responsibility of the employee
     */
    public void assignResponsibility(String name, String responsibility) {
        employeeManagement.assignResponsibility(name, responsibility);
    }

    /**
     * Printe all the detials of the employees.
     * 
     * @param name the name of the employees
     */
    public void printEmployeeDetails(String name) {
        employeeManagement.printEmployeeDetails(name);
    }
    
    // Enclosures
    public boolean addEnclosure(Enclosure enclosure) {
        return enclosureManagement.addEnclosure(enclosure);
    }

    public boolean removeEnclosure(EnclosureType type) {
        return enclosureManagement.removeEnclosure(type);
    }
    
    public boolean updateEnclosure(EnclosureType type, Enclosure updatedEnclosure) {
        return enclosureManagement.updateEnclosure(type, updatedEnclosure);
    }

    public Enclosure getEnclosure(EnclosureType type) {
        return enclosureManagement.getEnclosure(type);
    }

    public void printEnclosureDetails(EnclosureType type, double size) {
        enclosureManagement.printEnclosureDetails(type, size);
    }

    public Set<EnclosureType> getEnclosureType() {
        return enclosureManagement.getEnclosureTypes();
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosureManagement.getAllEnclosures();
    }


    // Visitor
    /**
     * Add a visit record for a visitor.
     * 
     * @param date the date the visitor would like to visit
     * @param entryTime the entry time on the visiting date
     * @param category the category of the ticket
     * @param duration the duration of the visit
     * @param animalFeedback the feedback on animals
     * @param cleanlinessFeedback the feedback on zoo cleanliness 
     * @param pricingFeedback the pricing feedback
     */
    public void addVisit(String date, int entryTime, String category, int duration, 
            Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        visitorManagement.addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
    }

    /**
     * Get the list of the visits.
     * 
     * @return the list of the visits
     */
    public List<Visit> getVisits() {
        return visitorManagement.getVisits();
    }

    /**
     * Get the total number of visits.
     * 
     * @return the total of visits.
     */
    public int getTotalVisits() {
        return visitorManagement.getTotalVisits();
    }
    
    /**
     * Get the number of visits on a specific date.
     * 
     * @param date the date of the visits
     * @return the number visits on the specified date
     */
    public int getVisitsCountByDate(String date) {
        return visitorManagement.getVisitsCountByDate(date);
    }

    /**
     * Get the peak hour count by date.
     * 
     * @param date the date for which to get the peak hour count
     * @return the peak hour count on the specified date
     */
    public int getPeakHourByDate(String date) {
        return visitorManagement.getPeakHourByDate(date);
    }

    /**
     * Get the average animal feedback by date.
     * 
     * @param date the date for which to get the average animal feedback
     * @return the average animal feedback on the specified date
     */
    public double getAverageAnimalFeedbackByDate(String date) {
        return visitorManagement.getAverageAnimalFeedbackByDate(date);
    }
}
