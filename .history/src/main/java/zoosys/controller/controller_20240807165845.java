package zoosys.controller;

import zoosys.model.Animal;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;
import zoosys.model.EmployeeManagement;
import zoosys.model.Enclosures;
import zoosys.model.EnclosureType;
import zoosys.model.Visitor;
import zoosys.model.Visit;

import java.util.List;
import java.util.Map;

/**
 *  The Controller class that manages interactions between model and the view.
 */
public class controller {
    private EmployeeManagement employeeManagement;
    private Enclosures enclosureManagement;
    private Visitor visitorManagement;
    
    /**
     * Constructs a Controller class instance with the specified model managements.
     * @param employeeManagement employeemanagement the employee management
     * @param enclosureManagement enclosuremanagement the enclosure management
     * @param visitorManagement visitormanagement the visitor management
     */
    public controller(EmployeeManagement employeeManagement,
            Enclosures enclosureManagement, Visitor visitorManagement) {
        employeeManagement = new EmployeeManagement();
        this.enclosureManagement = enclosureManagement;
        this.visitorManagement = visitorManagement;
    }

    // Animal
    /**
     * Add new animal to the enclosure
     * 
     * @param animal the animal to be added
     */
    public void addAnimal(Animal animal) {
        enclosureManagement.addAnimal(animal);
    }

    /**
     * Remove existed animal from the system.
     * 
     * @param id the ID of the animal to be removed
     */
    public void removeAnimal(int id) {
        Animal animal = findAnimalById(id);
        if (animal != null) {
            enclosureManagement.removeAnimal(animal);
        }
    }

    /**
     * Edit an existing animal in the system.
     * 
     * @param updatedAnimal the updated animal data
     */
    public void editAnimal(Animal updatedAnimal) {
        Animal animal = findAnimalById(updatedAnimal.getAnimal_id());
        if (animal != null) {
            enclosureManagement.removeAnimal(animal);
            enclosureManagement.addAnimal(updatedAnimal);
        }
    }

    /**
     * Find an animal by its ID.
     * 
     * @param id the ID of the animal
     * @return the animal with the specified ID, or null if not found
     */
    private Animal findAnimalById(int id) {
        for (Animal animal : enclosureManagement.getAnimals()) {
            if (animal.getAnimal_id() == id) {
                return animal;
            }
        }
        return null;
    }

    // Employee
    /**
     * Add new employee into the system.
     * 
     * @param employee the employee to be added
     */
    public void addEmployee(String name, String role, String shift, String responsibilities) {
        Employee employee = new EmployeeImpl(name, role);
        employee.setShift(shift);
        for (String responsibility : responsibilities.split(",")) {
            employee.addResponsibility(responsibility.trim());
        }
        for (String task : tasks.split(",")) {
            employee.addTask(task.trim());
        }
        employeeManagement.addEmployee(employee);
    }

    public EmployeeManagement getEmployeeManagement() {
        return employeeManagement;
    }

    /**
     * Remove the employee from the management system.
     * 
     * @param name the name of the employee to be removed
     */
    public void removeEmployee(String name) {
        employeeManagement.removeEmployee(name);
    }

