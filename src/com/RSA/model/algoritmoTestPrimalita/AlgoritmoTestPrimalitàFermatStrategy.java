package com.RSA.model.algoritmoTestPrimalita;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by beniamino on 30/06/16.
 */
public class AlgoritmoTestPrimalitàFermatStrategy implements IAlgoritmoTestPrimalitaStrategy{
    @Override
    public boolean testaPrimalitaIntero(BigInteger n, int times)
    {
        if (n.equals(BigInteger.ONE))
            return false;

        BigInteger a;
        Random rand = new Random();

        for (int i = 0; i < times; i++)
        {
            a = new BigInteger(n.bitLength() - 1, rand);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }
}
