package fr.unice.polytech.commande;

import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * Classe d'une commande d'une seul personne
 * @author Equipe J
 */
public class Commande {
    private final int id;
    private double prixCommande;
    private final Map<MenuPlat, Integer> menuPlats;
    private Restaurant restaurant;
    private EtatCommande etatCommande;
    private CompteUtilisateur compteUtilisateur;
    private InformationLivraison informationLivraison;

    /**
     * Constructeur pour les tests
     */
    public Commande(int id){
        this.id = id;
        prixCommande = 0;
        menuPlats = new HashMap<>();
        etatCommande = EtatCommande.EN_ATTENTE;
        informationLivraison = new InformationLivraison(this);
        compteUtilisateur = null;
    }

    /**
     * Constructeur par défaut de Commande
     */
    public Commande(CompteUtilisateur compteUtilisateur, int id) {
        this(id);
        this.compteUtilisateur = compteUtilisateur;
    }

    // Accesseurs et setters

    /**
     * Renvoie l'identifiant de la commande
     * @return l'identifiant de la commande
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */
    public double getPrix() {
        return prixCommande;
    }

    /**
     * Retourne l'état de la commande au niveau du client et du restaurant
     * @return l'état de la commande
     */
    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

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
     * Retourne les informations de livraison de la commande
     * @return les informations de livraison de la commande
     */
    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }


    /**
     * Change le statut de la commande quand elle est terminée pour le client ou restaurant
     * @param etatCommande le nouveau statut de la commande
     */
    public void setEtatCommande(EtatCommande etatCommande) {
        EventManager.notify(this, etatCommande.toString());
        this.etatCommande = etatCommande;
    }

    /**
     * Ajoute les informations de livraison de la commande
     * @param informationLivraison les informations de livraison de la commande
     */
    public void setInformationLivraison(InformationLivraison informationLivraison) {
        this.informationLivraison = informationLivraison;
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

    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Commande.class != o.getClass())
            return false;
        Commande commande = (Commande) o;
        return id == commande.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
