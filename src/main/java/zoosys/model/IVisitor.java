package zoosys.model;

import java.util.List;
import java.util.Map;

public interface IVisitor {
    void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback);
    List<Visit> getVisits();
    Visit getVisit(int index);
    int getTotalVisits();
    int getVisitsCountByDate(String date);
    int getPeakHourByDate(String date);
    double getAverageAnimalFeedbackByDate(String date);
    double getAverageCleanlinessFeedbackByDate(String date);
    double getAveragePricingFeedbackByDate(String date);
    void setPricing(Map<String, Double> pricing);
    double getRevenueByDate(String date);
}