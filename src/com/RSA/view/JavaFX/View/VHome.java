package com.RSA.view.JavaFX.View;

import com.RSA.model.algoritmoRSA.Client;
import com.RSA.model.algoritmoRSA.Cracker;
import com.RSA.model.algoritmoRSA.GeneratoreChiavi;
import com.RSA.model.algoritmoRSA.MessaggioChiaro;
import com.RSA.view.JavaFX.Util.ConvertitorePx;
import com.RSA.view.JavaFX.Util.Creatori.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by emanuele on 31/05/16.
 */
public class VHome implements Initializable {

    public VBox homeVBox;
    public ScrollPane scrollPaneBob;
    public ScrollPane scrollPaneAlice;
    public ScrollPane scrollPaneAttuale;
    public Button scriviMessaggioBobButton;
    public Button scriviMessaggioAliceButton;

    public Client bob;
    public Client alice;
    public Cracker eve;

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
        VBox bobVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER,0.5,0.5,33.3,true);
        Label bobNome = CreatoreLabel.creaLabel("Bob", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        Button bobKeyButton = CreatoreBottone.creaBottone("Genera Chiave",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0.5,true);
        bob = new Client("Bob",true);
        bobKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bobKeyButton.setText("Genera altre chiavi");
                scrollPaneAttuale = scrollPaneBob;
                if (scrollPaneBob == null) {
                    scrollPaneBob = creaScrollPane(bob);
                    bobVBox.getChildren().add(scrollPaneBob);
                    scriviMessaggioBobButton = creaMessaggioButton(bob);
                    bobVBox.getChildren().add(scriviMessaggioBobButton);
                    leggiUltimoMessaggioButton = creaLeggiMessaggioButton(bob);
                } else {
                    creaScrollPane(bob);
                }
            }
        });
        bobVBox.getChildren().addAll(bobNome,bobKeyButton);
        return bobVBox;
    }


    public VBox creaEve() {
        VBox eveVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER,0.5,0.5,33.3,true);
        Label eveNome = CreatoreLabel.creaLabel("Eve", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        eveVBox.getChildren().addAll(eveNome);
        return eveVBox;
    }

    public VBox creaAlice() {
        VBox aliceVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER,0.5,0.5,33.3,true);
        Label aliceNome = CreatoreLabel.creaLabel("Alice", Font.font("System", FontWeight.BOLD,16),5, TextAlignment.CENTER,0,0,true);
        Button aliceKeyButton = CreatoreBottone.creaBottone("Genera Chiave",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0.5,true);
        alice = new Client("Alice", true);
        aliceKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aliceKeyButton.setText("Genera altre chiavi");
                scrollPaneAttuale = scrollPaneAlice;
                if (scrollPaneAlice == null) {
                    scrollPaneAlice = creaScrollPane(alice);
                    aliceVBox.getChildren().add(scrollPaneAlice);
                    scriviMessaggioAliceButton = creaMessaggioButton(alice);
                    aliceVBox.getChildren().add(scriviMessaggioAliceButton);
                } else {
                    creaScrollPane(alice);
                }
            }
        });
        aliceVBox.getChildren().addAll(aliceNome,aliceKeyButton);
        return aliceVBox;
    }

    public ScrollPane creaScrollPane(Client client) {
        VBox content = null;
        ScrollPane scrollPane = null;
        if (scrollPaneAttuale != null) {
            scrollPane = scrollPaneAttuale;
            content = (VBox) scrollPaneAttuale.getContent();
            content.getChildren().clear();
            GeneratoreChiavi.generaChiavi(client,true);
        } else {
            content = CreatoreVBox.creaVBox(Pos.TOP_LEFT,0,0,200,true);
            scrollPane = CreatoreScrollPane.creaScrollPane(content,31,2,2,true);
        }
        Label privata = CreatoreLabel.creaLabel("Private Key:",Font.font("System",FontWeight.BOLD,16),5,TextAlignment.LEFT,0.5,0,true);
        Label p = CreatoreLabel.creaLabel("p: "+client.get_privateKey().get_p().toString(),Font.font("System", FontPosture.REGULAR,12),200,TextAlignment.LEFT,0.5,0,true);
        Label q = CreatoreLabel.creaLabel("q: "+client.get_privateKey().get_q().toString(),Font.font("System", FontPosture.REGULAR,12),200,TextAlignment.LEFT,0.5,0,true);
        Label d = CreatoreLabel.creaLabel("d: "+client.get_privateKey().get_d().toString(),Font.font("System", FontPosture.REGULAR,12),200,TextAlignment.LEFT,0.5,0,true);
        Label pubblica = CreatoreLabel.creaLabel("Public Key:",Font.font("System",FontWeight.BOLD,16),5,TextAlignment.LEFT,0.5,0,true);
        Label n = CreatoreLabel.creaLabel("n: "+client.get_publicKey().get_n().toString(),Font.font("System", FontPosture.REGULAR,12),200,TextAlignment.LEFT,0.5,0,true);
        Label e = CreatoreLabel.creaLabel("e: "+client.get_publicKey().get_e().toString(),Font.font("System", FontPosture.REGULAR,12),200,TextAlignment.LEFT,0.5,0,true);
        content.getChildren().addAll(privata,p,q,d,pubblica,n,e);


        return scrollPane;
    }

    public Button creaMessaggioButton(Client client) {
        Button bottone = CreatoreBottone.creaBottone("Invia Messaggio",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0,true);
        bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("messaggio");
                dialog.setTitle("Invio messaggio");
                dialog.setHeaderText("Inserisci il messaggio");
                dialog.setContentText("Messaggio da inviare:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    String messaggio = result.get();
                    MessaggioChiaro messaggioChiaro;
                    if (client.get_nomeClient().equals("Bob")){
                        messaggioChiaro = new MessaggioChiaro(client,alice,messaggio);
                    }
                    else{
                        messaggioChiaro = new MessaggioChiaro(client,bob,messaggio);
                    }
                    client.inviaMessaggioToClient(messaggioChiaro);
                }
            }
        });
        return bottone;
    }

    public Button creaLeggiMessaggioButton(Client client) {
        Button bottone = CreatoreBottone.creaBottone("Leggi Ultimo Messaggio Ricevuto",Pos.CENTER,Font.font("System",FontWeight.BOLD,16),0,true);
        bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (client.getUltimoMessaggioRicevutoCifrato() != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ultimo Messaggio");
                    alert.setHeaderText("Ultimo messaggio ricevuto");
                    alert.setContentText(client.getAndRemoveUltimoMessaggioRicevutoInChiaro().get_messaggioChiaro());
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ultimo Messaggio");
                    alert.setHeaderText("Ultimo messaggio ricevuto");
                    alert.setContentText("Nessun messaggio ricevuto");
                    alert.showAndWait();
                }
            }
        });
        return bottone;
    }
}
