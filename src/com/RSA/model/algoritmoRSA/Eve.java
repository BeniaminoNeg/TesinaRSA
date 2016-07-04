/**
 *
 */
package com.RSA.model.algoritmoRSA;

import com.RSA.model.algoritmoAttaccoEsponenteBassiWiener.AlgoritmoAttaccoEsponenteBassoWienerDefaultStrategy;
import com.RSA.model.algoritmoAttaccoEsponenteBassiWiener.IAlgoritmoAttaccoEsponenteBassoWienerStrategy;
import com.RSA.model.algoritmoAttaccoFattorizzazione.AlgoritmoAttaccoFattorizzazioneStrategy;
import com.RSA.model.algoritmoAttaccoFattorizzazione.IAlgoritmoAttaccoFattorizzazioneStrategy;

/**
 * Questa classe rappresenta un generico cracker (Eve) che vuole cercare di ottenere la chiave privata
 * di un client (Bob, Alice).
 *
 * @author emanuele & beniamino
 */
public class Eve {
    /**
     * Metodo per cercare di ottenere la chiave privata di un client a partire dalla sua chiave pubblica.
     *
     * @param publicKey Chiave pubblica del client, del quale si vuole conoscere la privata.
     * @return Chiave privata se esito positivo. Null altrimenti.
     */

    private String attacco;

    public PrivateKey ottieniChiavePrivataDaChiavePubblica(PublicKey publicKey) {

        PrivateKey privateKeyClient = null;
        // Scelgo l'algoritmo di attacco.
        switch (this.attacco) {
            case "Wiener":
                IAlgoritmoAttaccoEsponenteBassoWienerStrategy algoritmoAttaccoEsponenteBassoWienerStrategy = new AlgoritmoAttaccoEsponenteBassoWienerDefaultStrategy();
                // Provo ad ottenere la chiave
                privateKeyClient = algoritmoAttaccoEsponenteBassoWienerStrategy.calcolaPrivateKeyClient(publicKey);
                break;
            case "Fattorizzazione":
                IAlgoritmoAttaccoFattorizzazioneStrategy algoritmoAttaccoFattorizzazioneStrategy = new AlgoritmoAttaccoFattorizzazioneStrategy();
                // Provo ad ottenere la chiave
                privateKeyClient = algoritmoAttaccoFattorizzazioneStrategy.calcolaPrivateKeyClient(publicKey);
                break;
            default:
                break;
        }
        return privateKeyClient;
    }

    public void setAttacco(String attacco) {
        this.attacco = attacco;
    }
}
