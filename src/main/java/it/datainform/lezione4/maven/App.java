/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.datainform.lezione4.maven;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alfonso
 */
public class App {

    public static void main(String[] args) {

        Prodotto[] prodotti = caricaProdotti();

        for (Prodotto pr : prodotti) {
            pr.stampa();
        }

        System.out.println("----------------------------");

        for (int i = 0; i < prodotti.length; i++) {
            Prodotto pr = prodotti[i];
            pr.stampa();
        }

    }

    /**
     * esegue la query e torna un array di oggetti prodotto
     *
     * @return
     */
    private static Prodotto[] caricaProdotti() {

        EntityManagerFactory conn = Persistence.createEntityManagerFactory("magazzino");

        EntityManager em = conn.createEntityManager();

        List<Prodotto> result = em.createQuery("select p from Prodotto p", Prodotto.class)
                .getResultList();

        em.close();

        return result.toArray(new Prodotto[result.size()]);

    }

}
