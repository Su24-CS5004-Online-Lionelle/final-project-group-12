package zoosys.model;

/**
 * The Feeding_Time class represents a specific feeding time using minutes since midnight.
 */
public class FeedingTime {
    /**
     * The time in minutes since midnight.
     */
    private int timeInMinutes;

    /**
     * Constructs a Feeding_Time instance with a specific hour and minute.
     * 
     * @param hour the hour of the feeding time (0-23)
     * @param minute the minute of the feeding time (0-59)
     */
    public FeedingTime(int hour, int minute) {
        this.timeInMinutes = hour * 60 + minute;
    }

    /**
     * Gets the time in minutes since midnight.
     * 
     * @return the feeding time in minutes
     */
    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    /**
     * Sets the time in minutes since midnight.
     * 
     * @param timeInMinutes the feeding time in minutes
     */
    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    /**
     * Returns a string representation of the feeding time in HH:MM format.
     * 
     * @return the feeding time as a formatted string
     */
    @Override
    public String toString() {
        int hour = timeInMinutes / 60;
        int minute = timeInMinutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }
}
