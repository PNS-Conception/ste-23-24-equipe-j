package fr.unice.polytech.nourriture;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.StatusUtilisateur;

import java.io.ObjectInputFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Menu d'un restaurant
 * @author Equipe J
 */
public class Menu implements MenuPlat{
    private final String nomMenu;
    private double prix;
    private Map<StatusUtilisateur, Double> prixStatus;
    private Restaurant restaurant;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param nomMenu le nom du Menu
     */
    public Menu(String nomMenu, double prix) {
        if (nomMenu == null || nomMenu.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        this.nomMenu = nomMenu;
        this.prix = prix;
        restaurant = null;
        prixStatus = new HashMap<>();
    }

    public Menu(String nomMenu, double prix, Map<StatusUtilisateur, Double> prixStatus){
        if (nomMenu == null || nomMenu.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        this.nomMenu = nomMenu;
        this.prix = prix;
        this.prixStatus = prixStatus;
        restaurant = null;
    }

    // Accesseurs et setters

    /**
     * Retourne le nom du menu
     * @return le nom du menu
     */
    @Override
    public String getNom() {
        return nomMenu;
    }

    /**
     * Retourne le prix du menu
     * @return le prix du menu
     */
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

    // Equals et HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Menu.class)
            return false;
        Menu menu = (Menu) o;
        return menu.nomMenu.equals(nomMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMenu);
    }
}
