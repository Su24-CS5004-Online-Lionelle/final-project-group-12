package zoosys.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
    private BorderPane layout;

    // Constructor to initialize the main layout
    public View(Stage stage) {
        this.layout = new BorderPane();
        initialize(stage);
    }

    // Method to set up the initial layout and components
    private void initialize(Stage stage) {
        stage.setTitle("G12 Zoo Management");

        // Create a VBox for the side menu buttons
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10); // Set spacing between buttons

        // Create buttons for each management section
        Button animalButton = new Button("ANIMAL Management");
        Button enclosureButton = new Button("ENCLOSURE Management");
        Button employeeButton = new Button("EMPLOYEES Management");
        Button visitorsButton = new Button("VISITORS Information");
        Button helpButton = new Button("HELP");
        Button exitButton = new Button("EXIT");

        // Add buttons to the VBox
        buttonBox.getChildren().addAll(animalButton, enclosureButton, employeeButton, visitorsButton, helpButton, exitButton);

        // Create a Pane for the display area
        Pane displayPane = new Pane();
        displayPane.setStyle("-fx-background-color: #A8DADC; -fx-border-color: black; -fx-border-width: 1;");

        // Add the button VBox to the left and the display Pane to the center of the layout
        layout.setLeft(buttonBox);
        layout.setCenter(displayPane);

        // Create a Scene with the layout and set it on the stage
        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Set action events for each button
        animalButton.setOnAction(e -> openAnimalManagementWindow());
        enclosureButton.setOnAction(e -> openEnclosureManagementWindow());
        employeeButton.setOnAction(e -> openEmployeeManagementWindow());
        visitorsButton.setOnAction(e -> openVisitorsInformationWindow());
        helpButton.setOnAction(e -> showHelpDialog());
        exitButton.setOnAction(e -> stage.close());
    }

    // Method to open the Animal Management window
    private void openAnimalManagementWindow() {
        Stage animalStage = new Stage();
        VBox animalLayout = new VBox();
        animalLayout.setSpacing(10);
        animalLayout.setStyle("-fx-padding: 10;");

        Button addAnimalButton = new Button("Add Animal");
        Button editAnimalButton = new Button("Edit Animal");
        Button deleteAnimalButton = new Button("Delete Animal");

        animalLayout.getChildren().addAll(addAnimalButton, editAnimalButton, deleteAnimalButton);

        Scene animalScene = new Scene(animalLayout, 400, 300);
        animalStage.setScene(animalScene);
        animalStage.show();
    }

    // Method to open the Enclosure Management window
    private void openEnclosureManagementWindow() {
        Stage enclosureStage = new Stage();
        VBox enclosureLayout = new VBox();
        enclosureLayout.setSpacing(10);
        enclosureLayout.setStyle("-fx-padding: 10;");

        Button addEnclosureButton = new Button("Add Enclosure");
        Button editEnclosureButton = new Button("Edit Enclosure");
        Button deletEnclosureButton = new Button("Delete Enclosure");

        enclosureLayout.getChildern().addAll(addEnclosureButton, editEnclosureButton, deletEnclosureButton);
        
        Scene enclosureScene = new Scene(enclosureLayout, 400, 300);
        enclosureStage.setScene(enclosureScene);
        enclosureStage.show();
    }
    

    // Method to open the Employee Management window
    private void openEmployeeManagementWindow() {
        Stage employeeStage = new Stage();
        VBox employeeLayout = new VBox();
        employeeLayout.setSpacing(10);
        employeeLayout.setStyle("-fx-padding: 10;");

        Button addEmployeeButton = new Button("Add Employee");
        Button editEmployeeButton = new Button("Edit Employee");
        Button deleteEmployeeButton = new Button("Delete Employee");

        employeeLayout.getChildren().addAll(addEmployeeButton, editEmployeeButton, deleteEmployeeButton);

        Scene employeeScene = new Scene(employeeLayout, 400, 300);
        employeeStage.setScene(employeeScene);
        employeeStage.show();
    }

    // Method to open the Visitors Information window
    private void openVisitorsInformationWindow() {

    }

    // Method to show the Help dialog
    private void showHelpDialog() {
        Stage helpStage = new Stage();
        VBox helpLayout = new VBox();
        helpLayout.setSpacing(10);
        helpLayout.setStyle("-fx-padding: 10;");

        Button faqButton = new Button("FAQ");
        Button contactSupportButton = new Button("Contact Support");

        helpLayout.getChildren().addAll(faqButton, contactSupportButton);

        Scene helpScene = new Scene(helpLayout, 400, 300);
        helpStage.setScene(helpScene);
        helpStage.show();
    }
}
