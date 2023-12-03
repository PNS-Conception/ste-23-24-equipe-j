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
    private final TypeMenu typeMenu;

    // Constructeur

    /**
     * Constructeur pour les menus standard
     * @param nomMenu le nom du menu
     * @param prix le prix du menu
     */
    public Menu(String nomMenu, double prix) {
        this(nomMenu, prix, TypeMenu.NORMAL);
    }

    /**
     * Constructeur par défaut incluant le type de menu
     * @param nomMenu le nom du menu
     * @param prix le prix de la commande
     * @param typeDuMenu le type du menu de la commande
     * @throws IllegalArgumentException le nom de menu n'existe pas où est null
     * @throws IllegalArgumentException le type de menu est <code>null</code>
     */
    public Menu(String nomMenu, double prix, TypeMenu typeDuMenu) {
        if (nomMenu == null || nomMenu.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        else if (typeDuMenu == null)
            throw new IllegalArgumentException("Le type du menu est null");

        this.nomMenu = nomMenu;
        this.prix = prix;
        restaurant = null;
        typeMenu = typeDuMenu;
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

    /**
     * Retourne le type du menu
     * @return le type du menu
     */
    public TypeMenu getTypeMenu() {
        return typeMenu;
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
        return menu.nomMenu.equals(nomMenu) && typeMenu.equals(menu.typeMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMenu, typeMenu);
    }
}
