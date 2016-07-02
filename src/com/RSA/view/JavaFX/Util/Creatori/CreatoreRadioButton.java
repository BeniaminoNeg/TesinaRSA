package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emanuele & beniamino on 30/06/16.
 */
public class CreatoreRadioButton {

    public static List<RadioButton> creaRadioButton(List<String> testi, Font font, TextAlignment textAlignment, double paddingSinistraPercentuale, double paddingDestraPercentuale, boolean visibile) {
        List lista = new ArrayList<>();
        ToggleGroup toggleGroup = new ToggleGroup();
        for (String testo : testi) {
            RadioButton radioButton = new RadioButton(testo);
            radioButton.setToggleGroup(toggleGroup);
            layoutizeRadioButton(radioButton, font, textAlignment, paddingSinistraPercentuale, paddingDestraPercentuale, visibile);
            lista.add(radioButton);
        }
        return lista;
    }

    public static void layoutizeRadioButton(RadioButton radioButton, Font font, TextAlignment textAlignment, double paddingSinistraPercentuale, double paddingDestraPercentuale, boolean visibile) {
        radioButton.setFont(font);
        radioButton.setTextAlignment(textAlignment);
        radioButton.setStyle("-fx-padding: 0px " + ConvertitorePx.percentualeToPxLarghezza(paddingDestraPercentuale) + "px 0px " + ConvertitorePx.percentualeToPxLarghezza(paddingSinistraPercentuale) + "px;");
        radioButton.setVisible(visibile);
    }

}
