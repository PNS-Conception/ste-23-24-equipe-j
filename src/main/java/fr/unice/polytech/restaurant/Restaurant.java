package fr.unice.polytech.restaurant;

import fr.unice.polytech.nourriture.Menu;
import java.util.*;
import fr.unice.polytech.utils.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Classe Restaurant
 * @author Equipe J
 */
public class Restaurant {
    private final String nomRestaurant;
    private final Set<Menu> menus;
    private final Position position;
    private HashMap<Integer,Commande> commandes;
    private Coordinate coordonnees;
    // Constructeur

    /**
     * Constructeur pour les tests donnant une position par défaut
     * @param nomRestaurant nom du restaurant
     */
    protected Restaurant(String nomRestaurant) {
        this(nomRestaurant, new Position("Default"));
    }

    /**
     * Constructeur par défaut
     * @param nomRestaurant nom du restaurant
     * @param position la position du restaurant
     */
    public Restaurant(String nomRestaurant, Position position) {
        if (nomRestaurant == null || nomRestaurant.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        menus = new HashSet<>();
        this.nomRestaurant = nomRestaurant;
        this.position= position;

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

    public String getNom() {
        return nomRestaurant;
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
        return restaurant.getNomRestaurant().equals(getNomRestaurant()) && position.equals(restaurant.position);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nomRestaurant, position);
    }


}
