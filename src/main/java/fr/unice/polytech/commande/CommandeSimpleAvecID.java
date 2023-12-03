package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeSimple;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Classe abstraite avec des menus et des plats pour une commande avec un ID
 * @author EquipeJ
 */
public abstract class CommandeSimpleAvecID extends CommandeAvecID implements ICommandeSimple {
    protected final Map<MenuPlat, Integer> menuPlats;
    protected Restaurant restaurant;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeSimpleAvecID(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        menuPlats = new HashMap<>();
    }


    @Override
    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    @Override
    public boolean estCommandeSimple() {
        return true;
    }

    @Override
    public Map<MenuPlat, Integer> getMenuPlats() {
        return menuPlats;
    }

    @Override
    public Optional<Restaurant> getRestaurant() {
        return Optional.of(restaurant);
    }

    @Override
    public void ajoutMenuPlat(MenuPlat menuPlat, TypeMenuPlat typeMenuPlat) throws RestaurantNonValideException, CapaciteDepasseException {
        if (restaurant == null)
            restaurant = menuPlat.getRestaurant();

        GestionCommande.ajoutMenuPlat(menuPlats, menuPlat, restaurant);
    }

    @Override
    public boolean supprimerMenuPlat(MenuPlat menuPlat) {
        return GestionCommande.supprimerMenuPlat(menuPlats, menuPlat);
    }

    public abstract void payerCommande(Token token) throws TokenException;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
