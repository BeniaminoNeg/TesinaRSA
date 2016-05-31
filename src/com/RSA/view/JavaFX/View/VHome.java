package com.RSA.view.JavaFX.View;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreHBox;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreLabel;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreVBox;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        this.homeVBox.setAlignment(Pos.TOP_CENTER);
        double width = ConvertitorePx.percentualeToPxLarghezza(100);
        double height = ConvertitorePx.percentualeToPxAltezza(100);
        this.homeVBox.setPrefSize(width,height);
        HBox wrapperHBox = CreatoreHBox.creaHBox(Pos.CENTER,0,0,100,true);
        HBox bobHBox = CreatoreHBox.creaHBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label bobNome = CreatoreLabel.creaLabel("Bob", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        Button bobKeyButton =
        HBox eveHBox = CreatoreHBox.creaHBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label eveNome = CreatoreLabel.creaLabel("Eve", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        HBox aliceHBox = CreatoreHBox.creaHBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label aliceNome = CreatoreLabel.creaLabel("Alice", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        bobHBox.getChildren().addAll(bobNome);
        eveHBox.getChildren().addAll(eveNome);
        aliceHBox.getChildren().addAll(aliceNome);
        wrapperHBox.getChildren().addAll(bobHBox,eveHBox,aliceHBox);
        this.homeVBox.getChildren().add(wrapperHBox);
    }
}
