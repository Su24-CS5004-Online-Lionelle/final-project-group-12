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

                // Initialize the models
                EmployeeManagement employeeManagement = new EmployeeManagement();
                Enclosures enclosureManagement = new Enclosures(1000, 50, 22, 75, 90, 50, EnclosureType.TEMPERATE);
                Visitor visitorManagement = new Visitor();

                // Initialize the view
                view zooView = new view();

                // Initialize the controller
                controller controller = new Controller(employeeManagement, enclosureManagement, visitorManagement);

                zooView.setVisible(true);
                System.out.println("Zoo View is now visible.");
            }
        });
    }
}
