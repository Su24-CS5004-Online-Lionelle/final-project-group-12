package zoosys.model;

/**
 * AnimalImpl implements the IAnimal interface.
 */
public class AnimalImpl implements IAnimal {

    private int animal_id;
    private String animal_name;
    private String animal_type;
    private int age;
    private String medical_record;

    /**
     * Constructs an AnimalImpl instance.
     * 
     * @param animal_id the unique ID of the animal
     * @param animal_name the name of the animal
     * @param animal_type the type of the animal
     * @param age the age of the animal
     * @param medical_record the medical record of the animal
     */
    public AnimalImpl(int animal_id, String animal_name, String animal_type, int age, String medical_record) {
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.animal_type = animal_type;
        this.age = age;
        this.medical_record = medical_record;
    }

    @Override
    public int getAnimal_id() {
        return animal_id;
    }

    @Override
    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    @Override
    public String getAnimal_name() {
        return animal_name;
    }

    @Override
    public void setAnimal_name(String animal_name) {
        this.animal_name = animal_name;
    }

    @Override
    public String getAnimal_type() {
        return animal_type;
    }

    @Override
    public void setAnimal_type(String animal_type) {
        this.animal_type = animal_type;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getMedical_record() {
        return medical_record;
    }

    @Override
    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    @Override
    public String toCSV() {
        return animal_id + "," +
               animal_name + "," +
               animal_type + "," +
               age + "," +
               medical_record;
    }
}