package com.RSA.view.JavaFX.View;

import com.RSA.view.JavaFX.Util.Creatori.CreatoreHBox;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreLabel;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreVBox;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by emanuele on 31/05/16.
 */
public class VHome implements Initializable {

    public VBox homeVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeVBox = CreatoreVBox.creaVBox(Pos.CENTER,0,0,100,true);
        HBox bobHBox = CreatoreHBox.creaHBox(Pos.CENTER,0.5,0.5,50,true);
        Label bobNome = CreatoreLabel.creaLabel("Bob", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        HBox aliceHBox = CreatoreHBox.creaHBox(Pos.CENTER,0.5,0.5,50,true);
        bobHBox.getChildren().addAll(bobNome);
        this.homeVBox.getChildren().addAll(bobHBox,aliceHBox);
    }
}