package zoosys.model;

import java.util.List;
import java.util.Map;

public interface IAnimalNew {
    void addAnimal(AnimalCategory category, String name, int age, FoodType foodType, boolean medicalAttentionNeeded, Sex sex);
    List<List<String>> getAllAnimals();
    List<String> getAnimal(int index);
    int getTotalAnimals();
    Map<AnimalCategory, Integer> getAnimalsCountByCategory();
    Map<FoodType, Integer> getTotalFoodNeeded();
    List<String> getAnimalsNeedingMedicalAttention();
    void loadAnimalData();
    void saveAnimalData();  // New method to update the CSV file
    void removeAnimal(String name);  // New method to remove an animal by name
}
