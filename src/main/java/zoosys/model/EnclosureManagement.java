package zoosys.model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import zoosys.controller.controller;

import java.io.FileInputStream;

/**
 * The EnclosureManagement class manages the enclosures
 */
public class EnclosureManagement {
    private Map<String, Enclosure> enclosures;

    /**
     * Constructs a new EnclosureManagement instance.
     * Initializes the enclosures map and reads the enclosure data from a CSV file.
     */
    public EnclosureManagement() {
        enclosures = new HashMap<>();
        readCSV(); // Read enclosures from CSV on initialization
    }

    public EnclosureManagement(controller controller) {
        enclosures = new HashMap<>();
        readCSV();
    }

    /**
     * Reads the enclosures from a CSV file and populates the enclosure map.
     * The CSV file is expected to be in the resources directory.
     */
    public void readCSV() {
        List<String> lines;
        try {
            InputStream is = new FileInputStream("resources/enclosures.csv");
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
    
        if (lines == null || lines.isEmpty()) {
            return;
        }
    
        // Process header and remove it from lines
        lines.remove(0);
    
        // Process each line and add enclosures
        for (String line : lines) {
            String[] record = line.split(",");

            if(record.length < 7) {
                System.err.println("Skipping malformed line: " + line);
                continue;
            }
            double size = Double.parseDouble(record[0]);
            double humidity = Double.parseDouble(record[1]);
            double temperature = Double.parseDouble(record[2]);
            double vegetationCoverage = Double.parseDouble(record[3]);
            double zoneCleanliness = Double.parseDouble(record[4]);
            double foodInTrough = Double.parseDouble(record[5]);
            EnclosureType enclosureType = EnclosureType.valueOf(record[6]);

            Enclosure enclosure = new EnclosureImpl(size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough, enclosureType);
            String key = enclosureType.name();
            enclosures.put(key, enclosure);
        }
    }
    
    
    /**
     * Adds an enclosure to the management system.
     * 
     * @param enclosure the enclosure to add
     * @return true if the enclosure was successfully added, false if it already exists
     */
    public boolean addEnclosure(Enclosure enclosure) {
        String key = enclosure.getEnclosureType().name();
        if (enclosures.containsKey(key)) {
            return false; // when enclosure already exists, do not add
        }
        enclosures.put(key, enclosure);
        updateEnclosuresToCSV();
        return true; // Enclosure successfully added
    }

    /**
     * Removes an enclosure from the management system.
     * 
     * @param type of the enclosure to be removed
     * @param size of the enclosure to be removed
     * @return true if the enclosure was found and removed
     */
    public boolean removeEnclosure(EnclosureType type) {
        String key = type.name();
        if (enclosures.containsKey(key)) {
            enclosures.remove(key);
            updateEnclosuresToCSV();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves an enclosure by type and size.
     * 
     * @param type of the enclosure
     * @param size of the enclosure
     * @return the enclosure object
     */
    public Enclosure getEnclosure(EnclosureType type) {
        return enclosures.get(type.name());
    }

    /**
     * Updates the enclosure's information.
     * 
     * @param type type of the enclosure to be updated
     * @param size size of the enclosure to be updated
     * @param updatedEnclosure the updated enclosure information
     * @return true if the enclosure was found and updated, false otherwise
     */
    public boolean updateEnclosure(EnclosureType type, Enclosure updatedEnclosure) {
        String key = type.name();
        if (enclosures.containsKey(key)) {
            enclosures.put(key, updatedEnclosure);
            updateEnclosuresToCSV();
            return true;
        } else {
            System.out.println("Enclosure not found");
            return false;
        }
    }

    /**
     * Print the details of an enclosure.
     * 
     * @param type type of the enclosure
     * @param size size of the enclosure
     */
    public void printEnclosureDetails(EnclosureType type, double size) {
        Enclosure enclosure = enclosures.get(type.name() + "_" + size);
        if (enclosure != null) {
            System.out.println("Size: " + enclosure.getSize());
            System.out.println("Humidity: " + enclosure.getHumidity());
            System.out.println("Temperature: " + enclosure.getTemperature());
            System.out.println("Vegetation Coverage: " + enclosure.getVegetationCoverage());
            System.out.println("Zone Cleanliness: " + enclosure.getZoneCleanliness());
            System.out.println("Food in Trough: " + enclosure.getFoodInTrough());
            System.out.println("Type: " + enclosure.getEnclosureType());
        } else {
            System.out.println("Enclosure not found");
        }
    }

    /**
     * Retrieves the types of all enclosures.
     * 
     * @return set of the enclosure types
     */
    public Set<EnclosureType> getEnclosureTypes() {
        return enclosures.values().stream()
                         .map(Enclosure::getEnclosureType)
                         .collect(Collectors.toSet());
    }

    /**
     * Returns a list of all enclosures.
     * 
     * @return a list of all enclosures
     */
    public List<Enclosure> getAllEnclosures() {
        return enclosures.values().stream().collect(Collectors.toList());
    }

    /**
     * Updates the CSV file with all enclosure details.
     * The CSV file is overwritten with the current list of enclosures.
     */
    private void updateEnclosuresToCSV() {
        try (FileWriter writer = new FileWriter("resources/enclosures.csv", false)) {
            writer.append("Size,Humidity,Temperature,Vegetation Coverage,Zone Cleanliness,Food in Trough,Type");
            writer.append("\n");
            for (Enclosure enclosure : getAllEnclosures()) {
                writer.append(enclosure.toCSV());
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
