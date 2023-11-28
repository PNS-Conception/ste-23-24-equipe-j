package fr.unice.polytech.livraison;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.commande.interfacecommande.ILivrable;
import fr.unice.polytech.observer.EventListenerSystem;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.utils.adress.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.unice.polytech.livraison.EtatLivraisonCommande.EN_LIVRAISON;

/**
 * Système de livraison des commandes
 * @author Equipe J
 */
public class SystemeLivraison implements EventListenerSystem {
    private final Map<Position, CompteLivreur> livreursDisponibles;
    private final Map<CommandeAvecID, CompteLivreur> livreursEnLivraison;

    // Constructeur
    /**
     * Constructeur par défaut
     */
    public SystemeLivraison() {
        this.livreursDisponibles = new HashMap<>();
        this.livreursEnLivraison = new HashMap<>();
        EventManager.subscribe(this, EtatCommande.PRETE.toString(),
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
    public CompteLivreur getLivreurEnLivraison(ICommande commande) {
        return livreursEnLivraison.get(commande);
    }

    /**
     * Récupère la liste des livreurs disponibles
     * @return la liste des livreurs disponibles
     */
    public List<CompteLivreur> getLivreursDisponibles() {
        return List.of(livreursDisponibles.values().toArray(new CompteLivreur[0]));
    }

    @Override
    public void update(ICommande commande) {
        if (commande.estLivrable()) {
            ILivrable commandeLivrable = (ILivrable) commande;

            if (commandeLivrable.getInformationLivraison().getEtatLivraisonCommande() == EtatLivraisonCommande.LIVREE) {
                CompteLivreur compteLivreur = livreursEnLivraison.get(commande);

                livreursEnLivraison.remove(commande);
                livreursDisponibles.put(compteLivreur.getPosition(), compteLivreur);
            } else {
                CompteLivreur compteLivreur = getPlusProcheLivreur(new Position(0, 0));

                if (compteLivreur != null) {
                    commandeLivrable.getInformationLivraison().setEtatLivraisonCommande(EN_LIVRAISON);
                    livreursDisponibles.remove(compteLivreur.getPosition());
                    livreursEnLivraison.put((CommandeAvecID) commande, compteLivreur);
                    compteLivreur.notify(EN_LIVRAISON.toString());
                }
            }
        }
    }
}
