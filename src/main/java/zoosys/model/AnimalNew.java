package zoosys.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnimalNew implements IAnimalNew {

    private List<List<String>> animalData;

    public AnimalNew() {
        this.animalData = new ArrayList<>();
    }

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
        saveAnimalData();  // Update the CSV file after adding
    }

    @Override
    public void removeAnimal(String name) {
        animalData.removeIf(animal -> animal.get(1).equalsIgnoreCase(name));
        saveAnimalData();  // Update the CSV file after removing
    }

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

    @Override
    public List<List<String>> getAllAnimals() {
        return animalData;
    }

    @Override
    public List<String> getAnimal(int index) {
        if (index >= 0 && index < animalData.size()) {
            return animalData.get(index);
        } else {
            return null; // Or throw an exception
        }
    }

    @Override
    public int getTotalAnimals() {
        return animalData.size();
    }

    @Override
    public Map<AnimalCategory, Integer> getAnimalsCountByCategory() {
        return animalData.stream()
                .collect(Collectors.groupingBy(
                        animal -> AnimalCategory.valueOf(animal.get(0).toUpperCase()),
                        Collectors.summingInt(animal -> 1)
                ));
    }

    @Override
    public Map<FoodType, Integer> getTotalFoodNeeded() {
        return animalData.stream()
                .collect(Collectors.groupingBy(
                        animal -> FoodType.valueOf(animal.get(3).toUpperCase()),
                        Collectors.summingInt(animal -> 1)
                ));
    }

    @Override
    public List<String> getAnimalsNeedingMedicalAttention() {
        return animalData.stream()
                .filter(animal -> animal.get(4).equalsIgnoreCase("yes"))
                .map(animal -> animal.get(1)) // Get animal name
                .collect(Collectors.toList());
    }

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
