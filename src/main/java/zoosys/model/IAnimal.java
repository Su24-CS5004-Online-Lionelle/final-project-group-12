package zoosys.model;

/**
 * Interface that defines the methods that must be implemented by the class of animal in zoo.
 */
public interface IAnimal {

    /**
     * Gets the unique Id of the animal.
     * 
     * @return id of the animal
     */
    int getAnimal_id();

    /**
     * Set the id for the animal.
     * 
     * @param animal_id id to be set
     */
    void setAnimal_id(int animal_id);

    /**
     * Gets the name of the animal.
     * 
     * @return the animal's name
     */
    String getAnimal_name();

    /**
     * Sets the name of the animal.
     * 
     * @param animal_name the name to be set
     */
    void setAnimal_name(String animal_name);
    
    /**
     * Get animal's type.
     * 
     * @return type of the animal
     */
    String getAnimal_type();

    /**
     * Set the type of the animal
     * 
     * @param animal_type animal type to be set
     */
    void setAnimal_type(String animal_type);

    /**
     * Gets the age of the animal.
     * 
     * @return the age of the animal
     */
    int getAge();

    /**
     * Set the age for the animal.
     * 
     * @param age animal age to be set
     */
    void setAge(int age);

    /**
     * Get the medical record for the animal
     * 
     * @return medical record of the animal
     */
    String getMedical_record();

    /**
     * Set the medical record for the animal
     * 
     * @param medical_record medical record to be set
     */
    void setMedical_record(String medical_record);

    /**
     * Generate CSV representation of animal data.
     * 
     * @return the CSV representation
     */
    String toCSV();
}

