package fr.unice.polytech.restaurant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {

    private int jour;
    private int mois;
    private int annee;

    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }


    public Date(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Utilisez le parseur pour convertir la chaîne en objet LocalDate
        LocalDate date = LocalDate.parse(dateInput, formatter);

        // Obtenez l'année, le mois et le jour sous forme d'entiers
        this.annee = date.getYear();
        this.mois = date.getMonthValue();
        this.jour = date.getDayOfMonth();
    }

}
