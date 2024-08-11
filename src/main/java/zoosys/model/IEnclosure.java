package zoosys.model;

/**
 * The IEnclosure interface defines the methods that must be implemented
 * by any class representing an enclosure in the zoo. 
 */
public interface IEnclosure {

    /**
     * Gets the size of the enclosure.
     * 
     * @return the size of the enclosure
     */
    double getSize();
    
    /**
     * Sets the size of the enclosure.
     * 
     * @param size the size to set
     */
    void setSize(double size);

    /**
     * Gets the size of the enclosure (alias for getSize).
     * 
     * @return the size of the enclosure
     */
    double getEnclosureSize();
    
    /**
     * Sets the size of the enclosure (alias for setSize).
     * 
     * @param enclosureSize the size to set
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
     * @param humidity the humidity level to set
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
     * @param temperature the temperature to set
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
     * @param vegetationCoverage the vegetation coverage to set
     */
    void setVegetationCoverage(double vegetationCoverage);

    /**
     * Gets the cleanliness level of the enclosure.
     * 
     * @return the zone cleanliness level
     */
    int getZoneCleanliness();
    
    /**
     * Sets the cleanliness level of the enclosure.
     * 
     * @param zoneCleanliness the zone cleanliness level to set
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
     * @param foodInTrough the amount of food to set
     */
    void setFoodInTrough(int foodInTrough);

    /**
     * Gets the unique ID of the enclosure.
     * 
     * @return the enclosure's ID
     */
    int getId();

    /**
     * Generates a CSV representation of the enclosure's data.
     * 
     * @return a CSV-formatted string containing the enclosure's data
     */
    String toCSV();
}
