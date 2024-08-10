package zoosys.controller;

import zoosys.model.*;
import java.util.List;
import java.util.Set;

/**
 * The AnimalController class manages the operations related to animals using the AnimalManagement model. 
 */
public class AnimalController {

    private AnimalManagement animalManagement;

    /**
     * Constructs an AnimalController instance with the specified AnimalManagement.
     * 
     * @param animalManagement the AnimalManagement instance to manage animal operations
     */
    public AnimalController(AnimalManagement animalManagement) {
        this.animalManagement = animalManagement;
    }

    /**
     * Adds a new animal to the system.
     * 
     * @param animal_id the unique ID of the animal
     * @param animal_name the name of the animal
     * @param animal_type the type of the animal
     * @param age the age of the animal
     * @param medical_record the medical record of the animal
     */
    public void addAnimal(int animal_id, String animal_name, String animal_type, int age, String medical_record) {
        IAnimal animal = new AnimalImpl(animal_id, animal_name, animal_type, age, medical_record);
        animalManagement.addAnimal(animal);
    }

    /**
     * Removes an animal from the system by its ID.
     * 
     * @param animal_id the unique ID of the animal to be removed
     */
    public void removeAnimal(int animal_id) {
        animalManagement.removeAnimal(animal_id);
    }

    /**
     * Retrieves an animal by its ID.
     * 
     * @param animal_id the unique ID of the animal to retrieve
     * @return the IAnimal object representing the animal, or null if not found
     */
    public IAnimal getAnimal(int animal_id) {
        return animalManagement.getAnimal(animal_id);
    }

    /**
     * Updates an existing animal's details.
     * 
     * @param animal_id the unique ID of the animal to be updated
     * @param animal_name the new name of the animal
     * @param animal_type the new type of the animal
     * @param age the new age of the animal
     * @param medical_record the new medical record of the animal
     */
    public void updateAnimal(int animal_id, String animal_name, String animal_type, int age, String medical_record) {
        IAnimal updatedAnimal = new AnimalImpl(animal_id, animal_name, animal_type, age, medical_record);
        animalManagement.updateAnimal(animal_id, updatedAnimal);
    }

    /**
     * Retrieves a list of all animals.
     * 
     * @return a List of IAnimal objects representing all animals
     */
    public List<IAnimal> getAllAnimals() {
        return animalManagement.getAllAnimals();
    }

    /**
     * Retrieves a set of all animal IDs.
     * 
     * @return a Set of integers representing the IDs of all animals
     */
    public Set<Integer> getAllAnimalIds() {
        return animalManagement.getAnimalIds();
    }
}
