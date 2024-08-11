package zoosys.controller;

import zoosys.model.*;

import java.util.List;
import java.util.Map;

/**
 * The VisitorController class manages operations related to visitor data and visits,
 * using the Visitor model. 
 */
public class VisitorController {
    private Visitor visitorModel;

    /**
     * Constructs a VisitorController instance with the specified Visitor model.
     * 
     * @param visitorModel the Visitor model to manage visit operations
     */
    public VisitorController(Visitor visitorModel) {
        this.visitorModel = visitorModel;
    }

    /**
     * Adds a visit to the visitor model.
     * 
     * @param date the date of the visit
     * @param entryTime the entry time of the visit
     * @param category the category of the visit
     * @param duration the duration of the visit
     * @param animalFeedback the feedback on animals, or null if not provided
     * @param cleanlinessFeedback the feedback on cleanliness, or null if not provided
     * @param pricingFeedback the feedback on pricing, or null if not provided
     */
    public void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        visitorModel.addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
    }

    /**
     * Retrieves all visits from the visitor model.
     * 
     * @return a List of Visit objects representing all visits
     */
    public List<Visit> getAllVisits() {
        return visitorModel.getVisits();
    }

    /**
     * Retrieves a specific visit by its index.
     * 
     * @param index the index of the visit to retrieve
     * @return the Visit object at the specified index
     */
    public Visit getVisit(int index) {
        return visitorModel.getVisit(index);
    }

    /**
     * Gets the total number of visits recorded.
     * 
     * @return the total number of visits
     */
    public int getTotalVisits() {
        return visitorModel.getTotalVisits();
    }

    /**
     * Gets the number of visits on a specific date.
     * 
     * @param date the date for which to get the visit count
     * @return the number of visits on the specified date
     */
    public int getVisitsCountByDate(String date) {
        return visitorModel.getVisitsCountByDate(date);
    }

    /**
     * Gets the peak hour for visits on a specific date.
     * 
     * @param date the date for which to get the peak hour
     * @return the peak hour for visits on the specified date
     */
    public int getPeakHourByDate(String date) {
        return visitorModel.getPeakHourByDate(date);
    }

    /**
     * Gets the average animal feedback for visits on a specific date.
     * 
     * @param date the date for which to get the average animal feedback
     * @return the average animal feedback for the specified date
     */
    public double getAverageAnimalFeedbackByDate(String date) {
        return visitorModel.getAverageAnimalFeedbackByDate(date);
    }

    /**
     * Gets the average cleanliness feedback for visits on a specific date.
     * 
     * @param date the date for which to get the average cleanliness feedback
     * @return the average cleanliness feedback for the specified date
     */
    public double getAverageCleanlinessFeedbackByDate(String date) {
        return visitorModel.getAverageCleanlinessFeedbackByDate(date);
    }

    /**
     * Gets the average pricing feedback for visits on a specific date.
     * 
     * @param date the date for which to get the average pricing feedback
     * @return the average pricing feedback for the specified date
     */
    public double getAveragePricingFeedbackByDate(String date) {
        return visitorModel.getAveragePricingFeedbackByDate(date);
    }

    /**
     * Sets the pricing map in the visitor model.
     * 
     * @param pricing a Map where the key is the pricing category and the value is the price
     */
    public void setPricing(Map<String, Double> pricing) {
        visitorModel.setPricing(pricing);
    }

    /**
     * Gets the revenue for a specific date.
     * 
     * @param date the date for which to get the revenue
     * @return the revenue generated on the specified date
     */
    public double getRevenueByDate(String date) {
        return visitorModel.getRevenueByDate(date);
    }

    /**
     * Reads visitor data from a CSV file and populates the model.
     */
    public void readVisitorData() {
        visitorModel.readCSV();
    }
}
