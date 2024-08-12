package zoosys.model;

/**
 * Yangcheng Luo
 * Class representing a visitor's visit to the zoo.
 * Each visit includes details for the date, entry time, category of visitor, duration of stay, 
 * and feedback on various aspects of the zoo experience.
 */
public class Visit {
    // The date of the visit in the format "YYYY-MM-DD".
    private String date;
    //The entry time of the visit in hours (24-hour format,for example 16 is 4 pm).
    private int entryTime; 
    //The age group of the visit.
    private String category; 
    //The time(hours) visitor stays.
    private int duration; 
    //The feedback rating for animals.
    private Integer animalFeedback; 
    //The feedback rating for cleanliness.
    private Integer cleanlinessFeedback; 
    //The feedback rating for pricing.
    private Integer overallFeedback; 

    /**
     * Constructor to initialize all fields of a Visit.
     *
     * @param date                 The date of the visit.
     * @param entryTime            The entry time of the visit.
     * @param category             The category of the visitor.
     * @param duration             The duration of the stay in hours.
     * @param animalFeedback       The feedback on animals.
     * @param cleanlinessFeedback  The feedback on cleanliness.
     * @param overallFeedback      The overall feedback on the visit.
     */
    public Visit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer overallFeedback) {
        this.date = date;
        this.entryTime = entryTime;
        this.category = category;
        this.duration = duration;
        this.animalFeedback = animalFeedback;
        this.cleanlinessFeedback = cleanlinessFeedback;
        this.overallFeedback = overallFeedback;
    }

     /**
     * Retrieves the date of the visit.
     *
     * @return The date of the visit.
     */
    public String getDate() {
        return date;
    }

     /**
     * Retrieves the entry time of the visit.
     *(24-hour format,for example 16 is 4 pm).
     * @return The entry time of the visit.
     */
    public int getEntryTime() {
        return entryTime;
    }
    
     /**
     * Retrieves the age group of the visitor.
     *
     * @return The age group of the visitor.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the time visitor stay in hours.
     *
     * @return The time visitor stay in hours.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Retrieves the feedback on the animals.
     *
     * @return The feedback on the animals(1-5).
     */
    public Integer getAnimalFeedback() {
        return animalFeedback;
    }

    /**
     * Retrieves the feedback on the cleanliness.
     *
     * @return The feedback on the cleanliness(1-5).
     */
    public Integer getCleanlinessFeedback() {
        return cleanlinessFeedback;
    }

    /**
     * Retrieves the overall feedback on the visit.
     *
     * @return The overall feedback on the visit(1-5).
     */
    public Integer getOverallFeedback() {
        return overallFeedback;
    }

    
    @Override
    public String toString() {
        return "Visit{" +
                "date='" + date + '\'' +
                ", entryTime=" + entryTime +
                ", category='" + category + '\'' +
                ", duration=" + duration +
                ", animalFeedback=" + animalFeedback +
                ", cleanlinessFeedback=" + cleanlinessFeedback +
                ", overallFeedback=" + overallFeedback +
                '}';
    }
}
