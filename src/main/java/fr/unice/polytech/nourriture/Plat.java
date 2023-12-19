package fr.unice.polytech.nourriture;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.StatusUtilisateur;

import java.util.*;

/**
 * Classe Plat
 * @author Equipe J
 */
public class Plat implements MenuPlat {
    private final String nom;
    private double prix;
    private final Map<StatusUtilisateur, Double> prixStatus;
    private final List<String> aliments;
    private final List<String> alergenes;
    private Restaurant restaurant;

    /**
     * Constructeur par défaut de Plat
     * @param nom le nom du plat
     * @param prix le prix du plat
     * @param aliments les aliments du plat
     * @param alergenes les alergenes du plat
     */
    public Plat(String nom, double prix, List<String> aliments, List<String> alergenes){
        this.nom = nom;
        this.prix = prix;
        this.aliments = aliments;
        this.alergenes = alergenes;
        this.prixStatus = new EnumMap<>(StatusUtilisateur.class);
        restaurant = null;
    }

    public Plat(String nom, double prix, List<String> aliments, List<String> alergenes, Map<StatusUtilisateur, Double> prixStatus){
        this.nom = nom;
        this.prix = prix;
        this.aliments = aliments;
        this.alergenes = alergenes;
        this.prixStatus = prixStatus;
        restaurant = null;
    }

    // Accesseurs et setters

    @Override
    public String getNom() {
        return nom;
    }


    @Override
    public double getPrix(StatusUtilisateur statusUtilisateur) {
        if (prixStatus.containsKey(statusUtilisateur)){
            return prixStatus.get(statusUtilisateur);
        }
        return prix;
    }

    @Override
    public void setPrix(double newPrix){
        this.prix = newPrix;
    }

    @Override
    public void setPrixStatus(StatusUtilisateur statusUtilisateur, double newPrixStatus){
        prixStatus.put(statusUtilisateur, newPrixStatus);
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public Restaurant getRestaurant() {
        return restaurant;
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
