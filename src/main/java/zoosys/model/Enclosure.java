package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an enclosure with various attributes and methods to manage animals within the enclosure.
 */
public class Enclosure implements IEnclosure {
    private int id;
    private double enclosureSize;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private int zoneCleanliness;
    private int foodInTrough;
    private EnclosureType enclosureType;
    

    /**
     * Constructs a new Enclosure with the specified attributes.
     * 
     * @param id the ID of the enclosure
     * @param size Size of the enclosure
     * @param humidity Humidity inside the enclosure
     * @param temperature Temperature inside the enclosure
     * @param vegetationCoverage Vegetation coverage inside the enclosure
     * @param zoneCleanliness Cleanliness inside the enclosure
     * @param foodInTrough Amount of food in the trough
     * @param enclosureType The type of enclosure
     */
    public Enclosure(int id, double enclosureSize, double humidity, double temperature, double vegetationCoverage,
                      int zoneCleanliness, int foodInTrough, EnclosureType enclosureType) {
        this.id = id;
        this.enclosureSize = enclosureSize;
        this.humidity = humidity;
        this.temperature = temperature;
        this.vegetationCoverage = vegetationCoverage;
        this.zoneCleanliness = zoneCleanliness;
        this.foodInTrough = foodInTrough;
        this.enclosureType = enclosureType;
    }

    /**
     * Get the size of the enclosure.
     * 
     * @return The enclosure size
     */
    @Override
    public double getSize() {
        return enclosureSize;
    }

    /**
     * Set the size of the enclosure.
     * 
     * @param size The new size of the enclosure
     * @throws IllegalArgumentException if the size is not greater than 0
     */
    @Override
    public void setSize(double size) {
        setEnclosureSize(size);
    }

    /**
     * Get the type of the enclosure.
     * 
     * @return The enclosure type
     */
    @Override
    public EnclosureType getEnclosureType() {
        return enclosureType;
    }

    /**
     * Set the type of the enclosure.
     * 
     * @param type The new type of the enclosure
     */
    @Override
    public void setType(EnclosureType type) {
        setEnclosureType(type);
    }

    /**
     * Getter and setter for humidity.
     * 
     * @return The humidity inside the enclosure
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * Set the humidity inside the enclosure.
     * 
     * @param humidity The new humidity level
     * @throws IllegalArgumentException if the humidity is not between 0 and 100
     */
    public void setHumidity(double humidity) {
        if (humidity >= 0 && humidity <= 100) {
            this.humidity = humidity;
        } else {
            throw new IllegalArgumentException("Humidity must be between 0 and 100.");
        }
    }

    /**
     * Getter and setter for temperature.
     * 
     * @return The temperature inside the enclosure
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Set the temperature inside the enclosure.
     * 
     * @param temperature The new temperature
     * @throws IllegalArgumentException if the temperature is lower than -30 or higher than 50
     */
    public void setTemperature(double temperature) {
        if (temperature >= -30 && temperature <= 50) {
            this.temperature = temperature;
        } else {
            throw new IllegalArgumentException("Temperature must be between -30 and 50 degrees Celsius.");
        }
    }

    /**
     * Getter and setter for vegetation coverage.
     * 
     * @return The vegetation coverage
     */
    public double getVegetationCoverage() {
        return vegetationCoverage;
    }

    /**
     * Set the vegetation coverage inside the enclosure.
     * 
     * @param vegetationCoverage The new vegetation coverage
     * @throws IllegalArgumentException if the coverage is not between 0 and 100
     */
    public void setVegetationCoverage(double vegetationCoverage) {
        if (vegetationCoverage >= 0 && vegetationCoverage <= 100) {
            this.vegetationCoverage = vegetationCoverage;
        } else {
            throw new IllegalArgumentException("Vegetation coverage must be between 0 and 100.");
        }
    }

    /**
     * Getter and setter for zone cleanliness.
     * 
     * @return The zone cleanliness
     */
    public int getZoneCleanliness() {
        return zoneCleanliness;
    }

    /**
     * Set the zone cleanliness.
     * 
     * @param zoneCleanliness The new cleanliness level
     * @throws IllegalArgumentException if the cleanliness is lower than 0 or higher than 100
     */
    public void setZoneCleanliness(int zoneCleanliness) {
        if (zoneCleanliness >= 0 && zoneCleanliness <= 100) {
            this.zoneCleanliness = zoneCleanliness;
        } else {
            throw new IllegalArgumentException("Zone cleanliness must be between 0 and 100.");
        }
    }

    /**
     * Getter and setter for food in the trough.
     * 
     * @return The amount of food in the trough
     */
    public int getFoodInTrough() {
        return foodInTrough;
    }

    /**
     * Set the amount of food in the trough.
     * 
     * @param foodInTrough The new amount of food
     * @throws IllegalArgumentException if the amount of food is negative
     */
    public void setFoodInTrough(int foodInTrough) {
        if (foodInTrough >= 0) {
            this.foodInTrough = foodInTrough;
        } else {
            throw new IllegalArgumentException("The food amount in the trough cannot be negative.");
        }
    }

    /**
     * Set the type of the enclosure.
     * 
     * @param enclosureType The new enclosure type
     */
    public void setEnclosureType(EnclosureType enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * Get the ID of the enclosure.
     * 
     * @return The enclosure ID
     */
    public int getId() {
        return id;
    }

    @Override
    public double getEnclosureSize() {
        return enclosureSize;
    }

    @Override
    public void setEnclosureSize(double enclosureSize) {
        if (enclosureSize > 0){
            this.enclosureSize = enclosureSize;
        } else {
            throw new IllegalArgumentException("The enclosure size can not be less than 0.");
        }
    }

    @Override 
    public String toString() {
        return "Enclosure{id=" + id + ", enclosureSize=" + enclosureSize + ", humidity=" + humidity + ", temperature=" + temperature + 
        ", vegetationCoverage=" + vegetationCoverage + ", zoneCleanliness=" + zoneCleanliness + ", foodInTrough=" + foodInTrough + 
        ", enclosureType=" + enclosureType + '}';
    }
}
