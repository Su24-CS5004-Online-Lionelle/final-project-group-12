package zoosys.view;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
 
/**
* View class for the Payroll application.
* Provides the graphical user interface for user interaction.
*/
public class view extends JFrame {
    private JTextField animalManagementField;
    private JTextField enclosureManagementField;
    private JTextField employeesManagementField;
    private JTextField visitorsInformationField;
    private JButton helpButton;
    private JButton exitbButton;
 
/**
* Constructor to set up the GUI components.
* Initializes text fields and buttons, and adds them to the frame.
*/
    public view() {
        // Set up the main frame
        setTitle("Zoo Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));
 
        // Initialize and add GUI components
        add(new JLabel("Employee Name:")); // Label for employee name
        employeesManagementField = new JTextField(); // Text field for employee name input
        add(employeeNameField);
 
    /**
     * Gets the employee name from the text field.
     *
     * @return Employee name as a String
     */
    public String getEmployeeName() {
        return employeeNameField.getText();
    }
 
    }
}
