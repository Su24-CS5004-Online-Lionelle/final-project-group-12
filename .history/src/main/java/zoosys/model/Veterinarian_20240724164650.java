package zoosys.model;

/**
 * The Veterinarian class extends the EmployeeImpl class 
 * and represents a veterinarian in the zoo management system.
 */
public class Veterinarian extends EmployeeImpl {

    /**
     * Constructs a new Veterinarian with the specified name.
     * @param name the name of the veterinarian
     */
    public Veterinarian(String name) {
        super(name, "Veterinarian");
    }
}
