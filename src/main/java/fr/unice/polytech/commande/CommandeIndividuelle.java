package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe d'une commande d'une seul personne
 * @author Equipe J
 */
public class CommandeIndividuelle extends Commande {
    private final Map<MenuPlat, Integer> menuPlats;
    private double prixCommande;
    private Restaurant restaurant;
    private CompteUtilisateur compteUtilisateur;
    private EtatCommande etatCommande;

    /**
     * Constructeur pour les tests
     */
    public CommandeIndividuelle(int id){
        super(id);
        menuPlats = new HashMap<>();
        compteUtilisateur = null;
        etatCommande = EtatCommande.EN_ATTENTE;
    }

    /**
     * Constructeur par défaut de Commande
     */
    public CommandeIndividuelle(CompteUtilisateur compteUtilisateur, int id) {
        this(id);
        this.compteUtilisateur = compteUtilisateur;
    }

    // Accesseurs et setters

    /**
     * Retourne les plats ou menus et les quantités de la commande
     * @return les plats ou menus et les quantités de la commande
     */
    public Map<MenuPlat, Integer> getMenuPlats() {
        return menuPlats;
    }

    /**
     * Retourne le restaurant contenant les plats ou menus de la commande
     * @return le restaurant contenant les plats ou menus de la commande
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Retourne le compte utilisateur qui a passé la commande
     */
    public CompteUtilisateur getCompteUtilisateur() {
        return compteUtilisateur;
    }

    /**
     * Change le statut de la commande quand elle est terminée pour le client ou restaurant
     * @param etatCommande le nouveau statut de la commande
     */
    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }

    @Override
    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    @Override
    public boolean estGroupee() {
        return false;
    }

    @Override
    public double getPrix() {
        return prixCommande;
    }

    @Override
    public List<CommandeIndividuelle> getCommandes() {
        return List.of(this);
    }

    // Equals et hashCode

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o))
            return false;
        CommandeIndividuelle commandeIndividuelle = (CommandeIndividuelle) o;
        return !commandeIndividuelle.estGroupee();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    // Méthodes

    /**
     * Ajoute un plat ou un menu avec une quantite à la commande
     * @param menuPlat le plat ou menu à ajouter
     * @param quantite la quantite du plat ou menu à ajouter
     * @throws IllegalArgumentException si la quantite est négative ou
     * si le plat ou menu n'est pas du même restaurant que la commande
     */
    public void ajoutMenuPlat(MenuPlat menuPlat, int quantite){
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        if (restaurant == null)
            restaurant = menuPlat.getRestaurant();
        else if (!restaurant.equals(menuPlat.getRestaurant()))
            throw new IllegalArgumentException("Le plat ou menu n'est pas du même restaurant que la commande");

        if (menuPlats.containsKey(menuPlat)){
            int nouvelleQuantite = menuPlats.get(menuPlat) + quantite;
            menuPlats.put(menuPlat, nouvelleQuantite);
        }
        else {
            menuPlats.put(menuPlat, quantite);
        }
        prixCommande += menuPlat.getPrix();
    }

    // A revoir si on ajoute une quantite à supprimer d'un plat
    public void suppressionPlat(Plat plat){
        if (menuPlats.containsKey(plat)){
            int quantite = menuPlats.get(plat) - 1;
            if (quantite == 0){
                menuPlats.remove(plat);
            }
            else {
                menuPlats.put(plat, quantite);
            }
        }
    }
}
