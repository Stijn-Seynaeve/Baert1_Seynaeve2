/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.controller.StartschermController;
import ui.controller.LedenBeheerController;

/**
 *
 * @author Katrien.Deleu
 */
public class VIVESbike extends Application {

    private final Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        showstartscherm();
        stage.show();
    }

    public void showstartscherm() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/Startscherm.fxml"));
            Parent root = loader.load();
            StartschermController startscherm = (StartschermController) loader.getController();
            startscherm.setParent(this);

            Scene scene = new Scene(root);
            stage.setTitle("VIVESBike - Administratie");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("SYSTEEMFOUT bij laden startscherm: " + e.getMessage());
          
        }
    }

   

    public void laadLedenbeheer() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(
                    "view/LedenBeheer.fxml"));

            // controller instellen
            Parent root = loader.load();
            LedenBeheerController controller = (LedenBeheerController) loader.getController();

            // referentie naar hier bewaren in de controller
            controller.setParent(this);

            Scene scene = new Scene(root);
            stage.setTitle("Leden beheren");
            stage.setScene(scene);

        } catch (IOException e) {
            System.out.println("SYSTEEMFOUT bij laden ledenbeheer: " + e.getMessage());
        }

    }

    public Stage getPrimaryStage() {
        return stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
