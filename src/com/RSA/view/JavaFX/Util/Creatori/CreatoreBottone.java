package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * Created by emanuele on 31/05/16.
 */
public class CreatoreBottone {

    public static Button creaBottone(String testo, Pos alignment, Font font, double insets, boolean visibile) {
        Button bottone = new Button();
        bottone.setText(testo);
        layoutizeButton(bottone, alignment, font, insets, visibile);
        return bottone;
    }

    public static void layoutizeButton(Button bottone, Pos alignment, Font font, double insets,boolean visibile) {
        bottone.setAlignment(alignment);
        bottone.setFont(font);
        double insetsIntero = ConvertitorePx.percentualeToPxLarghezza(insets);
        bottone.setStyle("-fx-border-insets: "+insetsIntero+"px;");
        bottone.setVisible(visibile);
    }

}
