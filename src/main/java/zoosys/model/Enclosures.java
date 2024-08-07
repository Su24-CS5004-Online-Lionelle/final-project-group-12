package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Attributes for the enclosures's conditions.
 */
public class Enclosures implements IEnclosures{
    private List<Animal> animals = new ArrayList<>(); // Initialize the list 
    private double enclosureSize;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private int zoneCleanliness;
    private int foodInTrough;
    private EnclosureType enclosureType;

/**
 * Constructs new Enclosures with the specified attributes.
 * 
 * @param enclosureSize Size of the enclosures
 * @param humidity Humidity inside the enclosures
 * @param temperature Temperature inside the enclosures
 * @param vegetationCoverage Vegetation coverage inside the enclosures
 * @param zoneCleanliness Cleanliness inside the enclosures
 * @param foodInTrough Amount of food in trough
 * @param enclosureType The type of enclosure
 */
public Enclosures(double enclosureSize, double humidity, double temperature, double vegetationCoverage,
                  int zoneCleanliness, int foodInTrough, EnclosureType enclosureType) {
    setEnclosureSize(enclosureSize);
    setHumidity(humidity);
    setTemperature(temperature);
    setVegetationCoverage(vegetationCoverage);
    setZoneCleanliness(zoneCleanliness);
    setFoodInTrough(foodInTrough);
    this.enclosureType = enclosureType;
}

/**
 * Methods to add an animal to the enclosure 
 * 
 * @param animal The animal to add
 * @throws IllegalArgumentException the animal cannot be empty.
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
 * Remove animal from the enclosure
 * 
 * @param animal the animal to be removed from the enclosure.
 * @throws IllegalArgumentExeption the animal can not be empty or can not be removed if not in the enclosure.
 */
public void removeAnimal(Animal animal) {
    if (animal != null) {
        if(animals.contains(animal)) {
            animals.remove(animal);
        } else {
            throw new IllegalArgumentException("The animal you would like to remove is not in the enclosure.");
        }
    } else {
        throw new IllegalArgumentException("Animal can not be null");
    }
}

/**
 * Getter and setter for enclosure size.
 * 
 * @return enclosure size
 */
@Override
public double getEnclosureSize() {
    return enclosureSize;
}

/**
 * Set the size of the enclosure.
 * 
 * @param enclosureSize
 * @throws IllegalArgumentException if the size is not greater than 0
 */
public void setEnclosureSize(double enclosureSize) {
    if (enclosureSize > 0) {
        this.enclosureSize = enclosureSize;
    } else {
        throw new IllegalArgumentException("The enclosure size must be larger than 0.");
    }
}

/**
 * Getter and setter for humidity.
 * 
 * @return the humidity inside the enclosure
 */
@Override
public double getHumidity() {
    return humidity;
}

/**
 * Set the humidity inside the enclosure
 * 
 * @param humidity the humidity to be set
 * @throws IllegalArgumentException if the humidity is not between 0 and 100.
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
 * @return the temperature inside the enclosure
 */
@Override
public double getTemperature() {
    return temperature;
}

/**
 * Set the temperature inside the enclosure
 * 
 * @param temperature the temperature inside the enclosure
 * @throws IllegalArgumentException if temperature is lower than -30 or larger than 50
 */
public void setTemperature(double temperature) {
    if (temperature >= -30 && temperature <= 50) {
        this.temperature = temperature;
    } else {
        throw new IllegalArgumentException("Temperature must be between -30 to 50 degree Celcius.");
    }
}

/**
 * Getter and setter for vegetation coverage
 * 
 * @return the vegetation coverage
 */
@Override
public double getVegetationCoverage() {
    return vegetationCoverage;
}

/**
 * Set the vegetation coverage inside the enclosure
 * 
 * @param vegetationCoverage
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
 * Getter and setter for zone cleanliness
 * 
 * @return the cleanliness in zone
 */
@Override
public int getZoneCleanliness() {
    return zoneCleanliness;
}

/**
 * Set the cleanliness of the zone.
 * 
 * @param zoneCleanliness
 * @throws IllegalArgumentException if the cleanliness is lower than 0 or larger than 100
 */
public void setZoneCleanliness(int zoneCleanliness) {
    if (zoneCleanliness >= 0 && zoneCleanliness <= 100) {
        this.zoneCleanliness = zoneCleanliness;
    } else {
        throw new IllegalArgumentException("Zone cleanliness must be between 0 and 100.");
    }
}

/**
 * Getter and setter for food in trough
 * 
 * @return the amount of food in trough
 */
@Override
public int getFoodInTrough() {
    return foodInTrough;
}

/**
 * Set the amount of food in the trough.
 * 
 * @param foodInTrough the food amount in trough
 * @throws IllegalArgumentException if the amount of food is negative
 */
public void setFoodInTrough(int foodInTrough) {
    if (foodInTrough >= 0) {
        this.foodInTrough = foodInTrough;
    } else {
        throw new IllegalArgumentException("The food remain in trough can not be negative.");
    }
}

/**
 * Get the list of animals in the enclosure
 * 
 * @return the list of animals
 */
@Override
public List<Animal> getAnimals() {
   return new ArrayList<>(animals);
}

/**
 * Get the type of the enclosure
 * 
 * @return enclosure type
 */
@Override
public EnclosureType getEnclosureType() {
    return enclosureType;
}

/**
 * Set the type of the enclosure.
 * 
 * @param enclosureType the type of enclosure
 */
public void setEnclosureType(EnclosureType enclosureType) {
    this.enclosureType = enclosureType;
    }
}
