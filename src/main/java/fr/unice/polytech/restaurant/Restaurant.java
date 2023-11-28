package fr.unice.polytech.restaurant;

import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
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
    private final Set<MenuPlat> menus;
    private final Position position;
    // Constructeur

    /**
     * Constructeur pour les tests donnant une position par défaut
     * @param nomRestaurant nom du restaurant
     */
    public Restaurant(String nomRestaurant) {
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

    // Accesseur

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
    public List<MenuPlat> getMenus() throws AucunMenuException {
        if (nombreMenu() == 0)
            throw new AucunMenuException();
        return menus.stream().toList();
    }

    public MenuPlat getMenu(String nomMenu) throws AucunMenuException {
        List<MenuPlat> menus = getMenus();
        for (MenuPlat menu : menus){
            if (menu.getNom().equals(nomMenu))
                return menu;
        }
        return null;
    }

    /**
     * Récupère la position du restaurant
     * @return la position du restaurant
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Ajouter un menu au restaurant
     * @param m menu à ajouter
     * @return <code>true</code> si le code a été bien ajouté sinon <code>false</code>
     */
    public boolean addMenu(MenuPlat m) {
        m.setRestaurant(this);
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
