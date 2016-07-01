package com.RSA.model.algoritmoAttaccoFattorizzazione;

import com.RSA.model.algoritmoRSA.PrivateKey;
import com.RSA.model.algoritmoRSA.PublicKey;

/**
 * Created by emanuele on 01/07/16.
 */
public interface IAlgoritmoAttaccoFattorizzazioneStrategy {

    public PrivateKey calcolaPrivateKeyClient(PublicKey publicKeyClient);


}
