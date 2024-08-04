package zoosys;

import zoosys.model.*;
import zoosys.view.*;
import zoosys.controller.*;
import javax.swing.SwingUtilities;

public class ZooApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("Starting Zoo management Application...");
                View view = new View();
                model model = new model();
                Controller controller = controller(view, model);

                view.setVisible(true);
                System.out.println("Zoo View is now visible.");
            }
        });
    }
}