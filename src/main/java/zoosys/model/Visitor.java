package zoosys.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Visitor implements IVisitor {
    private List<Visit> visits;
    private Map<String, Double> pricing;

    public Visitor() {
        this.visits = new ArrayList<>();
        this.pricing = new HashMap<>();
        // Set default pricing
        pricing.put("adult", 20.0);
        pricing.put("teen", 15.0);
        pricing.put("elderly", 10.0);
        pricing.put("minor", 5.0);
    }

    @Override
    public void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback) {
        Visit visit = new Visit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
        visits.add(visit);
    }

    @Override
    public List<Visit> getVisits() {
        return new ArrayList<>(visits);
    }
    @Override
    public Visit getVisit(int index) {
        if (index >= 0 && index < visits.size()) {
            return visits.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid visit index");
        }
    }

    @Override
    public int getTotalVisits() {
        return visits.size();
    }

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

    @Override
    public void setPricing(Map<String, Double> pricing) {
        this.pricing = pricing;
    }

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

    @Override
    public String toString() {
        return "VisitorImpl{" +
                "visits=" + visits +
                ", pricing=" + pricing +
                '}';
    }

    @Override
    public void readCSV() {
        String filePath = "visitorInfo.csv";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            System.err.println("Could not find file: " + filePath);
            return;
        }

        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVReader csvReader = new CSVReader(reader)) {

            List<String[]> records = csvReader.readAll();
            for (String[] record : records.subList(1, records.size())) { // Skip header
                String date = record[0];
                int entryTime = Integer.parseInt(record[1]);
                String category = record[2];
                int duration = Integer.parseInt(record[3]);
                Integer animalFeedback = record[4].isEmpty() ? null : Integer.parseInt(record[4]);
                Integer cleanlinessFeedback = record[5].isEmpty() ? null : Integer.parseInt(record[5]);
                Integer pricingFeedback = record[6].isEmpty() ? null : Integer.parseInt(record[6]);
                addVisit(date, entryTime, category, duration, animalFeedback, cleanlinessFeedback, pricingFeedback);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}