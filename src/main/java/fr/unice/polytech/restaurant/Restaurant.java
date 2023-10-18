package fr.unice.polytech.restaurant;

import java.util.*;

/**
 * Classe Restaurant
 * @author Equipe J
 */
public class Restaurant {
    private final String nomRestaurant;
    private final Set<Menu> menus;
    private HashMap<Integer,Commande> commandes;
    private Coordinate coordonnees;

    // Constructor

    /**
     * Constructeur par défaut
     * @param nomRestaurant nom du Restaurant
     */
    public Restaurant(String nomRestaurant) {
        if (nomRestaurant == null || nomRestaurant.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        menus = new HashSet<>();
        this.nomRestaurant = nomRestaurant;
    }

    public Restaurant(String nomRestaurant, Coordinate coordonnees) {
        this(nomRestaurant);
        this.coordonnees = coordonnees;
        this.commandes = new HashMap<>();
    }

    // Accessor

    public HashMap<Integer,Commande> getCommandes() {
        return commandes;
    }

    public void addCommande(Commande commande) {
        this.commandes.remove(commande.getId());
        this.commandes.put(commande.getId(), commande);
    }

    public Coordinate getCoordonnees() {
        return coordonnees;
    }

    /**
     * Récupérer le nom du restaurant
     * @return Le nom du restaurant
     */
    public String getNomRestaurant() {
        return nomRestaurant;
    }

    /**
     * Récupère le nombre de menu du restaurant
     * @return Le nombre de menu
     */
    public int nombreMenu() {
        return menus.size();
    }

    /**
     * Récupère les menus du restaurant
     * @return les menus sous forme de liste
     */
    public List<Menu> getMenus() throws AucunMenuException {
        if (nombreMenu() == 0)
            throw new AucunMenuException();
        return menus.stream().toList();
    }

    /**
     * Ajouter un menu au restaurant
     * @param m menu à ajouter
     * @return <code>true</code> si le code a été bien ajouté sinon <code>false</code>
     */
    public boolean addMenu(Menu m) {
        return menus.add(m);
    }


    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Restaurant.class)
            return false;
        Restaurant restaurant = (Restaurant) o;
        return restaurant.getNomRestaurant().equals(getNomRestaurant());
    }

    @Override
    public int hashCode(){
        return Objects.hash(nomRestaurant);
    }


}
