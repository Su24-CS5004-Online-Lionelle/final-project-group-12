package zoosys.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Enclosures implements IEnclosures{
    private List<Animal> animals;
    private double enclosureSize;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private int zoneCleanliness;
    private int foodInTrough;

public Enclosures(double size, double humidity, double temperature, double vegetationCoverage,
                  int zoneCleanliness, int foodInTrough) {
    this.enclosureSize = size;
    this.humidity = humidity;
    this.temperature = temperature;
    this.vegetationCoverage = vegetationCoverage;
    this.zoneCleanliness = zoneCleanliness;
    this.foodInTrough = foodInTrough;
    this.animals = new ArrayList<>();
}

    @Override
    public void addAnimal(Animal animal) {
        if (animal != null) {
            animals.add(animal);
        } else {
            throw new IllegalArgumentException("Animal cannot be null");
        }
    }

    @Override
    public double getEnclosureSize() {
        return enclosureSize;
    }
    public void setEnclosureSize(double enclosureSize) {
        this.enclosureSize = enclosureSize;
    }

    @Override
    public double getHumidity() {
        return humidity;
    }
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getVegetationCoverage() {
        return vegetationCoverage;
    }
    public void setVegetationCoverage(double vegetationCoverage) {
        this.vegetationCoverage = vegetationCoverage;
    }

    @Override
    public int getZoneCleanliness() {
        return zoneCleanliness;
    }
    public void setZoneCleanliness(int zoneCleanliness) {
        this.zoneCleanliness = zoneCleanliness;
    }

    @Override
    public int getFoodInTrough() {
        return foodInTrough;
    }
    public void setFoodInTrough(int foodInTrough) {
        this.foodInTrough = foodInTrough;
    }
    // Function to create a new enclosure
    public static Enclosures createNewEnclosure() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the enclosure size: \n");
        double size = scanner.nextDouble();

        System.out.print("Please enter the humidity: \n");
        double humidity = scanner.nextDouble();

        System.out.print("Please enter the temperature: \n");
        double temperature = scanner.nextDouble();

        System.out.print("Please enter the vegetation coverage: \n");
        double vegetationCoverage = scanner.nextDouble();

        System.out.print("Please enter the cleanliness: \n");
        int zoneCleanliness = scanner.nextInt();

        System.out.print("Please enter the food in trough: \n");
        int foodInTrough = scanner.nextInt();

        return new Enclosures(size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough);
    }

    public void addAnimal() {
        
        Animal animal = new Animal();
        this.addAnimal(animal);
    }
}
