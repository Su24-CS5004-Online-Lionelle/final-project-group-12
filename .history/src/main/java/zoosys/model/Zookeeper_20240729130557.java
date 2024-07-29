package zoosys.model;

/**
 * The Zookeeper class extends the EmployeeImpl class 
 * and represents a zookeeper in the zoo management system.
 */
public class Zookeeper extends EmployeeImpl {

    /**
     * Constructs a new Zookeeper with the specified name.
     * 
     * @param role the name of the zookeeper
     */
    public Zookeeper(String role) {
        super(role, "Zookeeper");
    }
}
