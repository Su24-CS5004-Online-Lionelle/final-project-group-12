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
import java.io.FileInputStream;

/**
 * The EnclosureManagement class manages the enclosures, including
 * adding, removing, and updating enclosures.
 */
public class EnclosureManagement {
    private Map<Integer, Enclosure> enclosures;

    public EnclosureManagement() {
        enclosures = new HashMap<>();
        readCSV(); // Read enclosures from CSV on initialization
    }

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
            int id = Integer.parseInt(record[0]);
            double size = Double.parseDouble(record[1]);
            double humidity = Double.parseDouble(record[2]);
            double temperature = Double.parseDouble(record[3]);
            double vegetationCoverage = Double.parseDouble(record[4]);
            int zoneCleanliness = Integer.parseInt(record[5]);
            int foodInTrough = Integer.parseInt(record[6]);
          

            Enclosure enclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                zoneCleanliness, foodInTrough);
            enclosures.put(id, enclosure);
        }
    }

    public void addEnclosure(Enclosure enclosure) {
        enclosures.put(enclosure.getId(), enclosure);
        updateEnclosuresToCSV();
    }

    public void removeEnclosure(int id) {
        enclosures.remove(id);
        updateEnclosuresToCSV();
    }

    public Enclosure getEnclosure(int id) {
        return enclosures.get(id);
    }

    public void updateEnclosure(int id, Enclosure updatedEnclosure) {
        enclosures.put(id, updatedEnclosure);
        updateEnclosuresToCSV();
    }

    public Set<Integer> getEnclosureIds() {
        return enclosures.keySet();
    }

    public List<Enclosure> getAllEnclosures() {
        return enclosures.values().stream().collect(Collectors.toList());
    }

    private void updateEnclosuresToCSV() {
        try (FileWriter writer = new FileWriter("resources/enclosures.csv", false)) {
            writer.append("ID,Size,Humidity,Temperature,VegetationCoverage,ZoneCleanliness,FoodInTrough");
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
