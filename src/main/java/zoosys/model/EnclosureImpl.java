package zoosys.model;

/**
 * Implementation of the Enclosure interface.
 */
public class EnclosureImpl implements Enclosure {
    private double size;
    private double humidity;
    private double temperature;
    private double vegetationCoverage;
    private double zoneCleanliness;
    private double foodInTrough;
    private EnclosureType enclosureType;

    /**
     * EnclosureImpl object
     * @param enclosureSize size of the enclosure
     * @param humidity humidity of the enclosure
     * @param temperature temperature of the enclosure
     * @param vegetationCoverage vegetation coverage of the enclosure
     * @param zoneCleanliness zone cleanliness of the enclosure
     * @param foodInTrough food remain in the trough
     * @param enclosureType type of the enclosure
     */
    public EnclosureImpl(double enclosureSize, double humidity, double temperature, double vegetationCoverage, double zoneCleanliness, 
            double foodInTrough, EnclosureType enclosureType) {
        this.size = enclosureSize;
        this.humidity = humidity;
        this.temperature = temperature;
        this.vegetationCoverage = vegetationCoverage;
        this.zoneCleanliness = zoneCleanliness;
        this.foodInTrough = foodInTrough;
        this.enclosureType = enclosureType;
    }

    @Override
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
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
    public double getZoneCleanliness() {
        return zoneCleanliness;
    }
    public void setZoneCleanliness(double zoneCleanliness) {
        this.zoneCleanliness = zoneCleanliness;
    }

    @Override
    public double getFoodInTrough() {
        return foodInTrough;
    }
    public void setFoodInTrough(double foodInTrough) {
        this.foodInTrough = foodInTrough;
    }

    @Override
    public EnclosureType getEnclosureType() {
        return enclosureType;
    }
    public void setType(EnclosureType type) {
        this.enclosureType = type;
    }

    /**
     * convert enclosure attributes to CSV format string
     * 
     * @return csv formate string representing the enclosure attributes
     */
    @Override
    public String toCSV() {
        return size + "," + humidity + "," + temperature + "," + vegetationCoverage + "," + zoneCleanliness + "," + foodInTrough + "," + enclosureType.name();
    }

    /**
     * Create EnclosureImpl object from a CSV formatted string.
     * 
     * @param csvLine csv formatted string 
     * @return EnclosureImpl object
     */
    public static Enclosure fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length != 7) {
            throw new IllegalArgumentException("Invalid CSV line for Enclosure: " + csvLine);
        }

        double size = Double.parseDouble(values[0]);
        double humidity = Double.parseDouble(values[1]);
        double temperature = Double.parseDouble(values[2]);
        double vegetationCoverage = Double.parseDouble(values[3]);
        int cleanliness = Integer.parseInt(values[4]);
        int food = Integer.parseInt(values[5]);
        EnclosureType type = EnclosureType.valueOf(values[6]);

        return new EnclosureImpl(size, humidity, temperature, vegetationCoverage, cleanliness, food, type);
    }
}
