package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Created by emanuele on 31/05/16.
 */
public class CreatoreLabel {

    public static Label creaLabel(String testo, Font font, double minWidthPercentuale, TextAlignment textAlignment, double paddingSinistroPercentuale, double paddingDestroPercentuale, boolean visibile) {
        Label label = new Label(testo);
        layoutizeLabel(label, font, minWidthPercentuale, textAlignment, paddingSinistroPercentuale, paddingDestroPercentuale, visibile);
        return label;
    }

    public static void layoutizeLabel(Label label, Font font, double minWidthPercentuale, TextAlignment textAlignment, double paddingSinistroPercentuale, double paddingDestroPercentuale, boolean visibile) {
        label.setFont(font);
        double minWidth = ConvertitorePx.percentualeToPxLarghezza(minWidthPercentuale);
        label.setMinWidth(minWidth);
        label.setTextAlignment(textAlignment);
        double paddingDestro = ConvertitorePx.percentualeToPxLarghezza(paddingDestroPercentuale);
        double paddingSinistro = ConvertitorePx.percentualeToPxLarghezza(paddingSinistroPercentuale);
        label.setStyle("-fx-border-insets: 0px " + paddingDestro + "px 0px " + paddingSinistro + "px; -fx-background-insets: 0px " + paddingDestro + "px 0px " + paddingSinistro + "px; -fx-padding: 0px " + paddingDestro + "px 0px " + paddingSinistro + "px;");
        label.setVisible(visibile);
    }

}
