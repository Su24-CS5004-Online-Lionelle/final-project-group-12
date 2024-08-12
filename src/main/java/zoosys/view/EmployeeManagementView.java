package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The EmployeeManagementView class provides the graphical user interface (GUI)
 * for managing employees in the Employee Management System.
 */
public class EmployeeManagementView extends JFrame {
    private controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructs a new EmployeeManagementView instance.
     * @param controller the controller that manages interactions between the view and the model.
     */
    public EmployeeManagementView(controller controller) {
        this.controller = controller;
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize(); // Initialize the components in the GUI
        populateTable(); // Populate the table with employee data on initialization

        setVisible(true);
    }

    /**
     * Initializes the components in the Employee Management GUI.
     */
    private void initialize() {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons for adding, editing, and deleting employees
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton editEmployeeButton = new JButton("Edit Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        // Set up action listeners for the buttons
        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog(); // Show dialog for adding a new employee
            }
        });

        editEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditEmployeeDialog(); // Show dialog for editing an existing employee
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEmployeeDialog(); // Show dialog for deleting an employee
            }
        });

        // Set up the table to display employee information
        tableModel = new DefaultTableModel(new Object[]{"Name", "Role", "Shift", "Responsibilities"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to the panel
        employeePanel.add(addEmployeeButton);
        employeePanel.add(editEmployeeButton);
        employeePanel.add(deleteEmployeeButton);
        employeePanel.add(scrollPane);

        // Add the panel to the frame
        add(employeePanel);
    }

    /**
     * Displays a dialog for adding a new employee.
     */
    private void showAddEmployeeDialog() {
        JDialog addEmployeeDialog = new JDialog(this, "Add Employee", true);
        addEmployeeDialog.setSize(300, 300);
        addEmployeeDialog.setLayout(new GridLayout(5, 2));

        // Create labels and text fields for employee information
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        // Create submit button
        JButton submitButton = new JButton("Submit");

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(addEmployeeDialog, "Employee name cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                boolean success = controller.addEmployee(name, role, shift, responsibilities);
                if (success) {
                    JOptionPane.showMessageDialog(addEmployeeDialog, "Employee successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    populateTable();
                    addEmployeeDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(addEmployeeDialog, "Employee already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the dialog
        addEmployeeDialog.add(nameLabel);
        addEmployeeDialog.add(nameField);
        addEmployeeDialog.add(roleLabel);
        addEmployeeDialog.add(roleField);
        addEmployeeDialog.add(shiftLabel);
        addEmployeeDialog.add(shiftField);
        addEmployeeDialog.add(responsibilitiesLabel);
        addEmployeeDialog.add(responsibilitiesField);
        addEmployeeDialog.add(submitButton);

        // Display the dialog
        addEmployeeDialog.setVisible(true);
    }

    /**
     * Displays a dialog for editing an existing employee.
     */
    private void showEditEmployeeDialog() {
        JDialog editEmployeeDialog = new JDialog(this, "Edit Employee", true);
        editEmployeeDialog.setSize(300, 300);
        editEmployeeDialog.setLayout(new GridLayout(5, 2));

        // Create labels and text fields for employee information
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        // Create submit button
        JButton submitButton = new JButton("Submit");

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();

                EmployeeImpl updatedEmployee = new EmployeeImpl(name, role);
                updatedEmployee.setShift(shift);
                
                for (String responsibility : responsibilities.split(",")) {
                    updatedEmployee.addResponsibility(responsibility.trim());
                }

                boolean success = controller.updateEmployee(name, updatedEmployee);
                if (!success) {
                    JOptionPane.showMessageDialog(editEmployeeDialog, "Employee not found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    populateTable();
                    editEmployeeDialog.dispose();
                }
            }
        });

        // Add components to the dialog
        editEmployeeDialog.add(nameLabel);
        editEmployeeDialog.add(nameField);
        editEmployeeDialog.add(roleLabel);
        editEmployeeDialog.add(roleField);
        editEmployeeDialog.add(shiftLabel);
        editEmployeeDialog.add(shiftField);
        editEmployeeDialog.add(responsibilitiesLabel);
        editEmployeeDialog.add(responsibilitiesField);
        editEmployeeDialog.add(submitButton);

        // Display the dialog
        editEmployeeDialog.setVisible(true);
    }

    /**
     * Displays a dialog for deleting an existing employee.
     */
    private void showDeleteEmployeeDialog() {
        JDialog deleteEmployeeDialog = new JDialog(this, "Delete Employee", true);
        deleteEmployeeDialog.setSize(300, 100);
        deleteEmployeeDialog.setLayout(new GridLayout(2, 2));

        // Create label and text field for the employee name
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        // Action listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                boolean success = controller.removeEmployee(name);
                if (!success) {
                    JOptionPane.showMessageDialog(deleteEmployeeDialog, "Employee not found", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    populateTable();
                    deleteEmployeeDialog.dispose();
                }
            }
        });

        // Add components to the dialog
        deleteEmployeeDialog.add(nameLabel);
        deleteEmployeeDialog.add(nameField);
        deleteEmployeeDialog.add(deleteButton);

        // Display the dialog
        deleteEmployeeDialog.setVisible(true);
    }

    /**
     * Populates the table with employee data from the model.
     */
    private void populateTable() {
        tableModel.setRowCount(0); // Clear the table
        controller.getEmployeeManagement().readCSV(); // Ensure the CSV data is loaded
        for (Employee employee : controller.getEmployeeManagement().getAllEmployees()) {
            tableModel.addRow(new Object[]{
                employee.getName(),
                employee.getRole(),
                employee.getShift(),
                employee.getResponsibilities()
            });
        }
    }

}
