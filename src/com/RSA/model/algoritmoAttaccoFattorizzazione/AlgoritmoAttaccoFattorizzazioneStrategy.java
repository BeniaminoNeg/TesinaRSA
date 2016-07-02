package com.RSA.model.algoritmoAttaccoFattorizzazione;

import com.RSA.model.algoritmoRSA.PrivateKey;
import com.RSA.model.algoritmoRSA.PublicKey;

import java.math.BigInteger;

/**
 * Created by emanuele & beniamino on 01/07/16.
 */
public class AlgoritmoAttaccoFattorizzazioneStrategy implements IAlgoritmoAttaccoFattorizzazioneStrategy {
    @Override
    public PrivateKey calcolaPrivateKeyClient(PublicKey publicKeyClient) {
        double n = publicKeyClient.get_n().doubleValue();
        double radice = Math.sqrt(n);
        radice = Math.ceil(radice);
        if (radice % 2 == 0) {
            radice = radice + 1;
        }
        Integer radice1 = (int) radice;
        BigInteger rad = new BigInteger(radice1.toString());
        while (publicKeyClient.get_n().mod(rad) != BigInteger.ZERO && rad.compareTo(BigInteger.ZERO) >= 0) {
            rad = rad.subtract(new BigInteger("2"));
        }
        BigInteger p = rad;
        BigInteger q = publicKeyClient.get_n().divide(p);
        BigInteger fi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = publicKeyClient.get_e().modInverse(fi_n);
        PrivateKey privateKey = new PrivateKey(p, q, d);
        return privateKey;
    }
}
