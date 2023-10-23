package fr.unice.polytech.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe contenant la date de livraison d'une commande
 * @author Equipe J
 */
public class Date {

    private final int jour;
    private final int mois;
    private final int annee;

    /**
     * Constructeur par défaut
     * @param dateInput la date
     */
    public Date(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Utilisez le parseur pour convertir la chaîne en objet LocalDate
        LocalDate date = LocalDate.parse(dateInput, formatter);

        // Obtenez l'année, le mois et le jour sous forme d'entiers
        annee = date.getYear();
        mois = date.getMonthValue();
        jour = date.getDayOfMonth();
    }

    /**
     * Retourne le jour de la date
     * @return le jour de la date
     */
    public int getJour() {
        return jour;
    }

    /**
     * Retourne le mois de la date
     * @return le mois de la date
     */
    public int getMois() {
        return mois;
    }

    /**
     * Retourne l'année de la date
     * @return l'année de la date
     */
    public int getAnnee() {
        return annee;
    }
}
