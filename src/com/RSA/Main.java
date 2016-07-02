/**
 *
 */
package com.RSA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author emanuele & Beniamino
 */
public class Main extends Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/JavaFX/xml/home.fxml"));

        primaryStage.setTitle("Rsa");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root));

        //primaryStage.getIcons().add(new Image("file:../../../../Image/tankicon.png"));

        /*
        primaryStage.getIcons().add(
                new Image(
                        TankWarMain.class.getResourceAsStream( "../../../../Image/tankicon.png" )));
        */


        primaryStage.show();

    }
}
