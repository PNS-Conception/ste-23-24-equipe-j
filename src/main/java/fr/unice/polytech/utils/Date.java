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
    public Date(LocalDate localDate){
        annee = localDate.getYear();
        mois = localDate.getMonthValue();
        jour = localDate.getDayOfMonth();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;

        if (jour != date.jour) return false;
        if (mois != date.mois) return false;
        return annee == date.annee;
    }

    @Override
    public int hashCode() {
        int result = jour;
        result = 31 * result + mois;
        result = 31 * result + annee;
        return result;
    }
}
