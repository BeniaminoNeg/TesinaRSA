package com.RSA.view.JavaFX.View;

import com.RSA.model.algoritmoRSA.Client;
import com.RSA.view.JavaFX.Util.ConvertitorePx;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreBottone;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreHBox;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreLabel;
import com.RSA.view.JavaFX.Util.Creatori.CreatoreVBox;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
        VBox bobVBox = creaBob();
        VBox eveVBox = creaEve();
        VBox aliceVBox = creaAlice();
        wrapperHBox.getChildren().addAll(bobVBox,eveVBox,aliceVBox);
        this.homeVBox.getChildren().add(wrapperHBox);
    }

    public VBox creaBob() {
        VBox bobVBox = CreatoreVBox.creaVBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label bobNome = CreatoreLabel.creaLabel("Bob", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        Button bobKeyButton = CreatoreBottone.creaBottone("Genera Chiave",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0.5,true);
        bobKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Client bob = new Client("Bob",true);
                System.out.println(bob.get_privateKey().get_d().toString());
            }
        });
        bobVBox.getChildren().addAll(bobNome,bobKeyButton);
        return bobVBox;
    }


    public VBox creaEve() {
        VBox eveVBox = CreatoreVBox.creaVBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label eveNome = CreatoreLabel.creaLabel("Eve", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        eveVBox.getChildren().addAll(eveNome);
        return eveVBox;
    }

    public VBox creaAlice() {
        VBox aliceVBox = CreatoreVBox.creaVBox(Pos.CENTER,0.5,0.5,33.3,true);
        Label aliceNome = CreatoreLabel.creaLabel("Alice", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        Button aliceKeyButton = CreatoreBottone.creaBottone("Genera Chiave",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0.5,true);
        aliceKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Client alice = new Client("Alice", true);
                System.out.println(alice.get_privateKey().get_d().toString());
            }
        });
        aliceVBox.getChildren().addAll(aliceNome,aliceKeyButton);
        return aliceVBox;
    }
}
