package zoosys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Zookeeper class implements the Employee interface 
 * and represents a zookeeper in the zoo management system.
 */
public class Zookeeper implements Employee {
    private String name;
    private String role;
    private String shift;
    private List<String> responsibilities;
    private List<String> tasks;

    /**
     * Constructs a new Zookeeper with the specified name.
     * @param name the name of the zookeeper
     */
    public Zookeeper(String name) {
        this.name = name;
        this.role = "Zookeeper";
        this.responsibilities = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the name of the zookeeper.
     * @return the name of the zookeeper
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the role of the zookeeper.
     * @return the role of the zookeeper
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Sets the shift for the zookeeper.
     * @param shift the shift to be set
     */
    @Override
    public void setShift(String shift) {
        this.shift = shift;
    }

    /**
     * Gets the shift of the zookeeper.
     * @return the shift of the zookeeper
     */
    @Override
    public String getShift() {
        return shift;
    }

    /**
     * Adds a responsibility to the zookeeper's list of responsibilities.
     * @param responsibility the responsibility to add
     */
    @Override
    public void addResponsibility(String responsibility) {
        responsibilities.add(responsibility);
    }

    /**
     * Adds a task to the zookeeper's list of tasks.
     * @param task the task to add
     */
    @Override
    public void addTask(String task) {
        tasks.add(task);
    }

    /**
     * Gets the zookeeper's list of responsibilities.
     * @return a string of responsibilities
     */
    @Override
    public String getResponsibilities() {
        return String.join(", ", responsibilities);
    }

    /**
     * Gets the zookeeper's list of tasks.
     * @return a string of tasks
     */
    @Override
    public String getTasks() {
        return String.join(", ", tasks);
    }
}
