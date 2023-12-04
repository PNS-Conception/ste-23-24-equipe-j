package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.commande.interfacecommande.ICommandeSimple;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Commande simple sans identifiant de commande ajoutable dans une commande multiple
 * @author Equipe J
 */
public class CommandeSimpleSansID implements ICommandeAjoutable, ICommandeSimple {
    private final CompteUtilisateur createur;
    private EtatCommande etatCommande;
    private final Map<MenuPlat, Integer> menuPlats;
    private ACommandeGroupe commandeGroupe;
    private Restaurant restaurant;

    /**
     * Constructeur par défaut de la commande
     * @param createur le créateur de la commande
     */
    public CommandeSimpleSansID(CompteUtilisateur createur) {
        this.createur = createur;
        etatCommande = EtatCommande.EN_ATTENTE;
        menuPlats = new HashMap<>();
    }

    /**
     * Ajoute la commandeGroupe pour permettre de notifier la commande groupe
     * @param commandeGroupe la commande groupe à qui cette commande est associé
     */
    public void setCommandeGroupe(ACommandeGroupe commandeGroupe) {
        this.commandeGroupe = commandeGroupe;
    }

    @Override
    public CompteUtilisateur getCreateur() {
        return createur;
    }

    @Override
    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
        commandeGroupe.updateStatusCommande();
        EventManager.notify(this, etatCommande.name());
    }

    @Override
    public boolean estCommandeSimple() {
        return true;
    }

    @Override
    public boolean estLivrable() {
        return false;
    }

    @Override
    public boolean estPayable() {
        return false;
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

    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */

    public double getPrix() {
        double prix = 0.0;

        for (Map.Entry<MenuPlat, Integer> menusPlats : menuPlats.entrySet())
            prix += menusPlats.getKey().getPrix() * menusPlats.getValue();

        return prix;
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
    public InformationLivraison getInformationLivraison() {
        return null;
    }
}