    /**
     * Update the employee in the management system.
     * 
     * @param name the name of the employee to be updated
     * @param updatedEmployee the updated employee data
     */
    public void updateEmployee(String name, Employee updatedEmployee) {
        employeeManagement.updateEmployee(name, updatedEmployee);
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
     * Assign task to the employee.
     * 
     * @param name the name of the employee
     * @param task the task assigned to the employee
     */
    public void assignTask(String name, String task) {
        employeeManagement.assignTask(name, task);
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
        enclosureManagement.addAnimal(animal);
    }

    /**
     * Remove animal from enclosure.
     * 
     * @param animal name of animal
     */
    public void removeAnimalFromEnclosure(Animal animal) {
        enclosureManagement.removeAnimal(animal);
    }
    
    /**
     * Set the size of the enclosure.
     * 
     * @param size the size of the enclosure
     */
    public void setEnclosureSize(double size) {
        enclosureManagement.setEnclosureSize(size);
    }

    /**
     * Set the humidity in the enclosure.
     * 
     * @param humidity the humidity of the enclosure
     */
    public void setHumidity(double humidity) {
        enclosureManagement.setHumidity(humidity);
    }

    /**
     * Set the temperature in the enclosure.
     * 
     * @param temperature the temperature in the enclosure
     */
    public void setTemperature(double temperature) {
        enclosureManagement.setTemperature(temperature);
    }

    /**
     * Set the vegetation coverage in the enclosure.
     * 
     * @param vegetationCoverage the vegetation coverage in the enclosure
     */
    public void setVegetationCoverage(double vegetationCoverage) {
        enclosureManagement.setVegetationCoverage(vegetationCoverage);
    }
    
    /**
     * Set the zone cleanliness in the enclosure.
     * 
     * @param zoneCleanliness the zone cleanliness in the enclosure.
     */
    public void setZoneCleanliness(int zoneCleanliness) {
        enclosureManagement.setZoneCleanliness(zoneCleanliness);
    }

    /**
     * Set the amount of food in through.
     * 
     * @param getFoodInTrough the amount of food in through
     */
    public void setFoodInTrough(int getFoodInTrough) {
        enclosureManagement.setFoodInTrough(getFoodInTrough);
    }

    /**
     * Set the type of the enclosure (climate).
     * 
     * @param type the type of the enclosure
     */
    public void setEnclosureType(EnclosureType type) {
        enclosureManagement.setEnclosureType(type);
    }

    /**
     * Get the size of the enclosure
     * 
     * @return the size of the enclosure
     */
    public double getEnclosureSize() {
        return enclosureManagement.getEnclosureSize();
    }
    
    /**
     * Get the humidity of the enclosure.
     * 
     * @return the humidity of the enclosure
     */
    public double getHumidity() {
        return enclosureManagement.getHumidity();
    }

    /**
     * Get the temperature of the enclosure.
     * 
     * @return the temperature of the enclosure
     */
    public double getTemperature() {
        return enclosureManagement.getTemperature();
    }

    /**
     * Get the vegetation coverage of the enclosure.
     * 
     * @return the vegetation coverage of the enclosure
     */
    public double getVegetationCoverage() {
        return enclosureManagement.getVegetationCoverage();
    }

    /**
     * Get the zone cleanliness of the enclosure.
     * 
     * @return the zone cleanliness of the enclosure
     */
    public int getZoneCleanliness() {
        return enclosureManagement.getZoneCleanliness();
    }

    /**
     * Get the food amount inside the trough.
     * 
     * @return the amount of food inside the through
     */
    public int getFoodInTrough() {
        return enclosureManagement.getFoodInTrough();
    }

    /**
     * Get the list of animals inside the enclosure.
     * 
     * @return the list of animals inside the enclosure
     */
    public List<Animal> getAnimalsInEnclosure() {
        return enclosureManagement.getAnimals();
    }

    /**
     * Get the enclosure type (in clinate)
     * 
     * @return the type of the enclosure
     */
    public EnclosureType getEnclosureType() {
        return enclosureManagement.getEnclosureType();
    }

    // Visitor
    /**
     * 
     * @param date the date the visitor would like to visit
     * @param entryTime the entry time on the visiting date
     * @param category the category of the ticket
     * @param duration the duration of the visit
     * @param animalFeedback the feed back on animals
     * @param cleanlinessFeedback the feed back on zoo cleanliness 
     * @param overallFeedback the pricing feed back
     */
    public void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        visitorManagement.addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
    }

    /**
     * Get the list of the visitors.
     * 
     * @return the list of the visitors
     */
    public List<Visit> getVisits() {
        return visitorManagement.getVisits();
    }

    /**
     * Get the visitor index.
     * 
     * @param index index of the visitors
     * @return visitor index
     */
    public Visit getVisit(int index) {
        return visitorManagement.getVisit(index);
    }

    /**
     * Get the total visits.
     * 
     * @return the total visits.
     */
    public int getTotalVisits() {
        return visitorManagement.getTotalVisits();
    }
    
    /**
     * Get the visits count by date.
     * 
     * @param date the date of the visits
     * @return the visit counts by date
     */
    public int getVisitsCountByDate(String date) {
        return visitorManagement.getVisitsCountByDate(date);
    }

    /**
     * Get the peak hour by date.
     * 
     * @param date the peak hour in the date
     * @return the peak hour count by date
     */
    public int getPeakHourByDate(String date) {
        return visitorManagement.getPeakHourByDate(date);
    }

    /**
     * Get the average animal feed back by date.
     * 
     * @param date the feed back in the date
     * @return the feed back by the date
     */
    public double getAverageAnimalFeedbackByDate(String date) {
        return visitorManagement.getAverageAnimalFeedbackByDate(date);
    }

    /**
     * Get the average pricing feed back by date.
     * 
     * @param date the average pricing feed back by date
     * @return the feed back on pricing by the date
     */
    public double getAveragePricingFeedbackByDate(String date) {
        return visitorManagement.getAveragePricingFeedbackByDate(date);
    }

    /**
     * Set the pricing for all ages & groups of people.
     * 
     * @param pricing the pricing for the people in different ages & status
     */
    public void setPricing(Map<String, Double> pricing) {
        visitorManagement.setPricing(pricing);
    }

    /**
     * Get the daily revenue.
     * 
     * @param date the daily revenue
     * @return the revenue of the day
     */
    public double getRevenueByDate(String date) {
        return visitorManagement.getRevenueByDate(date);
    }
}
