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
