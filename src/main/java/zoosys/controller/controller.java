package zoosys.controller;

import java.util.List;
import java.util.Map;

import zoosys.model.*;

public class Controller {
    private AnimalManagement animalManagement;
    private EnclosureManagement enclosureManagement;
    private EmployeeManagement employeeManagement;
    private Visitor visitor;

    public Controller(AnimalManagement animalManagement, EnclosureManagement enclosureManagement, 
                      EmployeeManagement employeeManagement, Visitor visitor) {
        this.animalManagement = animalManagement;
        this.enclosureManagement = enclosureManagement;
        this.employeeManagement = employeeManagement;
        this.visitor = visitor;
    }

    // Animal Management Methods
    public void addAnimal(int id, String name, String type, int age, String medicalRecord) {
        animalManagement.addAnimal(id, name, type, age, medicalRecord);
    }

    public void removeAnimal(int id) {
        animalManagement.removeAnimal(id);
    }
  
    public void updateAnimal(int id, String name, String type, int age, String medicalRecord) {
        animalManagement.updateAnimal(id, name, type, age, medicalRecord);
    }
  
    public Animal getAnimal(int id) {
        return animalManagement.getAnimal(id);
    }

    public List<Animal> getAllAnimals() {
        return animalManagement.getAllAnimals();
    }

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


    // Enclosure Management Methods
    public void addEnclosure(int id, double size, double humidity, double temperature, 
                             double vegetationCoverage, int zoneCleanliness, int foodInTrough) {
        Enclosure enclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                                                 zoneCleanliness, foodInTrough);
        enclosureManagement.addEnclosure(enclosure);
    }
  
    public void removeEnclosure(int id) {
        enclosureManagement.removeEnclosure(id);
    }

    public void updateEnclosure(int id, double size, double humidity, double temperature, 
                                double vegetationCoverage, int zoneCleanliness, int foodInTrough) {
        Enclosure enclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                                                 zoneCleanliness, foodInTrough);
        enclosureManagement.updateEnclosure(id, enclosure);
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
   /**
    * Add animal to the enclosure.

    * @param animal name/type of the animal
    */
    public void addAnimalToEnclosure(Animal animal) {
        if (animal != null) {
            enclosureManagement.addAnimal(animal);
        }
    }

    /**
     * Remove animal from enclosure.
     * 
     * @param animal name of animal
     */
    public void removeAnimalFromEnclosure(Animal animal) {
        if (animal != null) {
            enclosureManagement.removeAnimal(animal);
        }
    }
    
    /**
     * Set the size of the enclosure.
     * 
     * @param size the size of the enclosure
     */
    public void setEnclosureSize(double size) {
        enclosureManagement.setEnclosureSize(size);
    }
      
    public Enclosure getEnclosure(int id) {
        return enclosureManagement.getEnclosure(id);
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosureManagement.getAllEnclosures();
    }

    // Employee Management Methods
    public void addEmployee(int id, String name, String position, double salary) {
        employeeManagement.addEmployee(id, name, position, salary);
    }

    public void removeEmployee(int id) {
        employeeManagement.removeEmployee(id);
    }

    public void updateEmployee(int id, String name, String position, double salary) {
        employeeManagement.updateEmployee(id, name, position, salary);
    }

    public Employee getEmployee(int id) {
        return employeeManagement.getEmployee(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeManagement.getAllEmployees();
    }

    // Visitor Management Methods
    public void addVisit(String date, int entryTime, String category, int duration, 
                         Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        visitor.addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
    }

    public List<Visit> getVisits() {
        return visitor.getVisits();
    }

    public Visit getVisit(int index) {
        return visitor.getVisit(index);
    }

    public int getTotalVisits() {
        return visitor.getTotalVisits();
    }

    public int getVisitsCountByDate(String date) {
        return visitor.getVisitsCountByDate(date);
    }

    public int getPeakHourByDate(String date) {
        return visitor.getPeakHourByDate(date);
    }

    public double getAverageAnimalFeedbackByDate(String date) {
        return visitor.getAverageAnimalFeedbackByDate(date);
    }

    public double getAverageCleanlinessFeedbackByDate(String date) {
        return visitor.getAverageCleanlinessFeedbackByDate(date);
    }

    public double getAveragePricingFeedbackByDate(String date) {
        return visitor.getAveragePricingFeedbackByDate(date);
    }

    public void setPricing(Map<String, Double> pricing) {
        visitor.setPricing(pricing);
    }

    public double getRevenueByDate(String date) {
        return visitor.getRevenueByDate(date);
    }

    public void readVisitorCSV() {
        visitor.readCSV();
    }
}
