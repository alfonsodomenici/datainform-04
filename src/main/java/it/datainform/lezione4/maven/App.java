/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.datainform.lezione4.maven;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alfonso
 */
public class App {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String scelta;
        do {
            stampaMenu();
            scelta = s.nextLine();
            switch (scelta) {
                case "1":
                    menuElencoProdotti();
                    break;
                case "2":
                    menuNuovoProdotto(s);
                    break;
                case "3":
                    menuEsci();
                    break;
                default:
                    System.out.println("Scelta non corretta..");
            }
        } while (!scelta.equals("3"));

        System.out.println("------------ End gestione prodotti ---------------");

    }

    public static void creaProdotto(String codice, String descrizione, float prezzo) {

        Prodotto p = new Prodotto();
        p.setCodice(codice);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);

        EntityManagerFactory conn = Persistence.createEntityManagerFactory("magazzino");

        EntityManager em = conn.createEntityManager();

        em.getTransaction().begin();

        em.merge(p);

        em.getTransaction().commit();

        em.close();

    }

    /**
     * esegue la query e torna un array di oggetti prodotto
     *
     * @return
     */
    private static Prodotto[] caricaProdotti() {

        EntityManagerFactory conn = Persistence.createEntityManagerFactory("magazzino");

        EntityManager em = conn.createEntityManager();

        List<Prodotto> result = em.createQuery("select p from Prodotto p order by p.descrizione", Prodotto.class)
                .getResultList();

        em.close();

        return result.toArray(new Prodotto[result.size()]);

    }

    private static void menuElencoProdotti() {
        Prodotto[] prodotti = caricaProdotti();
        System.out.println("------------ Elenco prodotti--------------");
        Arrays.asList(prodotti).stream().forEach(p -> p.stampa());
        System.out.println("------------- fine elenco prodotti --------");
    }

    private static void menuEsci() {
        System.out.println("Arrivederci e grazie...");
    }

    private static void stampaMenu() {
        System.out.println("");
        System.out.println("");

        System.out.println("------------ Start gestione prodotti ---------------");

        System.out.println("---------------- Menu principale -------------------");

        System.out.println("1 - Elenco prodotti");

        System.out.println("2 - Nuovo prodotto");

        System.out.println("3 - Esci");

        System.out.println("-----------------------------------------------------");

        System.out.println("Che vuoi fare?");
    }

    private static void menuNuovoProdotto(Scanner s) {
        System.out.println("Inserisci codice prodotto");
        String codice = s.nextLine();
        System.out.println("Inserisci descrizione prodotto");
        String nome = s.nextLine();
        System.out.println("Inserisci prezzo prodotto");
        float prezzo = s.nextFloat();
        creaProdotto(codice, nome, prezzo);
        System.out.println("il prodotto è stato inserito");
    }

}
