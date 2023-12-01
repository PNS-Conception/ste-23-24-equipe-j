package fr.unice.polytech.nourriture;

import fr.unice.polytech.restaurant.Restaurant;

import java.util.Objects;

/**
 * Menu d'un restaurant
 * @author Equipe J
 */
public class Menu implements MenuPlat{
    private final String nomMenu;
    private final double prix;
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
    public double getPrix() {
        return prix;
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
