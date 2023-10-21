package fr.unice.polytech.restaurant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Horaire {

    private int heure;
    private int minute;

    public Horaire(int heure, int minute) {
        this.heure = heure;
        this.minute = minute;
    }

    public Horaire(String hourInput) {
        String[] parts = hourInput.split(":");
        if (parts.length == 2) {
            try {
                this.heure = Integer.parseInt(parts[0]);
                this.minute = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.err.println("La chaîne n'est pas au format attendu.");
            }
        } else {
            System.err.println("La chaîne n'est pas au format attendu.");
        }
    }

}
