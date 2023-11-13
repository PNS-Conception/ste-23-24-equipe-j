package fr.unice.polytech.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Date.class)
            return false;

        Date date = (Date) o;
        return date.jour == this.jour && date.mois == this.mois && date.annee == this.annee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, mois, annee);
    }

    @Override
    public String toString() {
        return "Date{" +
                "jour=" + jour +
                ", mois=" + mois +
                ", annee=" + annee +
                '}';
    }
}
