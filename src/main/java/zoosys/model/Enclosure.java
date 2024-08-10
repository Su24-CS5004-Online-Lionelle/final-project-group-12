package zoosys.model;

/**
 * The Enclosure interface defines the basic structure and functionality 
 * for enclosures in the zoo management system.
 */
public interface Enclosure {

    /**
     * Gets the ID of the enclosure.
     * 
     * @return the enclosure ID
     */
    int getId();

    /**
     * Gets the size of the enclosure.
     * 
     * @return the size of the enclosure
     */
    double getSize();

    /**
     * Sets the size of the enclosure.
     * 
     * @param size the size to be set
     */
    void setSize(double size);

    /**
     * Gets the enclosure size.
     * 
     * @return the enclosure size
     */
    double getEnclosureSize();

    /**
     * Sets the enclosure size.
     * 
     * @param enclosureSize the enclosure size to be set
     */
    void setEnclosureSize(double enclosureSize);

    /**
     * Gets the humidity level in the enclosure.
     * 
     * @return the humidity level
     */
    double getHumidity();

    /**
     * Sets the humidity level in the enclosure.
     * 
     * @param humidity the humidity level to be set
     */
    void setHumidity(double humidity);

    /**
     * Gets the temperature in the enclosure.
     * 
     * @return the temperature
     */
    double getTemperature();

    /**
     * Sets the temperature in the enclosure.
     * 
     * @param temperature the temperature to be set
     */
    void setTemperature(double temperature);

    /**
     * Gets the vegetation coverage in the enclosure.
     * 
     * @return the vegetation coverage
     */
    double getVegetationCoverage();

    /**
     * Sets the vegetation coverage in the enclosure.
     * 
     * @param vegetationCoverage the vegetation coverage to be set
     */
    void setVegetationCoverage(double vegetationCoverage);

    /**
     * Gets the cleanliness level of the zone.
     * 
     * @return the cleanliness level
     */
    int getZoneCleanliness();

    /**
     * Sets the cleanliness level of the zone.
     * 
     * @param zoneCleanliness the cleanliness level to be set
     */
    void setZoneCleanliness(int zoneCleanliness);

    /**
     * Gets the amount of food in the trough.
     * 
     * @return the amount of food in the trough
     */
    int getFoodInTrough();

    /**
     * Sets the amount of food in the trough.
     * 
     * @param foodInTrough the amount of food to be set
     */
    void setFoodInTrough(int foodInTrough);

    /**
     * Generate CSV representation of enclosure data.
     * 
     * @return the csv
     */
    String toCSV();
}
