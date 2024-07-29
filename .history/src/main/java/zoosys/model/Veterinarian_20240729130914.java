package zoosys.model;

/**
 * The Veterinarian class extends the EmployeeImpl class 
 * and represents a veterinarian in the zoo management system.
 */
public class Veterinarian extends EmployeeImpl {

    /**
     * Constructs a new Veterinarian with the specified role.
     * 
     * @param role the role of the veterinarian
     */
    public Veterinarian(String role) {
        super(role, "Veterinarian");
    }
}
