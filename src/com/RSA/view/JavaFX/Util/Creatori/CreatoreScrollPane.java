package com.RSA.view.JavaFX.Util.Creatori;

import com.RSA.view.JavaFX.Util.ConvertitorePx;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Created by emanuele & beniamino on 01/06/16.
 */
public class CreatoreScrollPane {

    public static ScrollPane creaScrollPane(VBox content, double minWidthPercentuale, double paddingAltoPercentuale, double paddingBassoPercentuale, boolean visibile) {
        ScrollPane scrollPane = new ScrollPane();
        layoutizeScrollPane(scrollPane, minWidthPercentuale, paddingAltoPercentuale, paddingBassoPercentuale, visibile);
        riempiScrollPane(scrollPane, content);
        return scrollPane;
    }

    public static void layoutizeScrollPane(ScrollPane scrollPane, double minWidthPercentuale, double paddingAltoPercentuale, double paddingBassoPercentuale, boolean visibile) {
        double paddingAlto = ConvertitorePx.percentualeToPxAltezza(paddingAltoPercentuale);
        double paddingBasso = ConvertitorePx.percentualeToPxAltezza(paddingBassoPercentuale);
        scrollPane.setStyle("-fx-padding: " + paddingAlto + "px 0px " + paddingBasso + "px 0px;");
        double minWidth = ConvertitorePx.percentualeToPxLarghezza(minWidthPercentuale);
        scrollPane.setMaxWidth(minWidth);
        scrollPane.setMinWidth(minWidth);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVisible(visibile);
    }

    public static void riempiScrollPane(ScrollPane scrollPane, VBox content) {
        scrollPane.setContent(content);
    }
}
