package fr.unice.polytech.restaurant;

import fr.unice.polytech.exceptions.AucunMenuException;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.ImpossibleAugmenterCapaciterException;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.restaurant.Reduction.GoodClientReduction;
import fr.unice.polytech.restaurant.Reduction.SpecialRate;
import fr.unice.polytech.restaurant.reservation.Reservation;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.UserStatut;
import fr.unice.polytech.utils.temps.HoraireDate;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.offre.Creneau;
import fr.unice.polytech.offre.ICreneau;


import java.util.*;

/**
 * Classe Restaurant
 *
 * @author Equipe J
 */
public class Restaurant {
    private final String nomRestaurant;
    private final Set<MenuPlat> menus;
    private final Position position;


    private Reservation reservation;
    private GoodClientReduction goodClientReduction;
    private SpecialRate specialRate;

    private List<ICreneau> creneaus = new ArrayList<>();
    private int capacite;
    private int duree_slot;
    private  static  final int DEFAULT_DUREE_SLOT=10;
    public List<ICreneau> getCreneaus() {
        return creneaus;
    }

    public int getDuree_slot() {
        return duree_slot;
    }

    public void setDuree_slot(int duree_slot) {
        this.duree_slot = duree_slot;
    }



    // Constructeur

    /**
     * Constructeur pour les tests donnant une position par défaut
     *
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
     *
     * @param nomRestaurant nom du restaurant
     * @param position      la position du restaurant
     */
    public Restaurant(String nomRestaurant, Position position, int capaciteMaximale) {
        if (nomRestaurant == null || nomRestaurant.isEmpty())
            throw new IllegalArgumentException("Nom vide");
        menus = new HashSet<>();
        this.nomRestaurant = nomRestaurant;
        this.position= position;
        this.reservation = new Reservation(capaciteMaximale);
        this.goodClientReduction = new GoodClientReduction();
        this.specialRate = new SpecialRate();
        this.capacite = 0;
        this.creneaus = new ArrayList<>();
        duree_slot=DEFAULT_DUREE_SLOT;
    }

    public void ajoutCapacite(int capacite) {
        this.capacite += capacite;

    }

    public boolean ajouterCreneau(ICreneau creneau) {
        boolean estAjoute= estUnCreneauValide(creneau);
        if(estAjoute){
            creneaus.add(creneau);
            ajoutCapacite(ICreneau.getCapaciteCreneau(creneau));
        }

        return estAjoute;

    }

    public boolean estUnCreneauValide(ICreneau creneau) {
        return creneaus.stream().allMatch(c -> (c.getDebut().compareTo(creneau.getDebut()) < 0 && c.getFin().compareTo(creneau.getFin()) < 0) || (c.getDebut().compareTo(creneau.getDebut()) > 0 && c.getFin().compareTo(creneau.getFin()) > 0));


    }


    public GoodClientReduction getGoodClientReduction() {
        return goodClientReduction;
    }

    public SpecialRate getSpecialRate() {
        return specialRate;
    }

    public void setSpecialRate(SpecialRate specialRate) {
        this.specialRate = specialRate;
    }

    public void setGoodClientReduction(GoodClientReduction goodClientReduction) {
        this.goodClientReduction = goodClientReduction;
    }


    // Accesseur


    public void increaseReservation(HoraireDate horaire, int capacite) throws CapaciteDepasseException {
        this.reservation.increaseReservation(horaire, capacite);
    }

    public int getCapacity(HoraireDate horaire) {
        return reservation.getCapacity(horaire);
    }

    /**
     * Récupérer le nom du restaurant
     *
     * @return Le nom du restaurant
     */
    public String getNomRestaurant() {
        return nomRestaurant;
    }

    /**
     * Récupère le nombre de menu du restaurant
     *
     * @return Le nombre de menu
     */
    public int nombreMenu() {
        return menus.size();
    }

    /**
     * Récupère les menus du restaurant
     *
     * @return les menus sous forme de liste
     */
    public List<MenuPlat> getMenus() throws AucunMenuException {
        if (nombreMenu() == 0)
            throw new AucunMenuException();
        return menus.stream().toList();
    }

    /**
     * Récupère la position du restaurant
     *
     * @return la position du restaurant
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Ajouter un menu au restaurant
     *
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
    public int hashCode() {
        return Objects.hash(nomRestaurant, position);
    }

    public int getCapacite() {
        return capacite;
    }

}
