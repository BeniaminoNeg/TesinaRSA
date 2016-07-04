/**
 *
 */
package com.RSA.model.algoritmoRSA;

/**
 * Questa classe rappresenta l'astrazione pi� alta per un messaggio.
 *
 * @author emanuele & beniamino
 */
public abstract class Messaggio {

    /**
     * Mittente del messaggio.
     */
    private Utente _mittente;
    /**
     * Destinatario del messaggio.
     */
    private Utente _destinatario;

    /**
     * Costruttore.
     *
     * @param _mittente     Mittente del messaggio.
     * @param _destinatario Destinatario del messaggio.
     */
    public Messaggio(Utente _mittente, Utente _destinatario) {
        this._mittente = _mittente;
        this._destinatario = _destinatario;
    }

    /**
     * @return the _mittente
     */
    public Utente get_mittente() {
        return _mittente;
    }

    /**
     * @param _mittente the _mittente to set
     */
    public void set_mittente(Utente _mittente) {
        this._mittente = _mittente;
    }

    /**
     * @return the _destinatario
     */
    public Utente get_destinatario() {
        return _destinatario;
    }

    /**
     * @param _destinatario the _destinatario to set
     */
    public void set_destinatario(Utente _destinatario) {
        this._destinatario = _destinatario;
    }


}
