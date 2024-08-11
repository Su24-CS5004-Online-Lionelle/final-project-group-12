package zoosys.controller;

import zoosys.model.*;
import java.util.List;

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
        this.employeeManagement = new EmployeeManagement();
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
        if (animal != null) {
            enclosureManagement.addAnimal(animal);
        }
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
        if (updatedAnimal != null) {
            Animal animal = findAnimalById(updatedAnimal.getAnimal_id());
            if (animal != null) {
                enclosureManagement.removeAnimal(animal);
                enclosureManagement.addAnimal(updatedAnimal);
            }
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

    /**
     * Get the size of the enclosure
     * 
     * @return the size of the enclosure
     */
    public double getEnclosureSize() {
        return enclosureManagement.getEnclosureSize();
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
     * Get the humidity of the enclosure.
     * 
     * @return the humidity of the enclosure
     */
    public double getHumidity() {
        return enclosureManagement.getHumidity();
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
     * Get the temperature of the enclosure.
     * 
     * @return the temperature of the enclosure
     */
    public double getTemperature() {
        return enclosureManagement.getTemperature();
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
     * Get the vegetation coverage of the enclosure.
     * 
     * @return the vegetation coverage of the enclosure
     */
    public double getVegetationCoverage() {
        return enclosureManagement.getVegetationCoverage();
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
     * Get the zone cleanliness of the enclosure.
     * 
     * @return the zone cleanliness of the enclosure
     */
    public int getZoneCleanliness() {
        return enclosureManagement.getZoneCleanliness();
    }

    /**
     * Set the amount of food in the trough.
     * 
     * @param foodInTrough the amount of food in trough
     */
    public void setFoodInTrough(int getFoodInTrough) {
        enclosureManagement.setFoodInTrough(getFoodInTrough);
    }

    /**
     * Get the food amount inside the trough.
     * 
     * @return the amount of food inside the trough
     */
    public int getFoodInTrough() {
        return enclosureManagement.getFoodInTrough();
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
     * Get the type of the enclosure (climate)
     * 
     * @return the type of the enclosure
     */
    public EnclosureType getEnclosureType() {
        return enclosureManagement.getEnclosureType();
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
     * Get the visit at a specified index.
     * 
     * @param index index of the visit
     * @return the visit at the specified index
     */
    public Visit getVisit(int index) {
        return visitorManagement.getVisit(index);
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
