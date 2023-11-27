package fr.unice.polytech.utils;

/**
 * Classe représentant un horaire à laquelle la livraison de la commande doit avoir lieu
 * @author Equipe J
 */
public class Horaire  implements Comparable<Horaire>{
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
    public static int calculerDuree(Horaire debut,Horaire fin){
       return  ( (fin.getHeure()- debut.getHeure())*60+ fin.getMinute()- debut.getMinute());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horaire horaire = (Horaire) o;

        if (heure != horaire.heure) return false;
        return minute == horaire.minute;
    }

    @Override
    public int hashCode() {
        int result = heure;
        result = 31 * result + minute;
        return result;
    }


    @Override
    public int compareTo(Horaire o) {
        return Integer.compare(getHeure()*60+getMinute(),o.getHeure()*60+o.getMinute());

    }
}
