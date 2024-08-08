package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Employee;
import zoosys.model.EmployeeImpl;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementView extends JFrame {
    private controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public EmployeeManagementView(controller controller) {
        this.controller = controller;
        setTitle("Employee Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();

        tableModel = new DefaultTableModel(new Object[]{"Name","Role","Shift","Responsibilities"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void initialize() {
        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton editEmployeeButton = new JButton("Edit Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog();
            }
        });

        editEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditEmployeeDialog();
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEmployeeDialog();
            }
        });

        employeePanel.add(addEmployeeButton);
        employeePanel.add(editEmployeeButton);
        employeePanel.add(deleteEmployeeButton);

        add(employeePanel);
    }

    private void showAddEmployeeDialog() {
        JDialog addEmployeeDialog = new JDialog(this, "Add Employee", true);
        addEmployeeDialog.setSize(300, 300);
        addEmployeeDialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();
                controller.addEmployee(name, role, shift, responsibilities);

                addEmployeeDialog.dispose();
            }
        });

        addEmployeeDialog.add(nameLabel);
        addEmployeeDialog.add(nameField);
        addEmployeeDialog.add(roleLabel);
        addEmployeeDialog.add(roleField);
        addEmployeeDialog.add(shiftLabel);
        addEmployeeDialog.add(shiftField);
        addEmployeeDialog.add(responsibilitiesLabel);
        addEmployeeDialog.add(responsibilitiesField);
        addEmployeeDialog.add(submitButton);

        addEmployeeDialog.setVisible(true);
    }

    private void showEditEmployeeDialog() {
        JDialog editEmployeeDialog = new JDialog(this, "Edit Employee", true);
        editEmployeeDialog.setSize(300, 300);
        editEmployeeDialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();

                EmployeeImpl updatedEmployee = new EmployeeImpl(name, role);
                updatedEmployee.setShift(shift);
                updatedEmployee.addResponsibility(responsibilities);

                controller.updateEmployee(name, updatedEmployee);

                editEmployeeDialog.dispose();
            }
        });

        editEmployeeDialog.add(nameLabel);
        editEmployeeDialog.add(nameField);
        editEmployeeDialog.add(roleLabel);
        editEmployeeDialog.add(roleField);
        editEmployeeDialog.add(shiftLabel);
        editEmployeeDialog.add(shiftField);
        editEmployeeDialog.add(responsibilitiesLabel);
        editEmployeeDialog.add(responsibilitiesField);
        editEmployeeDialog.add(submitButton);

        editEmployeeDialog.setVisible(true);
    }

    private void showDeleteEmployeeDialog() {
        JDialog deleteEmployeeDialog = new JDialog(this, "Delete Employee", true);
        deleteEmployeeDialog.setSize(300, 100);
        deleteEmployeeDialog.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                controller.removeEmployee(name);

                deleteEmployeeDialog.dispose();
            }
        });

        deleteEmployeeDialog.add(nameLabel);
        deleteEmployeeDialog.add(nameField);
        deleteEmployeeDialog.add(deleteButton);

        deleteEmployeeDialog.setVisible(true);
    }

    private void populateTable() {
        for (Employee employee : controller.getEmployeeManagement().getAllEmployees()) {
            tableModel.addRow(new Object[]{
                employee
            });
        }
    }
}
