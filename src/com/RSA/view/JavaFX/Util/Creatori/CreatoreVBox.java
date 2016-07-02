package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * Created by emanuele & beniamino on 31/05/16.
 */
public class CreatoreVBox extends VBox {

    public static VBox creaVBox(Pos posizione, double paddingAltoPercentuale, double paddingBassoPercentuale, double maxWidthPercentuale, boolean visibile) {
        VBox vBox = new VBox();
        layoutizeVBox(vBox, posizione, paddingAltoPercentuale, paddingBassoPercentuale, maxWidthPercentuale, visibile);
        return vBox;
    }

    public static void layoutizeVBox(VBox vBox, Pos posizione, double paddingAltoPercentuale, double paddingBassoPercentuale, double maxWidthPercentuale, boolean visibile) {
        vBox.setAlignment(posizione);
        double paddingAlto = ConvertitorePx.percentualeToPxAltezza(paddingAltoPercentuale);
        double paddingBasso = ConvertitorePx.percentualeToPxAltezza(paddingBassoPercentuale);
        vBox.setStyle("-fx-border-insets: " + paddingAlto + "px 0px " + paddingBasso + "px 0px; -fx-background-insets: " + paddingAlto + "px 0px " + paddingBasso + "px 0px; -fx-padding: " + paddingAlto + "px 0px " + paddingBasso + "px 0px;");
        double maxWidth = ConvertitorePx.percentualeToPxLarghezza(maxWidthPercentuale);
        double spacing = ConvertitorePx.percentualeToPxAltezza(0.65);
        vBox.setSpacing(spacing);
        vBox.setMaxWidth(maxWidth);
        vBox.setMinWidth(maxWidth);
        vBox.setVisible(visibile);
    }

}
