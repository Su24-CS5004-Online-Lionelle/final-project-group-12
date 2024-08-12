package zoosys.view;

import zoosys.model.Visit;
import zoosys.model.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Yangcheng Luo
 * The current code will have the controller methods in it.Bowen is responsible for the controller part he can take it out and put it under the controller.
 * It is easier for me to test my gui this way.
 * GUI for Visitor info.
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Date", "Entry Time", "Age Group", "Duration", "Animal Feedback", "Cleanliness Feedback", "Pricing Feedback"}, 0);

        // Create JTable with the table model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Populate table with visitor data
        populateTable();

        // Create panel for date input and results
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

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateTextField.getText();
                displayStatistics(date);
            }
        });

        setVisible(true);
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
     * @param date The date for which to display statistics (format: "YYYY-MM-DD").
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

    public static void main(String[] args) {
        // Create a Visitor instance
        Visitor visitor = new Visitor();

        // Create and display the GUI
        new VisitorView(visitor);
    }
}
