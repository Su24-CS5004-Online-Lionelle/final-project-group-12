package zoosys.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeImpl implements Employee {
    private String name;
    private String role;
    private String shift;
    private List<String> responsibilities;

    public EmployeeImpl(String name, String role) {
        this.name = name;
        this.role = role;
        this.responsibilities = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String getShift() {
        return shift;
    }

    @Override
    public void addResponsibility(String responsibility) {
        this.responsibilities.add(responsibility);
    }

    @Override
    public String getResponsibilities() {
        return String.join(", ", responsibilities);
    }

    public String toCSV() {
        return String.join(",", name, role, shift, getResponsibilities());
    }
}
