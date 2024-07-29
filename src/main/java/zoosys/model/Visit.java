package zoosys.model;

public class Visit {
    private String date;
    private int entryTime; // hour only
    private String category; // adult, teen, elderly, minor
    private int duration; // duration of stay in hours
    private Integer animalFeedback; // feedback on a scale of 1-5
    private Integer cleanlinessFeedback; // feedback on a scale of 1-5
    private Integer overallFeedback; // feedback on a scale of 1-5

    public Visit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer overallFeedback) {
        this.date = date;
        this.entryTime = entryTime;
        this.category = category;
        this.duration = duration;
        this.animalFeedback = animalFeedback;
        this.cleanlinessFeedback = cleanlinessFeedback;
        this.overallFeedback = overallFeedback;
    }

    public String getDate() {
        return date;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public String getCategory() {
        return category;
    }

    public int getDuration() {
        return duration;
    }

    public Integer getAnimalFeedback() {
        return animalFeedback;
    }

    public Integer getCleanlinessFeedback() {
        return cleanlinessFeedback;
    }

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
