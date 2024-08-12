import zoosys.model.AnimalNew;
import zoosys.model.AnimalCategory;
import zoosys.model.FoodType;
import zoosys.model.Sex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Yangcheng Luo.
 *test for animal related method.
 */
public class AnimalTest {

    private AnimalNew animalNew;

    @BeforeEach
    public void setUp() {
        // Initialize a new instance of AnimalNew before each test
        animalNew = new AnimalNew();
        // Load existing animal data from the CSV file
        animalNew.loadAnimalData();
    }

    @Test
    public void testAddAnimal_NormalCase() {
        int initialCount = animalNew.getTotalAnimals();

        animalNew.addAnimal(AnimalCategory.PREDATOR, "lion5", 4, FoodType.MEAT, false, Sex.MALE);
        int updatedCount = animalNew.getTotalAnimals();

        assertEquals(initialCount + 1, updatedCount, "The total number of animals should increase by 1 after adding a new animal.");

        List<List<String>> allAnimals = animalNew.getAllAnimals();
        boolean animalExists = allAnimals.stream()
                .anyMatch(animal -> animal.get(1).equals("lion5"));
        assertTrue(animalExists, "The new animal 'lion5' should exist in the system.");
    }


    @Test
    public void testRemoveAnimal_NormalCase() {
        animalNew.addAnimal(AnimalCategory.BIRD, "owl11", 2, FoodType.MEAT, false, Sex.FEMALE);
        int initialCount = animalNew.getTotalAnimals();

        animalNew.removeAnimal("owl11");
        int updatedCount = animalNew.getTotalAnimals();

        assertEquals(initialCount - 1, updatedCount, "The total number of animals should decrease by 1 after removing an animal.");

        List<List<String>> allAnimals = animalNew.getAllAnimals();
        boolean animalExists = allAnimals.stream()
                .anyMatch(animal -> animal.get(1).equals("owl11"));
        assertFalse(animalExists, "The animal 'owl11' should no longer exist in the system.");
    }

    @Test
    public void testGetAnimalsNeedingMedicalAttention_NormalCase() {
        animalNew.addAnimal(AnimalCategory.MARINE, "shark1", 7, FoodType.MEAT, true, Sex.MALE);

        List<String> animalsNeedingAttention = animalNew.getAnimalsNeedingMedicalAttention();

        assertTrue(animalsNeedingAttention.contains("shark1"), "The animal 'shark1' should be listed as needing medical attention.");
    }

    @Test
    public void testGetAnimalsCountByCategory_NormalCase() {
        Map<AnimalCategory, Integer> countByCategory = animalNew.getAnimalsCountByCategory();

        assertNotNull(countByCategory, "The map of animal counts by category should not be null.");
        assertTrue(countByCategory.containsKey(AnimalCategory.PREDATOR), "The map should contain an entry for PREDATOR category.");
    }

    @Test
    public void testSaveAndLoadAnimalData_EdgeCase() throws IOException {
        animalNew.addAnimal(AnimalCategory.PRIMATE, "gorilla1", 5, FoodType.VEGETABLE, false, Sex.FEMALE);

        animalNew.saveAnimalData();

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/animal.csv"));
        String line;
        boolean found = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("gorilla1")) {
                found = true;
                break;
            }
        }
        reader.close();

        assertTrue(found, "The animal 'gorilla1' should be saved to the CSV file.");
    }
}
