package zoosys.model;

/**
 * Interface that defines the structure for animal entity.
 */
public interface Animal {

    /**
     * Get the id of the specific animal.
     * 
     * @return the id of the animal
     */
    int getAnimal_id();

    /**
     * Set the id for the specific animal.
     * 
     * @param animal_id the animal's ID
     */
    void setAnimal_id(int animal_id);

    /**
     * Get the name of the animal.
     * 
     * @return the animal's name
     */
    String getAnimal_name();

    /**
     * Set the name for the animal.
     * 
     * @param animal_name the animal's name
     */
    void setAnimal_name(String animal_name);

    /**
     * Get the type of animal.
     * 
     * @return animal's type
     */
    String getAnimal_type();

    /**
     * Set the type for the animal
     * 
     * @param animal_type the type of animal
     */
    void setAnimal_type(String animal_type);

    /**
     * Get the age of the animal.
     * 
     * @return the age of the animal
     */
    int getAge();

    /**
     * Set the age for the animal.
     * 
     * @param age the age of the animal
     */
    void setAge(int age);

    /**
     * Get the medical record for the animal.
     * 
     * @return the medical record for the animal
     */
    String getMedical_record();

    /**
     * Set the medical record for the animal.
     * 
     * @param medical_record the medical record for the animal
     */
    void setMedical_record(String medical_record);

    /**
     * Convert animal's attributes to CSV format string.
     * 
     * @return CSV format string.
     */
    String toCSV();
}
