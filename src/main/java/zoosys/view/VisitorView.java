package zoosys.view;

import zoosys.model.Visit;
import zoosys.model.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The current code will have the controller methods in it.Bowen is responsible for the controller part he can take it out and put it under the controller.
 * It is easier for me to test my gui this way.
 * GUI for Visitor info in the zoo app.
 */
public class VisitorView extends JFrame {
    private Visitor visitor;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField dateTextField;
    private JTextArea resultTextArea;

    /**
     * Constructor for the VisitorView class.
     * Initializes the GUI components and sets up the layout.
     *
     * @param visitor The Visitor object to interact with the visitor data model.
     */
    public VisitorView(Visitor visitor) {
        this.visitor = visitor;
        setTitle("Visitor Information");
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Initialize table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Date", "Entry Time", "Age Group", "Duration", "Animal Feedback", "Cleanliness Feedback", "Pricing Feedback"}, 0);

        // Create JTable with the table model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Populate the table with visitor data from the CSV
        populateTable();

        // Create a panel for date input and displaying results
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        dateTextField = new JTextField();
        JButton submitButton = new JButton("Submit");
        resultTextArea = new JTextArea(10, 50);
        resultTextArea.setEditable(false);

        inputPanel.add(dateTextField, BorderLayout.NORTH);
        inputPanel.add(submitButton, BorderLayout.CENTER);
        inputPanel.add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        // Add action listener to the submit button to trigger statistics display
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateTextField.getText();
                if (isValidDate(date)) {
                    displayStatistics(date);
                } else {
                    showError("Not a valid date! Please enter a date in the format MM_DD_yyyy.");
                }
            }
        });

        setVisible(true);
    }

    /**
     * Validates if the input string is a valid date in the format "M_d_yyyy".
     *
     * @param date The date string to validate.
     * @return True if the date is valid, false otherwise.
     */
    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("M_d_yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Displays an error message dialog.
     *
     * @param message The error message to display.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Populates the JTable with visitor data read from the CSV file.
     */
    private void populateTable() {
        visitor.readCSV(); // Read the CSV file to populate the visits list
        List<Visit> visits = visitor.getVisits();
        for (Visit visit : visits) {
            tableModel.addRow(new Object[]{
                    visit.getDate(),
                    visit.getEntryTime(),
                    visit.getCategory(),
                    visit.getDuration(),
                    visit.getAnimalFeedback(),
                    visit.getCleanlinessFeedback(),
                    visit.getOverallFeedback()
            });
        }
    }

    /**
     * Displays statistics for visits on a specific date.
     *
     * @param date The date for which to display statistics (format: "d_M_yyyy").
     */
    private void displayStatistics(String date) {
        int visitCount = visitor.getVisitsCountByDate(date);
        double revenue = visitor.getRevenueByDate(date);
        int peakHour = visitor.getPeakHourByDate(date);
        double avgAnimalFeedback = visitor.getAverageAnimalFeedbackByDate(date);
        double avgCleanlinessFeedback = visitor.getAverageCleanlinessFeedbackByDate(date);
        double avgPricingFeedback = visitor.getAveragePricingFeedbackByDate(date);

        resultTextArea.setText("Statistics for " + date + ":\n");
        resultTextArea.append("Total Visits: " + visitCount + "\n");
        resultTextArea.append("Total Revenue: " + revenue + "\n");
        resultTextArea.append("Peak Hour: " + peakHour + "\n");
        resultTextArea.append("Average Animal Feedback: " + avgAnimalFeedback + "\n");
        resultTextArea.append("Average Cleanliness Feedback: " + avgCleanlinessFeedback + "\n");
        resultTextArea.append("Average Pricing Feedback: " + avgPricingFeedback + "\n");
    }
}

