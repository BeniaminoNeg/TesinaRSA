package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Created by emanuele on 31/05/16.
 */
public class CreatoreHBox {

    public static HBox creaHBox(Pos posizione, double paddingAltoPercentuale, double paddingBassoPercentuale, double maxWidthPercentuale, boolean visibile) {
        HBox hBox = new HBox();
        layoutizeHBox(hBox, posizione, paddingAltoPercentuale, paddingBassoPercentuale, maxWidthPercentuale, visibile);
        return hBox;
    }

    public static void layoutizeHBox(HBox hBox, Pos posizione, double paddingAltoPercentuale, double paddingBassoPercentuale, double maxWidthPercentuale, boolean visibile) {
        hBox.setAlignment(posizione);
        double paddingAlto = ConvertitorePx.percentualeToPxAltezza(paddingAltoPercentuale);
        double paddingBasso = ConvertitorePx.percentualeToPxAltezza(paddingBassoPercentuale);
        hBox.setStyle("-fx-padding: " + paddingAlto + "px 0px " + paddingBasso + "px 0px;");
        double maxWidth = ConvertitorePx.percentualeToPxLarghezza(maxWidthPercentuale);
        hBox.setMaxWidth(maxWidth);
        hBox.setMinWidth(maxWidth);
        hBox.setVisible(visibile);
    }

}
