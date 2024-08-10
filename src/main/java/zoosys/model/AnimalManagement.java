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
 * The AnimalManagement class manages the animals, including
 * adding, removing, and updating animals.
 */
public class AnimalManagement {
    private Map<Integer, IAnimal> animals;

    public AnimalManagement() {
        animals = new HashMap<>();
        readCSV(); // Read animals from CSV on initialization
    }

    /**
     * Read the animal data from a CSV file 
     */
    public void readCSV() {
        List<String> lines;
        try {
            InputStream is = new FileInputStream("resources/animals.csv");
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

        // Process each line and add animals
        for (String line : lines) {
            String[] record = line.split(",");
            int animal_id = Integer.parseInt(record[0]);
            String animal_name = record[1];
            String animal_type = record[2];
            int age = Integer.parseInt(record[3]);
            String medical_record = record[4];

            IAnimal animal = new AnimalImpl(animal_id, animal_name, animal_type, age, medical_record);
            animals.put(animal_id, animal);
        }
    }

    /**
     * Add new animal to the collection and update it to AnimalCSV.
     * 
     * @param animal the animal to be added
     */
    public void addAnimal(IAnimal animal) {
        animals.put(animal.getAnimal_id(), animal);
        updateAnimalsToCSV();
    }

    /**
     * Remove animal from the CSV.
     * 
     * @param id the id of the animal to be removed
     */
    public void removeAnimal(int id) {
        animals.remove(id);
        updateAnimalsToCSV();
    }

    /**
     * Removes animal from collection by looking at the id
     * 
     * @param id id of the animal
     * @return animal with id
     */
    public IAnimal getAnimal(int id) {
        return animals.get(id);
    }

    /**
     * Update the detail of the existed animal to the CSV file.
     * 
     * @param id the ID of the animal to be updated
     * @param updatedAnimal the details of the animal
     */
    public void updateAnimal(int id, IAnimal updatedAnimal) {
        animals.put(id, updatedAnimal);
        updateAnimalsToCSV();
    }

    /**
     * Retrieves a set of all animal IDs.
     * 
     * @return a set of all animal IDs
     */
    public Set<Integer> getAnimalIds() {
        return animals.keySet();
    }

    /**
     * Retrieves a list of all animals
     * 
     * @return the list of all animals
     */

    public List<IAnimal> getAllAnimals() {
        return animals.values().stream().collect(Collectors.toList());
    }

    /**
     * update animals to CSV
     */
    private void updateAnimalsToCSV() {
        try (FileWriter writer = new FileWriter("resources/animals.csv", false)) {
            writer.append("ID,Name,Type,Age,MedicalRecord");
            writer.append("\n");
            for (IAnimal animal : getAllAnimals()) {
                writer.append(animal.toCSV());
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
