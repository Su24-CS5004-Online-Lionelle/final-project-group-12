package zoosys.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Yangcheng Luo
 * Class implementing the IVisitor interface.
 * Make sure all methods in the IVisitor interface can be properly used.
 */
public class Visitor implements IVisitor {

    // List to store visit records
    private List<Visit> visits;
    // Map to store pricing information based on visitor categories
    private Map<String, Double> pricing;

    /**
     * Constructor initializing the visits list and setting default pricing for different categories.
     */
    public Visitor() {
        this.visits = new ArrayList<>();
        this.pricing = new HashMap<>();
        // Set default pricing
        pricing.put("adult", 20.0);
        pricing.put("teen", 15.0);
        pricing.put("elderly", 10.0);
        pricing.put("minor", 5.0);
    }

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
    @Override
    public void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        Visit visit = new Visit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
        visits.add(visit);
    }

    /**
     * Retrieves a list of all visit records.
     *
     * @return A list of Visit objects.
     */
    @Override
    public List<Visit> getVisits() {
        return new ArrayList<>(visits);
    }

    /**
     * Retrieves the total number of visits recorded in the system.
     *
     * @return The total number of visits.
     */
    @Override
    public int getTotalVisits() {
        return visits.size();
    }

     /**
     * Retrieves the total number of visits.
     *
     * @return The total number of visits.
     */
    @Override
    public int getVisitsCountByDate(String date) {
        int count = 0;
        for (Visit visit : visits) {
            if (visit.getDate().equals(date)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Find out the peak hour for zoo.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The hour of the day (0-23) with the most entries.
     */
    @Override
    public int getPeakHourByDate(String date) {
        Map<Integer, Integer> hourCount = new HashMap<>();
        for (Visit visit : visits) {
            if (visit.getDate().equals(date)) {
                hourCount.put(visit.getEntryTime(), hourCount.getOrDefault(visit.getEntryTime(), 0) + 1);
            }
        }
        int peakHour = -1;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : hourCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                peakHour = entry.getKey();
            }
        }
        return peakHour;
    }

    /**
     * The average visitor feedback for animals by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The average animal feedback (1-5).
     */
    @Override
    public double getAverageAnimalFeedbackByDate(String date) {
        int totalFeedback = 0;
        int count = 0;
        for (Visit visit : visits) {
            if (visit.getDate().equals(date) && visit.getAnimalFeedback() != null) {
                totalFeedback += visit.getAnimalFeedback();
                count++;
            }
        }
        return count == 0 ? 0 : (double) totalFeedback / count;
    }

    /**
     * The average visitor feedback for cleanliness by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The cleanliness animal feedback (1-5).
     */
    @Override
    public double getAverageCleanlinessFeedbackByDate(String date) {
        int totalFeedback = 0;
        int count = 0;
        for (Visit visit : visits) {
            if (visit.getDate().equals(date) && visit.getCleanlinessFeedback() != null) {
                totalFeedback += visit.getCleanlinessFeedback();
                count++;
            }
        }
        return count == 0 ? 0 : (double) totalFeedback / count;
    }
    
    /**
     * The average visitor feedback for pricing by date.
     *
     * @param date The date of interest in the format "MM-DD-YYYY".
     * @return The cleanliness pricing feedback (1-5).
     */
    @Override
    public double getAveragePricingFeedbackByDate(String date) {
        int totalFeedback = 0;
        int count = 0;
        for (Visit visit : visits) {
            if (visit.getDate().equals(date) && visit.getOverallFeedback() != null) {
                totalFeedback += visit.getOverallFeedback();
                count++;
            }
        }
        return count == 0 ? 0 : (double) totalFeedback / count;
    }

    /**
     * Sets the pricing for different age group.
     *
     * @param pricing A map where the key is the visit category and the value is the corresponding price.
     */

    @Override
    public void setPricing(Map<String, Double> pricing) {
        this.pricing = pricing;
    }

    /**
     * Sets the pricing for different age group.
     *
     * @param pricing A map where the key is the visit category and the value is the corresponding price.
     */
    @Override
    public double getRevenueByDate(String date) {
        double totalRevenue = 0;
        for (Visit visit : visits) {
            if (visit.getDate().equals(date)) {
                Double price = pricing.get(visit.getCategory());
                if (price != null) {
                    totalRevenue += price;
                }
            }
        }
        return totalRevenue;
    }

    /**
     * Provides a string representation of the Visitor object.
     *
     * @return A string representation of the visitor object, including visits and pricing.
     */
    @Override
    public String toString() {
        return "VisitorImpl{" +
                "visits=" + visits +
                ", pricing=" + pricing +
                '}';
    }

    /**
     * Reads visit data from a CSV file and populates the system with this data.
     * Each line in the file represents a visit, which is parsed and added to the system.
     */
    @Override
    public void readCSV() {
        List<String> lines;
        try {
            InputStream is = getClass().getResourceAsStream("/visitor.csv");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        if (lines == null || lines.isEmpty()) {
            return;
        }

        // Process header and remove it from lines
        lines.remove(0);

        // Process each line and add visits
        for (String line : lines) {
            String[] record = line.split(",");
            String date = record[0];
            int entryTime = Integer.parseInt(record[1]);
            String category = record[2];
            int duration = Integer.parseInt(record[3]);
            Integer animalFeedback = record[4].isEmpty() ? null : Integer.parseInt(record[4]);
            Integer cleanlinessFeedback = record[5].isEmpty() ? null : Integer.parseInt(record[5]);
            Integer pricingFeedback = record[6].isEmpty() ? null : Integer.parseInt(record[6]);
            addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
        }
    }
}
