package fr.unice.polytech.livraison;


import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.commande.CommandeManager;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.observer.EventListenerSystem;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.utils.Position;

import java.util.HashMap;
import java.util.Map;

import static fr.unice.polytech.livraison.EtatLivraisonCommande.EN_LIVRAISON;

/**
 * Système de livraison des commandes
 * @author Equipe J
 */
public class SystemeLivraison implements EventListenerSystem {
    private final Map<Position, CompteLivreur> livreursDisponibles;
    private final Map<Commande, CompteLivreur> livreursEnLivraison;

    // Constructeur
    /**
     * Constructeur par défaut
     */
    public SystemeLivraison(EventManager eventManager) {
        this.livreursDisponibles = new HashMap<>();
        this.livreursEnLivraison = new HashMap<>();
        eventManager.subscribe(this, EtatCommande.PRETE.toString(),
                EtatLivraisonCommande.LIVREE.toString());
    }

    /**
     * Ajoute un livreur au système de livraison
     * @param compteLivreur compte du livreur
     */
    public void addLivreur(CompteLivreur compteLivreur) {
        livreursDisponibles.put(compteLivreur.getPosition(), compteLivreur);
    }

    /**
     * Récupère le livreur le plus proche d'une position
     * @param position la position avec laquelle on cherche le plus proche livreur
     * @return le livreur le plus proche
     */
    public CompteLivreur getPlusProcheLivreur(Position position) {
        int minDistance = -1;
        Position meilleurPosition = null;

        if (livreursDisponibles.isEmpty())
            return null;
        if (livreursDisponibles.containsKey(position)) {
            return livreursDisponibles.get(position);
        }

        for (Position pos : livreursDisponibles.keySet()) {
            int distance = position.compareTo(pos);

            if (minDistance == -1 || distance < minDistance) {
                minDistance = distance;
                meilleurPosition = pos;
            }

            if (minDistance == 1) {
                return livreursDisponibles.get(meilleurPosition);
            }
        }

        return livreursDisponibles.get(meilleurPosition);
    }

    /**
     * Récupère le livreur en livraison pour une commande
     * @param commande la commande que livre le livreur
     * @return le livreur qui livre la commande
     */
    public CompteLivreur getLivreurEnLivraison(Commande commande) {
        return livreursEnLivraison.get(commande);
    }

    @Override
    public void notify(Commande commande) {
        if (commande.getInformationLivraison().getEtatLivraisonCommande() == EtatLivraisonCommande.LIVREE) {
            CompteLivreur compteLivreur = livreursEnLivraison.get(commande);

            livreursEnLivraison.remove(commande);
            livreursDisponibles.put(compteLivreur.getPosition(), compteLivreur);
        }
        else {
            CompteLivreur compteLivreur = getPlusProcheLivreur(commande.getRestaurant().getPosition());

            if (compteLivreur != null) {
                commande.getInformationLivraison().setEtatLivraisonCommande(EN_LIVRAISON);
                livreursDisponibles.remove(compteLivreur.getPosition());
                livreursEnLivraison.put(commande, compteLivreur);
                compteLivreur.notify(EN_LIVRAISON.toString());
            }
        }
    }
}
