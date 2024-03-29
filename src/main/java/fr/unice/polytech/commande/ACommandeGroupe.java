package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.commande.interfacecommande.ICommandeSimple;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.*;

import java.util.*;

/**
 * Classe abstraite d'une commande groupe contenant toutes les commandes
 * @author Equipe J
 */
public abstract class ACommandeGroupe extends CommandeAvecID {

    protected ACommandeGroupe(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
    }

    /**
     * Ajoute une commande à la liste des commandes
     * @param commande la commande à ajouter
     */
    public abstract void ajouterCommande(ICommandeAjoutable commande);

    /**
     * Supprime une commande de la liste des commandes
     * @param commande la commande à supprimer
     */
    public abstract void supprimerCommande(ICommandeAjoutable commande);

    /**
     * Récupérer la liste de commandes de la commandes groupe
     * @return les commandes groupes
     */
    public abstract List<ICommandeAjoutable> getCommandes();

    /**
     * Récupérer la liste des commandes en préparation de la commande groupe
     * @return les commandes en préparation
     */
    public List<ICommandeAjoutable> getCommandesEnPreparationRestaurant(Restaurant restaurant) {
        List<ICommandeAjoutable> commandesPreparation = new ArrayList<>();

        for (ICommandeAjoutable commande : getCommandes()) {
            if (commande.getEtatCommande() == EtatCommande.EN_PREPARATION) {
                if (!commande.estCommandeSimple()) {
                    commandesPreparation.addAll(((CommandeMultipleAjoutable) commande).getCommandes());
                }
                else {
                    Optional<Restaurant> restaurantCommande = ((ICommandeSimple) commande).getRestaurant();

                    if (restaurantCommande.isPresent() && restaurantCommande.get().equals(restaurant))
                        commandesPreparation.add(commande);
                }
            }
        }

        return commandesPreparation;
    }

    /**
     * Met à jour l'état de la commande groupe quand une commande est ajoutée ou supprimée
     */
    protected void updateStatusCommande() {
        EtatCommande newEtatCommande = CalculEtatCommande.calculEtatCommande(getCommandes());

        if (this.etatCommande != newEtatCommande)
            super.setEtatCommande(newEtatCommande);
    }

    @Override
    public EtatCommande getEtatCommande() {
        return CalculEtatCommande.calculEtatCommande(getCommandes());
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        if (etatCommande == EtatCommande.ANNULE) {
            this.etatCommande = etatCommande;
            EventManager.notify(this, etatCommande.toString());
        }
        else
            throw new IllegalArgumentException("Impossible de changer l'état d'une commande de groupe");
    }

    @Override
    public boolean estCommandeSimple() {
        return false;
    }

    @Override
    public boolean estLivrable() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
