package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Attributes for the enclosures's conditions.
 */
public class Enclosures implements IEnclosures{
    private List<Animal> animals;
    private double enclosureSize;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private int zoneCleanliness;
    private int foodInTrough;
    private EnclosureType enclosureType;

/**
 * 
 * @param size Size of the enclosures
 * @param humidity Humidity inside the enclosures
 * @param temperature Temperature inside the enclosures
 * @param vegetationCoverage Vegetation coverage inside the enclosures
 * @param zoneCleanliness Cleanliness inside the enclosures
 * @param foodInTrough Amount of food in trough
 * @param enclosureType The type of enclosure
 */
public Enclosures(double size, double humidity, double temperature, double vegetationCoverage,
                  int zoneCleanliness, int foodInTrough) {
    this.enclosureSize = size;
    this.humidity = humidity;
    this.temperature = temperature;
    this.vegetationCoverage = vegetationCoverage;
    this.zoneCleanliness = zoneCleanliness;
    this.foodInTrough = foodInTrough;
    this.enclosureType = enclosureType;
    this.animals = new ArrayList<>();
}

/**
 * Methods to add an animal to the enclosure 
 * 
 * @param animal The animal to add
 * @throw IllegalArgumentException the animal cannot be empty.
 */
@Override
public void addAnimal(Animal animal) {
    if (animal != null) {
        animals.add(animal);
    } else {
        throw new IllegalArgumentException("Animal cannot be null");
    }
}

/**
 * Getter and setter for enclosure size.
 */
@Override
public double getEnclosureSize() {
    return enclosureSize;
}
public void setEnclosureSize(double enclosureSize) {
    if (enclosureSize > 0) {
        this.enclosureSize = enclosureSize;
    } else {
        throw new IllegalArgumentException("The enclosure size must be larger than 0.");
    }
}

/**
 * Getter and setter for humidity.
 */
@Override
public double getHumidity() {
    return humidity;
}
public void setHumidity(double humidity) {
    if (humidity >= 0 && humidity <= 100) {
        this.humidity = humidity;
    } else {
        throw new IllegalArgumentException("Humidity must be between 0 and 100.");
    }
}

/**
 * Getter and setter for temperature.
 */
@Override
public double getTemperature() {
    return temperature;
}
public void setTemperature(double temperature) {
    if (temperature >= -30 && temperature <= 50) {
        this.temperature = temperature;
    } else {
        throw new IllegalArgumentException("Temperature must be between -30 to 50 degree Celcius.");
    }
}

/**
 * Getter and setter for vegetation coverage
 */
@Override
public double getVegetationCoverage() {
    return vegetationCoverage;
}
public void setVegetationCoverage(double vegetationCoverage) {
    if (vegetationCoverage >= 0 && vegetationCoverage <= 100) {
        this.vegetationCoverage = vegetationCoverage;
    } else {
        throw new IllegalArgumentException("Vegetation coverage must be between 0 and 100.");
    }
}

/**
 * Getter and setter for zone cleanliness
 */
@Override
public int getZoneCleanliness() {
    return zoneCleanliness;
}
public void setZoneCleanliness(int zoneCleanliness) {
    if (zoneCleanliness >= 0 && zoneCleanliness <= 100) {
        this.zoneCleanliness = zoneCleanliness;
    } else {
        throw new IllegalArgumentException("Zone cleanliness must be between 0 and 100.");
    }
}

/**
 * Getter and setter for food in trough
 */
@Override
public int getFoodInTrough() {
    return foodInTrough;
}
public void setFoodInTrough(int foodInTrough) {
    if (foodInTrough >= 0) {
        this.foodInTrough = foodInTrough;
    } else {
        throw new IllegalArgumentException("The food remain in trough can not be negative.");
    }
}

public EnclosureType getEnclosureType() {
    return enclosureType;
}

public void setEnclosureType(EnclosureType enclosureType) {
    this.enclosureType = enclosureType;
}

public static Enclosures creaEnclosures(double size, double humidity, double temperature, double vegetationCoverage, 
        int zoneCleanliness, int foodInTrough, EnclosureType enclosureType) {
    return new Enclosures(size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough);
    }
}
