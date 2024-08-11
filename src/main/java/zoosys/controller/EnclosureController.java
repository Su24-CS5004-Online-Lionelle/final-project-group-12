package zoosys.controller;

import java.util.List;

import zoosys.model.*;

/**
 * The EnclosureController class provides methods to interact with the EnclosureManagement system.
 */
public class EnclosureController {
    private EnclosureManagement enclosureManagement;

    /**
     * Constructs a new EnclosureController with the specified EnclosureManagement instance.
     * 
     * @param enclosureManagement the EnclosureManagement instance to use
     */
    public EnclosureController(EnclosureManagement enclosureManagement) {
        this.enclosureManagement = enclosureManagement;
    }

    /**
     * Adds a new enclosure to the management system.
     * 
     * @param id the ID of the enclosure
     * @param size the size of the enclosure
     * @param humidity the humidity level in the enclosure
     * @param temperature the temperature in the enclosure
     * @param vegetationCoverage the vegetation coverage in the enclosure
     * @param zoneCleanliness the cleanliness level of the enclosure
     * @param foodInTrough the amount of food in the trough
     */
    public void addEnclosure(int id, double size, double humidity, double temperature, 
                             double vegetationCoverage, int zoneCleanliness, int foodInTrough) {
        Enclosure enclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                                                zoneCleanliness, foodInTrough);
        enclosureManagement.addEnclosure(enclosure);
    }

    /**
     * Removes an enclosure from the management system by ID.
     * 
     * @param id the ID of the enclosure to remove
     */
    public void removeEnclosure(int id) {
        enclosureManagement.removeEnclosure(id);
    }

    /**
     * Updates an existing enclosure in the management system.
     * 
     * @param id the ID of the enclosure to update
     * @param size the new size of the enclosure
     * @param humidity the new humidity level in the enclosure
     * @param temperature the new temperature in the enclosure
     * @param vegetationCoverage the new vegetation coverage in the enclosure
     * @param zoneCleanliness the new cleanliness level of the enclosure
     * @param foodInTrough the new amount of food in the trough
     */
    public void updateEnclosure(int id, double size, double humidity, double temperature, 
                                double vegetationCoverage, int zoneCleanliness, int foodInTrough) {
        Enclosure updatedEnclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                                                        zoneCleanliness, foodInTrough);
        enclosureManagement.updateEnclosure(id, updatedEnclosure);
    }

    /**
     * Retrieves an enclosure by ID.
     * 
     * @param id the ID of the enclosure to retrieve
     * @return the Enclosure object, or null if not found
     */
    public Enclosure getEnclosure(int id) {
        return enclosureManagement.getEnclosure(id);
    }

    /**
     * Retrieves all enclosures.
     * 
     * @return a list of all Enclosure objects
     */
    public List<Enclosure> getAllEnclosures() {
        return enclosureManagement.getAllEnclosures();
    }

    /**
     * Prints the details of an enclosure by ID.
     * 
     * @param id the ID of the enclosure to print
     */
    public void printEnclosureDetails(int id) {
        Enclosure enclosure = getEnclosure(id);
        if (enclosure != null) {
            System.out.println("ID: " + enclosure.getId());
            System.out.println("Size: " + enclosure.getSize());
            System.out.println("Humidity: " + enclosure.getHumidity());
            System.out.println("Temperature: " + enclosure.getTemperature());
            System.out.println("Vegetation Coverage: " + enclosure.getVegetationCoverage());
            System.out.println("Zone Cleanliness: " + enclosure.getZoneCleanliness());
            System.out.println("Food in Trough: " + enclosure.getFoodInTrough());
        } else {
            System.out.println("Enclosure not found");
        }
    }
}
