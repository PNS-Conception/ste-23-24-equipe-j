package fr.unice.polytech.nourriture;

import java.util.List;
import java.util.Objects;

/**
 * Classe Plat
 * @Author Equipe J
 */
public class Plat implements MenuPlat {
    private String nom;
    private double prix;
    private List<String> aliments;
    private List<String> alergenes;

    public Plat(String nom, double prix, List<String> aliments, List<String> alergenes){
        this.nom = nom;
        this.prix = prix;
        this.aliments = aliments;
        this.alergenes = alergenes;
    }

    // Accesseurs

    @Override
    public String getNom() {
        return nom;
    }


    @Override
    public double getPrix() {
        return prix;
    }


    /**
     * Récupérer les aliments du plat
     * @return les aliments du plat
     */
    public List<String> getAliments() {
        return aliments;
    }

    /**
     * Récupérer les alergenes du plat
     * @return les alergenes du plat
     */
    public List<String> getAlergenes() {
        return alergenes;
    }

    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || getClass() != o.getClass())
            return false;
        Plat plat = (Plat) o;
        return nom.equals(plat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
