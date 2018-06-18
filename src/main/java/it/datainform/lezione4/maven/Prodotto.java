/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.datainform.lezione4.maven;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author alfonso
 */
@Entity
public class Prodotto implements Serializable {

    @Id
    private String codice;

    private String descrizione;

    private float prezzo;

    public void stampa() {
        System.out.println("codice:" + codice + ","
                + "descrizione: " + descrizione + ","
                + "prezzo: " + prezzo);
    }

    /*
    metodi accessors
     */
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        if (prezzo < 0) {
            throw new RuntimeException("prezzo non valido");
        }

        this.prezzo = prezzo;
    }

}
