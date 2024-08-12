package zoosys.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Class implementing the IAnimalNew interface.
 * Make sure all methods in the IAnimalNew interface can be properly used.
 */
public class AnimalNew implements IAnimalNew {

    private List<List<String>> animalData;

    /**
     * Constructor initializing the animal data list.
     */
    public AnimalNew() {
        this.animalData = new ArrayList<>();
    }

    
    /**
     * Adds a new animal to the system.
     *
     * @param category               The category of the animal.(category can be found in AnimalCategoty.java)
     * @param name                   The name of the animal.(No same name is allowed for easy management)
     * @param age                    The age of the animal in years.
     * @param foodType               The type of food the animal consumes (foodtype can be found in FoodType.java).
     * @param medicalAttentionNeeded If the animal requires medical attention.
     * @param sex                    The sex of the animal (Male, Female).
     */

    @Override
    public void addAnimal(AnimalCategory category, String name, int age, FoodType foodType, boolean medicalAttentionNeeded, Sex sex) {
        List<String> newAnimal = new ArrayList<>();
        newAnimal.add(category.name());
        newAnimal.add(name);
        newAnimal.add(String.valueOf(age));
        newAnimal.add(foodType.name());
        newAnimal.add(medicalAttentionNeeded ? "yes" : "no");
        newAnimal.add(sex.name());
        animalData.add(newAnimal);
        saveAnimalData();
    }

    /**
     * Removes an animal by name.
     * @param name The name of the animal to be removed.
     */
    @Override
    public void removeAnimal(String name) {
        animalData.removeIf(animal -> animal.get(1).equalsIgnoreCase(name));
        saveAnimalData();  // Update the CSV file after removing
    }

    /**
     * Saves the updated animal info to the csv file under resources.
     */
    @Override
    public void saveAnimalData() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/animal.csv"), StandardCharsets.UTF_8))) {
            writer.write("Category,Name,Age,Food Type,Medical Attention Needed,Sex\n");
            for (List<String> animal : animalData) {
                writer.write(String.join(",", animal) + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all animals.
     *
     * @return A list where each element is a list of strings representing an animal'.
     */
    @Override
    public List<List<String>> getAllAnimals() {
        return animalData;
    }

    /**
     * Get the total number of animals.
     *
     * @return The total number of animals.
     */
    @Override
    public int getTotalAnimals() {
        return animalData.size();
    }

    /**
     * Counts number of animal in different categories.
     *
     * @return A map where the key is the AnimalCategory and the value is the count of animals in that category.
     */
    @Override
    public Map<AnimalCategory, Integer> getAnimalsCountByCategory() {
        return animalData.stream()
                .collect(Collectors.groupingBy(
                        animal -> AnimalCategory.valueOf(animal.get(0).toUpperCase()),
                        Collectors.summingInt(animal -> 1)
                ));
    }

    /**
     * Calculates the total foods needed for all animals, categorized by food type.
     *
     * @return A map where the key is the FoodType and the value is the total amount of food required.
     */
    @Override
    public Map<FoodType, Integer> getTotalFoodNeeded() {
        return animalData.stream()
                .collect(Collectors.groupingBy(
                        animal -> FoodType.valueOf(animal.get(3).toUpperCase()),
                        Collectors.summingInt(animal -> 1)
                ));
    }

    /**
     * Retrieves a list of animals that need medical attention.
     *
     * @return A list of strings, each representing the details of an animal that requires medical attention.
     */
    @Override
    public List<String> getAnimalsNeedingMedicalAttention() {
        return animalData.stream()
                .filter(animal -> animal.get(4).equalsIgnoreCase("yes"))
                .map(animal -> animal.get(1)) // Get animal name
                .collect(Collectors.toList());
    }

    /**
     * Loads animal data from the csv file under resources.
     */
    @Override
    public void loadAnimalData() {
        List<String> lines;
        try {
            InputStream is = getClass().getResourceAsStream("/animal.csv");
            if (is == null) {
                throw new FileNotFoundException("Resource file not found: /animal.csv");
            }
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            lines = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        if (lines == null || lines.isEmpty()) {
            System.err.println("File is empty or null.");
            return;
        }

        // Remove the header line
        lines.remove(0);

        // Process each line and add animals
        for (String line : lines) {
            try {
                String[] record = line.split(",");
                if (record.length != 6) {
                    System.err.println("Skipping invalid line (incorrect number of columns): " + line);
                    continue;
                }

                String category = record[0].trim();
                String name = record[1].trim();
                int age = Integer.parseInt(record[2].trim());
                String foodType = record[3].trim();
                boolean medicalAttentionNeeded = record[4].trim().equalsIgnoreCase("yes");
                String sex = record[5].trim();

                addAnimal(
                        AnimalCategory.valueOf(category.toUpperCase()),
                        name,
                        age,
                        FoodType.valueOf(foodType.toUpperCase()),
                        medicalAttentionNeeded,
                        Sex.valueOf(sex.toUpperCase())
                );
            } catch (Exception e) {
                System.err.println("Error processing line: " + line);
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates an existing animal's details based on the provided information and updates the data source.
     *
     * @param category               The new category of the animal.(category can be found in AnimalCategoty.java)
     * @param name                   The new name of the animal.(No same name is allowed for easy management)
     * @param age                    The new age of the animal in years.
     * @param foodType               The new type of food the animal consumes (foodtype can be found in FoodType.java).
     * @param medicalAttentionNeeded Update if animal needs medical attention
     * @param sex                    The new sex of the animal (Male, Female).
     */
    public void updateAnimal(String existingName, AnimalCategory category, String newName, int age, FoodType foodType, boolean medicalAttentionNeeded, Sex sex) {
        for (List<String> animal : animalData) {
            if (animal.get(1).equalsIgnoreCase(existingName)) {
                animal.set(0, category.name());
                animal.set(1, newName);
                animal.set(2, String.valueOf(age));
                animal.set(3, foodType.name());
                animal.set(4, medicalAttentionNeeded ? "yes" : "no");
                animal.set(5, sex.name());
                saveAnimalData();  // Update the CSV file
                break;
            }
        }
    }
}
