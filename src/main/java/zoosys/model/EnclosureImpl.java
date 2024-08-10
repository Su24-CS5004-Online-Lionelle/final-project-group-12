package zoosys.model;

/**
 * Implement the Enclosure interface and represents an enclosure in the zoo with attibutes.
 */
public class EnclosureImpl implements Enclosure {
    private int id;
    private double size;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private int zoneCleanliness;
    private int foodInTrough;

    /**
     * Construct EnclosureImpl instance with specified attibutes
     * 
     * @param id the id of the enclosure
     * @param size the size of the enclosure
     * @param humidity the humidity of the enclosure
     * @param temperature the temperature of the enclosure
     * @param vegetationCoverage the vegetation coverage of the enclosure
     * @param zoneCleanliness the zone cleanliness of the enclosure
     * @param foodInTrough the food remains in the trough
     */
    public EnclosureImpl(int id, double size, double humidity, double temperature, double vegetationCoverage, 
                         int zoneCleanliness, int foodInTrough) {
        this.id = id;
        this.size = size;
        this.humidity = humidity;
        this.temperature = temperature;
        this.vegetationCoverage = vegetationCoverage;
        this.zoneCleanliness = zoneCleanliness;
        this.foodInTrough = foodInTrough;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public double getEnclosureSize() {
        return size;
    }

    @Override
    public void setEnclosureSize(double size) {
        this.size = size;
    }

    @Override
    public double getHumidity() {
        return humidity;
    }

    @Override
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getVegetationCoverage() {
        return vegetationCoverage;
    }

    @Override
    public void setVegetationCoverage(double vegetationCoverage) {
        this.vegetationCoverage = vegetationCoverage;
    }

    @Override
    public int getZoneCleanliness() {
        return zoneCleanliness;
    }

    @Override
    public void setZoneCleanliness(int zoneCleanliness) {
        this.zoneCleanliness = zoneCleanliness;
    }

    @Override
    public int getFoodInTrough() {
        return foodInTrough;
    }

    @Override
    public void setFoodInTrough(int foodInTrough) {
        this.foodInTrough = foodInTrough;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toCSV() {
        return String.format("%d,%f,%f,%f,%f,%d,%d",
            id, size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough);
    }
}
