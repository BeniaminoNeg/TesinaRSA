package com.RSA.view.JavaFX.View;

import com.RSA.model.algoritmoRSA.*;
import com.RSA.view.JavaFX.Util.ConvertitorePx;
import com.RSA.view.JavaFX.Util.Creatori.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by emanuele & beniamino & beniamino on 31/05/16.
 */
public class VHome implements Initializable {

    public VBox homeVBox;
    public ScrollPane scrollPaneBob;
    public ScrollPane scrollPaneAlice;
    public ScrollPane scrollPaneAttuale;
    public Button scriviMessaggioBobButton;
    public Button scriviMessaggioAliceButton;
    public Button leggiUltimoMessaggioAliceButton;
    public Button leggiUltimoMessaggioBobButton;

    public Button trovaChiaveBobButton;
    public Button trovaChiaveAliceButton;

    public ToggleGroup sicuraBobToggleGroup;
    public ToggleGroup sicuraAliceToggleGroup;

    public VBox eveVBox;

    public Client bob;
    public Client alice;
    public Cracker eve;

    public ComboBox<String> comboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.homeVBox.setAlignment(Pos.TOP_CENTER);
        double width = ConvertitorePx.percentualeToPxLarghezza(100);
        double height = ConvertitorePx.percentualeToPxAltezza(100);
        this.homeVBox.setPrefSize(width, height);
        HBox wrapperHBox = CreatoreHBox.creaHBox(Pos.CENTER, 0, 0, 100, true);
        VBox bobVBox = creaBob();
        VBox aliceVBox = creaAlice();
        VBox eveVBox = creaEve();
        wrapperHBox.getChildren().addAll(bobVBox, eveVBox, aliceVBox);
        this.homeVBox.getChildren().add(wrapperHBox);
    }

    public VBox creaBob() {
        VBox bobVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER, 0.5, 0.5, 33.3, true);
        Label bobNome = CreatoreLabel.creaLabel("Bob", Font.font("System", FontWeight.BOLD, 16), 5, TextAlignment.CENTER, 0, 0, true);
        List<String> testi = new ArrayList<>();
        testi.add("Vulnerabile");
        testi.add("Non Vulnerabile");
        List<RadioButton> radio = CreatoreRadioButton.creaRadioButton(testi, Font.font("System", FontWeight.BOLD, 14), TextAlignment.LEFT, 1, 1, true);
        sicuraBobToggleGroup = radio.get(0).getToggleGroup();
        sicuraBobToggleGroup.selectToggle(radio.get(1));
        Button bobKeyButton = CreatoreBottone.creaBottone("Genera Chiave", Pos.CENTER, Font.font("System", FontWeight.BOLD, 16), 0.5, true);
        bobKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                bobKeyButton.setText("Genera altre chiavi");
                scrollPaneAttuale = scrollPaneBob;
                if (((RadioButton) sicuraBobToggleGroup.getSelectedToggle()).getText().equals("Non Vulnerabile")) {
                    bob = new Client("Bob", true);
                } else {
                    bob = new Client("Bob", false);
                }
                if (scrollPaneBob == null) {
                    scrollPaneBob = creaScrollPane(bob);
                    bobVBox.getChildren().add(scrollPaneBob);
                    scriviMessaggioBobButton = creaMessaggioButton(bob);
                    bobVBox.getChildren().add(scriviMessaggioBobButton);
                    leggiUltimoMessaggioBobButton = creaLeggiMessaggioButton(bob);
                    bobVBox.getChildren().add(leggiUltimoMessaggioBobButton);

                    trovaChiaveBobButton = creaAttaccoButton(bob);
                    eveVBox.getChildren().addAll(trovaChiaveBobButton);

                } else {
                    creaScrollPane(bob);
                }
            }
        });
        bobVBox.getChildren().addAll(bobNome, radio.get(0), radio.get(1), bobKeyButton);
        return bobVBox;
    }


    public VBox creaEve() {
        eve = new Cracker();

        eve.setAttacco("Wiener");
        eveVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER, 0.5, 0.5, 33.3, true);
        Label eveNome = CreatoreLabel.creaLabel("Eve", Font.font("System", FontWeight.BOLD, 16), 5, TextAlignment.CENTER, 0, 0, true);
        Label tipologiaAttacco = CreatoreLabel.creaLabel("Tipologia attacco:", Font.font("System", FontWeight.NORMAL, 14), 5, TextAlignment.CENTER, 0, 0, true);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Wiener",
                        "Fattorizzazione"
                );
        comboBox = new ComboBox(options);
        comboBox.getSelectionModel().select(0);
        eveVBox.getChildren().addAll(eveNome, tipologiaAttacco, comboBox);
        return eveVBox;
    }

    private Button creaAttaccoButton(Client client) {
        String nomeClient = client.get_nomeClient();
        Button bottone = CreatoreBottone.creaBottone(" Trova la chiave di " + nomeClient, Pos.CENTER, Font.font("System", FontWeight.BOLD, 16), 0, true);
        bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chiave di " + nomeClient);
                alert.setHeaderText("Risultato attacco");
                String attacco = comboBox.getSelectionModel().getSelectedItem();
                eve.setAttacco(attacco);
                switch (nomeClient) {
                    case "Bob":
                        if (bob != null) {
                            // Recupero la chiave di Bob.
                            PublicKey publicKeyBob = ArchivioChiaviPubbliche.getInstance().ottieniChiaveClient("Bob");
                            // Provo a calcolare la chiave privata di Bob.
                            PrivateKey privateKeyBob = eve.ottieniChiavePrivataDaChiavePubblica(publicKeyBob);
                            // Controllo se ho ottenuto la chiave
                            //System.out.println("Chiave Bob " + privateKeyBob.toString());
                            if (privateKeyBob != null) {
                                String messaggio = "P: " + privateKeyBob.get_p().toString() + "\nQ: " + privateKeyBob.get_q().toString() + "\nD: " + privateKeyBob.get_d().toString();
                                alert.setContentText(messaggio);
                            } else {
                                alert.setContentText("Chiave non trovata!");
                            }
                        } else {
                            alert.setContentText("Bob non è stato inizializzato!");
                        }
                        alert.showAndWait();
                        break;
                    case "Alice":
                        if (alice != null) {
                            // Recupero la chiave di Alice.
                            PublicKey publicKeyAlice = ArchivioChiaviPubbliche.getInstance().ottieniChiaveClient("Alice");
                            // Provo a calcolare la chiave privata di Alice.
                            PrivateKey privateKeyAlice = eve.ottieniChiavePrivataDaChiavePubblica(publicKeyAlice);
                            // Controllo se ho ottenuto la chiave
                            //System.out.println("Chiave Bob " + privateKeyBob.toString());
                            if (privateKeyAlice != null) {
                                String messaggio = "P: " + privateKeyAlice.get_p().toString() + "\nQ: " + privateKeyAlice.get_q().toString() + "\nD: " + privateKeyAlice.get_d().toString();
                                alert.setContentText(messaggio);
                            } else {
                                alert.setContentText("Chiave non trovata!");
                            }
                        } else {
                            alert.setContentText("Alice non è stato inizializzato!");
                        }
                        alert.showAndWait();
                        break;
                    default:
                        break;
                }
            }
        });
        return bottone;
    }

    public VBox creaAlice() {
        VBox aliceVBox = CreatoreVBox.creaVBox(Pos.TOP_CENTER, 0.5, 0.5, 33.3, true);
        Label aliceNome = CreatoreLabel.creaLabel("Alice", Font.font("System", FontWeight.BOLD, 16), 5, TextAlignment.CENTER, 0, 0, true);
        Button aliceKeyButton = CreatoreBottone.creaBottone("Genera Chiave", Pos.CENTER, Font.font("System", FontWeight.BOLD, 16), 0.5, true);
        List<String> testi = new ArrayList<>();
        testi.add("Vulnerabile");
        testi.add("Non Vulnerabile");
        List<RadioButton> radio = CreatoreRadioButton.creaRadioButton(testi, Font.font("System", FontWeight.BOLD, 14), TextAlignment.CENTER, 1, 1, true);
        sicuraAliceToggleGroup = radio.get(0).getToggleGroup();
        sicuraAliceToggleGroup.selectToggle(radio.get(1));
        aliceKeyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                aliceKeyButton.setText("Genera altre chiavi");
                scrollPaneAttuale = scrollPaneAlice;
                if (((RadioButton) sicuraAliceToggleGroup.getSelectedToggle()).getText().equals("Non Vulnerabile")) {
                    alice = new Client("Alice", true);
                } else {
                    alice = new Client("Alice", false);
                }
                if (scrollPaneAlice == null) {
                    scrollPaneAlice = creaScrollPane(alice);
                    aliceVBox.getChildren().add(scrollPaneAlice);

                    scriviMessaggioAliceButton = creaMessaggioButton(alice);
                    aliceVBox.getChildren().add(scriviMessaggioAliceButton);

                    leggiUltimoMessaggioAliceButton = creaLeggiMessaggioButton(alice);
                    aliceVBox.getChildren().add(leggiUltimoMessaggioAliceButton);

                    trovaChiaveAliceButton = creaAttaccoButton(alice);
                    eveVBox.getChildren().addAll(trovaChiaveAliceButton);
                } else {
                    creaScrollPane(alice);
                }
            }
        });
        aliceVBox.getChildren().addAll(aliceNome, radio.get(0), radio.get(1), aliceKeyButton);
        return aliceVBox;
    }

    public ScrollPane creaScrollPane(Client client) {
        VBox content = null;
        ScrollPane scrollPane = null;
        if (scrollPaneAttuale != null) {
            scrollPane = scrollPaneAttuale;
            content = (VBox) scrollPaneAttuale.getContent();
            content.getChildren().clear();
        } else {
            content = CreatoreVBox.creaVBox(Pos.TOP_LEFT, 0, 0, 200, true);
            scrollPane = CreatoreScrollPane.creaScrollPane(content, 31, 2, 2, true);
        }
        Label privata = CreatoreLabel.creaLabel("Private Key:", Font.font("System", FontWeight.BOLD, 16), 5, TextAlignment.LEFT, 0.5, 0, true);
        Label p = CreatoreLabel.creaLabel("p: " + client.get_privateKey().get_p().toString(), Font.font("System", FontPosture.REGULAR, 12), 200, TextAlignment.LEFT, 0.5, 0, true);
        Label q = CreatoreLabel.creaLabel("q: " + client.get_privateKey().get_q().toString(), Font.font("System", FontPosture.REGULAR, 12), 200, TextAlignment.LEFT, 0.5, 0, true);
        Label d = CreatoreLabel.creaLabel("d: " + client.get_privateKey().get_d().toString(), Font.font("System", FontPosture.REGULAR, 12), 200, TextAlignment.LEFT, 0.5, 0, true);
        Label pubblica = CreatoreLabel.creaLabel("Public Key:", Font.font("System", FontWeight.BOLD, 16), 5, TextAlignment.LEFT, 0.5, 0, true);
        Label n = CreatoreLabel.creaLabel("n: " + client.get_publicKey().get_n().toString(), Font.font("System", FontPosture.REGULAR, 12), 200, TextAlignment.LEFT, 0.5, 0, true);
        Label e = CreatoreLabel.creaLabel("e: " + client.get_publicKey().get_e().toString(), Font.font("System", FontPosture.REGULAR, 12), 200, TextAlignment.LEFT, 0.5, 0, true);
        content.getChildren().addAll(privata, p, q, d, pubblica, n, e);


        return scrollPane;
    }

    public Button creaMessaggioButton(Client client) {
        Button bottone = CreatoreBottone.creaBottone("Invia Messaggio", Pos.CENTER, Font.font("System", FontWeight.BOLD, 16), 0, true);
        bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("messaggio");
                dialog.setTitle("Invio messaggio");
                dialog.setHeaderText("Inserisci il messaggio");
                dialog.setContentText("Messaggio da inviare:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    String messaggio = result.get();
                    MessaggioChiaro messaggioChiaro;
                    if (client.get_nomeClient().equals("Bob")) {
                        messaggioChiaro = new MessaggioChiaro(client, alice, messaggio);
                    } else {
                        messaggioChiaro = new MessaggioChiaro(client, bob, messaggio);
                    }
                    client.inviaMessaggioToClient(messaggioChiaro);
                }


            }
        });
        return bottone;
    }

    public Button creaLeggiMessaggioButton(Client client) {
        Button bottone = CreatoreBottone.creaBottone("Leggi Ultimo Messaggio Ricevuto", Pos.CENTER, Font.font("System", FontWeight.BOLD, 16), 0, true);
        bottone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BigInteger messaggioCifrato = client.getUltimoMessaggioRicevutoCifrato().get_messaggioCifrato();
                if (messaggioCifrato != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ultimo Messaggio");
                    alert.setHeaderText("Ultimo messaggio ricevuto");
                    alert.setContentText("Messaggio in chiaro: " + client.getAndRemoveUltimoMessaggioRicevutoInChiaro().get_messaggioChiaro() + "\n" + "Messaggio cifrato: " + messaggioCifrato);
                    alert.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label) node).setMinHeight(Region.USE_PREF_SIZE));
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
