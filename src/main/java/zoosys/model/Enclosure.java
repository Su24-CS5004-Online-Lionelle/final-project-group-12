package zoosys.model;

/**
 * The Enclosure interface defines the basic structure and functionality 
 * for enclosures in the zoo management system.
 */
public interface Enclosure {
    /**
     * Gets the size of the enclosure.
     * 
     * @return the size of the enclosure
     */
    double getSize();

    /**
     * Gets the humidity level of the enclosure.
     * 
     * @return the humidity level of the enclosure
     */
    double getHumidity();

    /**
     * Gets the temperature of the enclosure.
     * 
     * @return the temperature of the enclosure
     */
    double getTemperature();

    /**
     * Gets the vegetation coverage in the enclosure.
     * 
     * @return the vegetation coverage in the enclosure
     */
    double getVegetationCoverage();

    /**
     * Gets the zone cleanliness of the enclosure.
     * 
     * @return the zone cleanliness of the enclosure
     */
    double getZoneCleanliness();

    /**
     * Gets the amount of food in the trough of the enclosure.
     * 
     * @return the amount of food in the trough
     */
    double getFoodInTrough();

    /**
     * Gets the type of the enclosure.
     * 
     * @return the type of the enclosure
     */
    EnclosureType getEnclosureType();

    /**
     * Generate CSV representation of enclosure data.
     * 
     * @return the csv
     */
    String toCSV();
}
