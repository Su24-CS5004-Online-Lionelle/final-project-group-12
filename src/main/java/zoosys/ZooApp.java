package zoosys;

import zoosys.model.*;
import zoosys.view.*;
import zoosys.controller.*;

import javax.swing.SwingUtilities;

public class ZooApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Starting Zoo Management Application...");
                IVisitor visitor = new Visitor();
                visitor.readCSV();

                // Initialize the models
                EmployeeManagement employeeManagement = new EmployeeManagement();
                EnclosureManagement enclosureManagement = new EnclosureManagement();
                Visitor visitorManagement = new Visitor();

                // Initialize the controller
                Controller controller = new Controller(employeeManagement, enclosureManagement, visitorManagement);

                AnimalManagementView animalView = new AnimalManagementView(controller, 1);
                // Initialize the view
                view view = new view(controller);

                view.setVisible(true);
                System.out.println("Zoo View is now visible.");
            }
        });
    }
}
