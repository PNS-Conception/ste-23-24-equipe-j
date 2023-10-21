package fr.unice.polytech.utils;

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
}
