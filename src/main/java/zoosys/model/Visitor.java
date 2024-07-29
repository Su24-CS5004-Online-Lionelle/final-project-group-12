package zoosys.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Visitor implements IVisitor{
    private List<Visit> visits;
    private Map<String, Double> pricing;

    public void VisitorImpl() {
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
}