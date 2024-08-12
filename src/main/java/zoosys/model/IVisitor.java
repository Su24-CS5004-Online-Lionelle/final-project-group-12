package zoosys.model;

import java.util.List;
import java.util.Map;

/**
 * Yangcheng Luo
 * Interface that have operations for visitor info.
 * With methods retrieving visitor info from the csv file and summarize it for user by data.
 */

public interface IVisitor {

    /**
     * Adds a new visit.
     *
     * @param date                 The date of the visit in the format "MM-DD-YYYY".
     * @param entryTime            The entry time of the visit in hours (24-hour format,for example 16 is 4 pm).
     * @param category             The age group of the visit.
     * @param duration             The time(hours) visitor stays.
     * @param animalFeedback       The feedback rating for animals.
     * @param cleanlinessFeedback  The feedback rating for cleanliness.
     * @param pricingFeedback      The feedback rating for pricing.
     */
    void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback);

    /**
     * Retrieves a list of all visits info .
     *
     * @return A list of Visit objects representing each recorded visit.
     */
    List<Visit> getVisits();

    /**
     * The total number of visits recorded in the system.
     *
     * @return The total count of visits.
     */
    int getTotalVisits();

     /**
     * Retrieves the total number of visits.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The number of visits on the specified date.
     */
    int getVisitsCountByDate(String date);

    /**
     * Find out the peak hour for zoo.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The hour of the day (0-23) with the most entries.
     */
    int getPeakHourByDate(String date);

    /**
     * The average visitor feedback for animals by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The average animal feedback (1-5).
     */
    double getAverageAnimalFeedbackByDate(String date);

    /**
     * The average visitor feedback for cleanliness by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The cleanliness animal feedback (1-5).
     */
    double getAverageCleanlinessFeedbackByDate(String date);
    
    /**
     * The average visitor feedback for pricing by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The cleanliness pricing feedback (1-5).
     */
    double getAveragePricingFeedbackByDate(String date);

    /**
     * Sets the pricing for different age group.
     *
     * @param pricing A map where the key is the visit category and the value is the corresponding price.
     */
    void setPricing(Map<String, Double> pricing);
    /**
     * Calculates the total revenue by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The total revenue ny date.
     */
    double getRevenueByDate(String date);

    /**
     * Reads visit data from a CSV file and populates the system with this data.
     */
    void readCSV();
}
