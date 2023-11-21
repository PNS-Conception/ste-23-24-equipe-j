package fr.unice.polytech.utils;

import java.util.Objects;

/**
 * Classe représentant un horaire à laquelle la livraison de la commande doit avoir lieu
 * @author Equipe J
 */
public class Horaire {
    private int heure;
    private int minute;

    /**
     * Constructeur par défaut
     */
    public Horaire(String hourInput) {
        String[] parts = hourInput.split(":");
        if (parts.length == 2) {
            try {
                heure = Integer.parseInt(parts[0]);
                minute = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.err.println("La chaîne n'est pas au format attendu.");
            }
        } else {
            System.err.println("La chaîne n'est pas au format attendu.");
        }
    }

    public Horaire(int heure, int minute) {
        this.heure = heure;
        this.minute = minute;
    }

    /**
     * Retourne l'heure à laquelle la livraison de la commande doit avoir lieu
     * @return l'heure
     */
    public int getHeure() {
        return heure;
    }

    /**
     * Retourne les minutes à laquelle la livraison de la commande doit avoir lieu
     * @return les minutes
     */
    public int getMinute() {
        return minute;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        else if (o == null || o.getClass() != Horaire.class)
            return false;
        Horaire horaire = (Horaire) o;
        return heure == horaire.heure && minute == horaire.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heure, minute);
    }

    @Override
    public String toString() {
        return heure + ":" + minute;
    }
}
