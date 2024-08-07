package zoosys;

import zoosys.model.*;
import zoosys.view.*;
import zoosys.controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class ZooApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting Zoo Management Application...");

        // Initialize the models
        EmployeeManagement employeeManagement = new EmployeeManagement();
        Enclosures enclosureManagement = new Enclosures(1000, 50, 22, 75, 90, 50, EnclosureType.TEMPERATE);
        Visitor visitorManagement = new Visitor();

        // Initialize the view
        view view = new view(primaryStage);

        // Initialize the controller
        controller controller = new controller(employeeManagement, enclosureManagement, visitorManagement);

        System.out.println("Zoo View is now visible.");
    }
}
