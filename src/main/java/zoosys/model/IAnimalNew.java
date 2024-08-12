package zoosys.model;

import java.util.List;
import java.util.Map;


/**
 * Yangcheng Luo
 * Interface that have operations for managing animals in the zoo system.
 * With methods to adding, retrieving, and managing animal info from the csv file.
 */

public interface IAnimalNew {
    
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
    void addAnimal(AnimalCategory category, String name, int age, FoodType foodType, boolean medicalAttentionNeeded, Sex sex);
    
    /**
     * Retrieves a list of all animals.
     *
     * @return A list where each element is a list of strings representing an animal'.
     */
    List<List<String>> getAllAnimals();

    
    /**
     * Get the total number of animals.
     *
     * @return The total number of animals.
     */
    int getTotalAnimals();

    /**
     * Counts number of animal in different categories.
     *
     * @return A map where the key is the AnimalCategory and the value is the count of animals in that category.
     */
    Map<AnimalCategory, Integer> getAnimalsCountByCategory();

    
    /**
     * Calculates the total foods needed for all animals, categorized by food type.
     *
     * @return A map where the key is the FoodType and the value is the total amount of food required.
     */
    Map<FoodType, Integer> getTotalFoodNeeded();

    /**
     * Retrieves a list of animals that need medical attention.
     *
     * @return A list of strings, each representing the details of an animal that requires medical attention.
     */
    List<String> getAnimalsNeedingMedicalAttention();
    /**
     * Loads animal data from the csv file under resources.
     */
    void loadAnimalData();
    
    /**
     * Saves the updated animal info to the csv file under resources.
     */
    void saveAnimalData(); 

    /**
     * Removes an animal by name.
     * @param name The name of the animal to be removed.
     */
    void removeAnimal(String name); 
