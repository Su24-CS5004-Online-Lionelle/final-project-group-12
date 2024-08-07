package zoosys.model;

import java.util.List;

/**
 * The Animal class represents an animal in the zoo management system.
 */
public class Animal {
    /**
     * The unique ID of the animal.
     */
    private int animal_id;

    /**
     * The name of the animal.
     */
    private String animal_name;

    /**
     * The type of the animal.
     */
    private String animal_type;

    /**
     * The age of the animal.
     */
    private int age;

    /**
     * The list of feeding times for the animal.
     */
    private List<Feeding_Time> feeding_times;

    /**
     * The medical record of the animal.
     */
    private String medical_record;

    /**
     * Constructs an Animal instance.
     * 
     * @param animal_id the unique ID of the animal
     * @param animal_name the name of the animal
     * @param animal_type the type of the animal
     * @param age the age of the animal
     * @param feeding_times the list of feeding times for the animal
     * @param medical_record the medical record of the animal
     */
    public Animal(int animal_id, String animal_name, String animal_type, int age, List<Feeding_Time> feeding_times, String medical_record) {
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.animal_type = animal_type;
        this.age = age;
        this.feeding_times = feeding_times;
        this.medical_record = medical_record;
    }

    // Getters and Setters

    /**
     * Gets the ID of the animal.
     * 
     * @return the ID of the animal
     */
    public int getAnimal_id() {
        return animal_id;
    }

    /**
     * Sets the ID of the animal.
     * 
     * @param animal_id the ID of the animal
     */
    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    /**
     * Gets the name of the animal.
     * 
     * @return the name of the animal
     */
    public String getAnimal_name() {
        return animal_name;
    }

    /**
     * Sets the name of the animal.
     * 
     * @param animal_name the name of the animal
     */
    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    /**
     * Gets the type of the animal.
     * 
     * @return the type of the animal
     */
    public String getAnimal_type() {
        return animal_type;
    }

    /**
     * Sets the type of the animal.
     * 
     * @param animal_type the type of the animal
     */
    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    /**
     * Gets the age of the animal.
     * 
     * @return the age of the animal
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the animal.
     * 
     * @param age the age of the animal
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the list of feeding times for the animal.
     * 
     * @return the list of feeding times
     */
    public List<Feeding_Time> getFeeding_times() {
        return feeding_times;
    }

    /**
     * Sets the list of feeding times for the animal.
     * 
     * @param feeding_times the list of feeding times
     */
    public void setFeeding_times(List<Feeding_Time> feeding_times) {
        this.feeding_times = feeding_times;
    }

    /**
     * Gets the medical record of the animal.
     * 
     * @return the medical record of the animal
     */
    public String getMedical_record() {
        return medical_record;
    }

    /**
     * Sets the medical record of the animal.
     * 
     * @param medical_record the medical record of the animal
     */
    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }
}
