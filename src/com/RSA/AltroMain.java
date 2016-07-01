package com.RSA;

import com.RSA.model.algoritmoRSA.Client;
import com.RSA.model.algoritmoRSA.Cracker;
import com.RSA.model.algoritmoRSA.PrivateKey;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigInteger;

/**
 * Created by emanuele on 30/06/16.
 */
public class AltroMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        /*int i = 0;
        Client Bob = new Client("Bob",false);
        Client Alice = new Client("Alice",false);
        Cracker Eve = new Cracker();
        while (i<5) {
            PrivateKey privateKeyBob = Eve.ottieniChiavePrivataDaChiavePubblica(Bob.get_publicKey());
            if (privateKeyBob != null) {
                System.out.println(privateKeyBob.get_p()+"  "+privateKeyBob.get_q());
                i++;
            } else System.out.println("non trovato");
            Bob = new Client("Bob",false);
           // System.out.println(Bob.get_privateKey().get_p());*/
        Client Bob = new Client("Bob",false);
        Client Alice = new Client("Alice",false);
        double n = Bob.get_publicKey().get_n().doubleValue();
        double radice = Math.sqrt(n);
        radice = Math.ceil(radice);
        if (radice % 2 == 0) {
            radice = radice+1;
        }
        Integer radice1 = (int) radice;
        BigInteger rad = new BigInteger(radice1.toString());
        while (Bob.get_publicKey().get_n().mod(rad) == BigInteger.ZERO) {

        }
    }
    }