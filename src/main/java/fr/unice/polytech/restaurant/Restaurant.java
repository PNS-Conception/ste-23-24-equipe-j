package fr.unice.polytech.restaurant;

import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.utils.HoraireDate;
import fr.unice.polytech.utils.Position;

import java.util.*;

/**
 * Classe Restaurant
 * @author Equipe J
 */
public class Restaurant {
    private final String nomRestaurant;
    private final Set<MenuPlat> menus;
    private final Position position;
    private final int CapaciteMaximale;

    /**
     * Capacité enregistré par horaire
     * Uniquement les horaires avec une capacité différente de 0 sont inscrite dedans
     * Par défaut, si une horaire ne figure pas alors elle a une capacité de 0
     */
    private HashMap<HoraireDate,Integer> capaciteParHoraire = new HashMap<>();
    // Constructeur

    /**
     * Constructeur pour les tests donnant une position par défaut
     * @param nomRestaurant nom du restaurant
     */
    public Restaurant(String nomRestaurant) {
        this(nomRestaurant, new Position("Default"));
    }

    public Restaurant(String nomRestaurant, Position position) {
        this(nomRestaurant, position,10);
    }
    /**
     * Constructeur par défaut
     * @param nomRestaurant nom du restaurant
     * @param position la position du restaurant
     */
    public Restaurant(String nomRestaurant, Position position, int capaciteMaximale) {
        if (nomRestaurant == null || nomRestaurant.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        menus = new HashSet<>();
        this.nomRestaurant = nomRestaurant;
        this.position= position;
        this.CapaciteMaximale = capaciteMaximale;
    }

    // Accesseur

    public void increaseReservation(HoraireDate horaire) throws CapaciteDepasseException {
        increaseReservation(horaire, 1);
    }

    public void increaseReservation(HoraireDate horaire, int capacite) throws CapaciteDepasseException {
        if (horaire == null)
            throw new IllegalArgumentException("Horaire null");
        if (capacite > CapaciteMaximale || capacite < 0)
            throw new CapaciteDepasseException();
        if (capaciteParHoraire.containsKey(horaire)) {
            int capaciteRestante = capaciteParHoraire.get(horaire) + capacite;
            if (capaciteRestante > CapaciteMaximale) {
                throw new CapaciteDepasseException();
            } else {
                capaciteParHoraire.put(horaire, capaciteRestante);
            }
        } else {
            capaciteParHoraire.put(horaire, capacite);
        }
    }

    public void reduceReservation(HoraireDate horaire) throws CapaciteDepasseException, ImpossibleAugmenterCapaciterException {
        reduceReservation(horaire, 1);
    }

    public void reduceReservation(HoraireDate horaire, int capacite) throws CapaciteDepasseException, ImpossibleAugmenterCapaciterException {
        if (horaire == null)
            throw new IllegalArgumentException("Horaire null");
        if (capacite <= 0 || capacite >= CapaciteMaximale)
            throw new CapaciteDepasseException();
        if (capaciteParHoraire.containsKey(horaire)) {
            int capaciteRestante = capaciteParHoraire.get(horaire) - capacite;
            if (capaciteRestante <= 0) {
                capaciteParHoraire.remove(horaire);
            } else {
                capaciteParHoraire.put(horaire, capaciteRestante);
            }
        } else {
            throw new ImpossibleAugmenterCapaciterException();
        }
    }

    public int getCapaciteMaximale() {
        return CapaciteMaximale;
    }

    public int getCapacity(HoraireDate horaire) {
        if (horaire == null)
            throw new IllegalArgumentException("Horaire null");
        if (capaciteParHoraire.containsKey(horaire)) {
            return capaciteParHoraire.get(horaire);
        } else {
            return CapaciteMaximale;
        }
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
    public List<MenuPlat> getMenus() throws AucunMenuException {
        if (nombreMenu() == 0)
            throw new AucunMenuException();
        return menus.stream().toList();
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
