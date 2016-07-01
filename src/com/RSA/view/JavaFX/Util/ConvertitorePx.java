package com.RSA.view.JavaFX.Util;

import javafx.stage.Screen;

/**
 * Created by emanuele on 24/05/16.
 */
public class ConvertitorePx {

    private final static double larghezzaPercentuale = (Screen.getPrimary().getBounds().getWidth()) / 100;
    private final static double altezzaPercentuale = (Screen.getPrimary().getBounds().getHeight()) / 100;

    public static double percentualeToPxLarghezza(double percentuale) {
        return Math.round(percentuale * larghezzaPercentuale);
    }

    public static double percentualeToPxAltezza(double percentuale) {
        return Math.round(percentuale * altezzaPercentuale);
    }

}
