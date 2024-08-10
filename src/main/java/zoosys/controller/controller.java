package zoosys.controller;

import zoosys.model.*;
import java.util.List;

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

    // Animal
    /**
     * Add new animal to the enclosure
     * 
     * @param animal the animal to be added
     */
    public void addAnimal(int enclosureId, Animal animal) {
        if (animal != null) {
            enclosureManagement.addAnimal(enclosureId, animal);
        }
    }

    /**
     * Remove existed animal from the system.
     * 
     * @param id the ID of the animal to be removed
     */
    public void removeAnimal(int enclosureId, int animalId) {
        Animal animal = findAnimalById(enclosureId, animalId);
        if (animal != null) {
            enclosureManagement.removeAnimal(enclosureId, animal);
        }
    }

    /**
     * Edit an existing animal in the system.
     * 
     * @param updatedAnimal the updated animal data
     */
    public void editAnimal(int enclosureId, Animal updatedAnimal) {
        if (updatedAnimal != null) {
            Animal animal = findAnimalById(enclosureId, updatedAnimal.getAnimal_id());
            if (animal != null) {
                enclosureManagement.removeAnimal(enclosureId, animal);
                enclosureManagement.addAnimal(enclosureId, updatedAnimal);
            }
        }
    }

    /**
     * Find an animal by its ID.
     * 
     * @param id the ID of the animal
     * @return the animal with the specified ID, or null if not found
     */
    private Animal findAnimalById(int enclosureId, int animalId) {
        for (Animal animal : enclosureManagement.getAnimals(enclosureId)) {
            if (animal.getAnimal_id() == animalId) {
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
        if (name != null) {
            employeeManagement.removeEmployee(name);
        }
    }

    /**
     * Update the employee in the management system.
     * 
     * @param name the name of the employee to be updated
     * @param updatedEmployee the updated employee data
     */
    public void updateEmployee(String name, Employee updatedEmployee) {
        if (name != null && !name.isEmpty() && updatedEmployee != null) {
            employeeManagement.updateEmployee(name, updatedEmployee);
        }
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
    public void addEnclosure(Enclosure enclosure) {
        if (enclosure != null) {
            enclosureManagement.addEnclosure(enclosure);
        }
    }

    /**
     * Remove animal from enclosure.
     * 
     * @param animal name of animal
     */
    public void removeEnclosure(int id) {
            enclosureManagement.removeEnclosure(id);
    }

    /**
     * Set the size of the enclosure.
     * 
     * @param size the size of the enclosure
     */
    public void updateEnclosure(Enclosure enclosure) {
        if(enclosure != null) {
            enclosureManagement.updateEnclosure(enclosure);
        }
        
    }

    /**
     * Get the size of the enclosure
     * 
     * @return the size of the enclosure
     */
    public Enclosure getEnclosureById(int id) {
        return enclosureManagement.getEnclosure(id);
    }

    public EnclosureManagement getEnclosureManagement() {
        return enclosureManagement;
    }

    public void setEnclosureSize(int enclosureId, double size) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if(enclosure != null) {
            enclosure.setSize(size);
        }
    }

    public void setHumidity(int enclosureId, double humidity) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if(enclosure != null) {
            enclosure.setHumidity(humidity);
        }
    }
    
    public void setTemperature(int enclosureId, double temperature) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if (enclosure != null) {
            enclosure.setTemperature(temperature);
        }
    }

    public void setVegetationCoverage(int enclosureId, double vegetationCoverage) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if (enclosure != null) {
            enclosure.setVegetationCoverage(vegetationCoverage);
        }
    }

    public void setZoneCleanliness(int enclosureId, int cleanliness) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if (enclosure != null) {
            enclosure.setZoneCleanliness(cleanliness);
        }
    }

    public void setFoodInTrough(int enclosureId, int food) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if (enclosure != null) {
            enclosure.setFoodInTrough(food);
        }
    }

    public void setEnclosureType(int enclosureId, EnclosureType type) {
        Enclosure enclosure = enclosureManagement.getEnclosure(enclosureId);
        if (enclosure != null) {
            enclosure.setEnclosureType(type);
        }
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

    /**
     * Get the average cleanliness feedback by date.
     * 
     * @param date the date for which to get the average cleanliness feedback
     * @return the average cleanliness feedback on the specified date
     */
    public double getAverageCleanlinessFeedbackByDate(String date) {
        return visitorManagement.getAverageCleanlinessFeedbackByDate(date);
    }

    /**
     * Get the average pricing feedback by date.
     * 
     * @param date the date for which to get the average pricing feedback
     * @return the average pricing feedback on the specified date
     */
    public double getAveragePricingFeedbackByDate(String date) {
        return visitorManagement.getAveragePricingFeedbackByDate(date);
    }
}
